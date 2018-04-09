package cn.oauth.open.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.oauth.open.cache.CodeCacheService;
import cn.oauth.open.cache.vo.CodeCacheVo;
import cn.oauth.open.client.UserClient;
import cn.oauth.open.constants.Constants.OAUTH_ACCESS_TOKEN;
import cn.oauth.open.constants.Constants.OAUTH_CLIENT_DETAILS;
import cn.oauth.open.dao.OauthAccessLogDAO;
import cn.oauth.open.dao.OauthAccessTokenDAO;
import cn.oauth.open.dao.OauthClientDetailsDAO;
import cn.oauth.open.util.CookieUtil;
import cn.oauth.open.util.EncryptUtil;
import cn.oauth.open.util.IPUtil;
import cn.oauth.open.util.JsonUtils;
import cn.oauth.open.util.StringUtil;
import cn.oauth.open.vo.OauthAccessTokenVO;
import cn.oauth.open.vo.OauthClientDetailsVO;

@Controller
@RequestMapping("/oauth2")
public class OauthAction {

	@Autowired
	private OauthClientDetailsDAO ocdDAO;

	@Autowired
	private OauthAccessTokenDAO oatDAO;

	@Autowired
	private OauthAccessLogDAO logDAO;

	/**
	 * 链入的方法
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/authorize", method = RequestMethod.GET)
	public String authorize(@RequestParam(value = "client_id", defaultValue = "", required = true) String clientId,
			@RequestParam(value = "redirect_uri", defaultValue = "", required = true) String redirectUri,
			@RequestParam(value = "grant_type", defaultValue = "", required = false) String grantType,
			@RequestParam(value = "client", defaultValue = "", required = false) String client,
			HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) throws IOException {

		/** 先检查参数，再查询DB，避免不必要的db开销，直接回调页面 */
		if (StringUtil.isNullOrBlank(clientId) || StringUtil.isNullOrBlank(redirectUri)) {
			model.put("error", "对不起，请求参数不能为空");
			return "/oauth/error.jsp";
		}

		OauthClientDetailsVO ocdOutputVO = ocdDAO.getClientDetails(clientId);
		if (null == ocdOutputVO || ocdOutputVO.getStatus() != OAUTH_CLIENT_DETAILS.STATUS.ON) {
			model.put("error", "对不起，该网站尚未开通xx账号登录");
			return "/oauth/error.jsp";
		}

		String UrlCallback = URLDecoder.decode(redirectUri, "utf-8");
		String UrlCallbackDomain = StringUtil.getRootDomain(UrlCallback);
		String clientUri = StringUtil.getRootDomain(ocdOutputVO.getClient_uri());
		if (!UrlCallbackDomain.equals(clientUri)) {
			model.put("error", "对不起，你所在的站点在xx认证失败，请联系<a href=\"" + ocdOutputVO.getClient_uri() + "\">"
					+ ocdOutputVO.getClient_name() + "</a>");
			return "/oauth/error.jsp";
		}

		// 传递首页、应用名
		model.put("clientUri", ocdOutputVO.getClient_uri());
		model.put("clientName", ocdOutputVO.getClient_name());

		// 用户在线判断
		boolean isOnline = true;//TODO 可以从request中分析是否在线，具体取决于你的项目，不在线则先跳转到登录再跳回来。
		model.put("online", isOnline);
		if (isOnline) {
			/** 经过在线校验的用户信息是可靠的，用户名也直接从cookie中取 */
			Cookie[] cookies = request.getCookies();
			Integer userId = Integer.parseInt(CookieUtil.getCookieValue(cookies, "user", "id"));
			String userName = CookieUtil.getCookieValue(cookies, "user", "w");
			String ssoWsid = CookieUtil.getCookieValue(cookies, "sso", "wsid");

			model.put("userId", userId);
			model.put("userName", userName);

			String openId = CookieUtil.getCookieValue(cookies, "open_id");
			
			// TODO 直接通过无需点击，自行实现
			if("0".equals(clientId)){
				grantType = OAUTH_ACCESS_TOKEN.TYPE.AUTHOR;
			}

			if (String.valueOf(userId).equals(openId) || OAUTH_ACCESS_TOKEN.TYPE.AUTHOR.equalsIgnoreCase(grantType)) {
				/** 直接传递第三方密码，节省db开销 */
				String rad = String.valueOf(new Random().nextInt(99999));
				/** 同一个应用、回调地址、用户缓存一个临时code redirectUri解码用于匹配 */
				String code = EncryptUtil.encryptMD5(clientId + ocdOutputVO.getClient_secret()
						+ URLDecoder.decode(redirectUri, "utf-8") + userId + rad, "UTF-8");
				CodeCacheVo vo = new CodeCacheVo();
				vo.setUserId(userId);
				vo.setUserName(userName);
				vo.setRad(rad);
				if (CodeCacheService.getInstance().set(clientId + code, vo)) {
					// 写入cookie
					Cookie cookie = new Cookie("open_id", String.valueOf(userId));
					cookie.setMaxAge(-1);
					cookie.setPath("/");
					response.addCookie(cookie);

					// 获取成功写日志
					logDAO.writeLog(userId, userName, clientId, IPUtil.getUserIP(request), ssoWsid);

					/** 外链的页面跳转重定向，RedirectView */
					model.clear();
					return "redirect:" + UrlCallback + (UrlCallback.indexOf("?") > -1 ? "&" : "?") + "code=" + code;
				} else {
					model.put("error", "授权失败，请重试");
				}
			}
		}

		/** 请求code但找不到在线用户时，给错误提示 */
		if (isOnline == false && OAUTH_ACCESS_TOKEN.TYPE.AUTHOR.equals(grantType)) {
			model.put("error", "请先授权并登录您的xx账号");
		}

		String registerFailURL = request.getRequestURL() + "?" + request.getQueryString();
		// 如果request已经包含了grant_type，那么就不需要再添加了
		String registerSuccessURL = registerFailURL;
		if (!request.getQueryString().contains("grant_type=authorization_code")) {
			registerSuccessURL = registerFailURL + "&grant_type=" + OAUTH_ACCESS_TOKEN.TYPE.AUTHOR;
		}

		// 当前URL,登录失败返回地址
		model.put("returnURL", registerFailURL);
		// 登录成功返回地址
		model.put("forwarURL", registerSuccessURL);

		if ("0".equals(clientId)) {
			model.put("registerForwardURL", URLEncoder.encode(registerSuccessURL, "utf-8"));
		} else {
			model.put("registerForwardURL", URLEncoder.encode(registerFailURL, "utf-8"));
		}

		if ("touch".equalsIgnoreCase(client))
			return "/oauth/authorize_m.jsp";
		else
			return "/oauth/authorize.jsp";
	}

	/**
	 * 令牌发放方法
	 * 
	 * 服务端自动交互
	 * 
	 * @return
	 */
	@RequestMapping("/token")
	@ResponseBody
	public String token(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "client_id", defaultValue = "", required = true) String clientId,
			@RequestParam(value = "client_secret", defaultValue = "", required = true) String clientSecret,
			@RequestParam(value = "grant_type", defaultValue = "", required = true) String grantType,// 授权模式，在这里是指authorization_code
			@RequestParam(value = "redirect_uri", defaultValue = "", required = true) String redirectUri,
			@RequestParam(value = "code", defaultValue = "", required = true) String code) throws Exception {

		// 判断参数
		if (StringUtil.isNullOrBlank(clientId) || StringUtil.isNullOrBlank(clientSecret)
				|| StringUtil.isNullOrBlank(redirectUri) || StringUtil.isNullOrBlank(code)) {
			return JsonUtils.getFailedResponse("-1", "参数不对");
		}

		/** 判断授权方式是否合法 */
		if (!OAUTH_ACCESS_TOKEN.TYPE.AUTHOR.equals(grantType)) {
			return JsonUtils.getFailedResponse("-1", "暂时只支持authorization_code授权");
		}

		/** 取缓存对象，检查code是否有效 */
		CodeCacheVo vo = CodeCacheService.getInstance().get(clientId + code);
		if (vo == null)
			return JsonUtils.getFailedResponse("-2", "code失效或者已过期");

		/** 检查第三方请求的秘钥和返回地址是否都匹配 redirectUri解码用于匹配 */
		String checkCode = EncryptUtil.encryptMD5(clientId + clientSecret + URLDecoder.decode(redirectUri, "utf-8")
				+ vo.getUserId() + vo.getRad(), "UTF-8");
		if (!code.equals(checkCode))
			return JsonUtils.getFailedResponse("-2", "第三方应用检查不通过");

		/** 清理缓存对象 */
		CodeCacheService.getInstance().delete(clientId + code);
		Integer userId = vo.getUserId();

		long currentTime = System.currentTimeMillis();
		OauthAccessTokenVO tokenVo = oatDAO.queryByUserId(clientId, userId);
		if (tokenVo != null) {
			// 检查token是否过期
			if (tokenVo.getAvailable_time().getTime() > currentTime
					&& tokenVo.getStatus() == OAUTH_ACCESS_TOKEN.STATUS.ON) {
				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("tokenId", tokenVo.getToken_id());
				resultMap.put("expires", tokenVo.getAvailable_time());
				return JsonUtils.getSuccessResponse("1", "令牌发放成功", resultMap);
			}
		}

		long availableTime = currentTime + OAUTH_ACCESS_TOKEN.TIME;
		String tokenId = EncryptUtil.encryptMD5(clientId + userId + availableTime + code, "UTF-8");

		OauthAccessTokenVO oatInputVO = new OauthAccessTokenVO();
		oatInputVO.setUser_id(userId);
		oatInputVO.setUser_name(vo.getUserName());
		oatInputVO.setClient_id(clientId);
		oatInputVO.setToken_id(tokenId);
		oatInputVO.setAvailable_time(new Date(availableTime));
		oatInputVO.setStatus(OAUTH_ACCESS_TOKEN.STATUS.ON);
		if (!oatDAO.createToken(oatInputVO)) {
			return JsonUtils.getFailedResponse("-3", "服务器出错，请稍后再试");
		}

		/**
		 * 返回的参数包括 1.tokenId 2.expires
		 */
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("tokenId", tokenId);
		resultMap.put("expires", availableTime);
		return JsonUtils.getSuccessResponse("1", "令牌发放成功", resultMap);
	}

	/**
	 * 开放用户信息方法
	 * 
	 * 服务端自动交互
	 * 
	 * @return
	 */
	@RequestMapping("/user/info")
	@ResponseBody
	public String userInfo(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "token", defaultValue = "", required = true) String token,
			@RequestParam(value = "client_id", defaultValue = "", required = true) String clientId) {

		// 判断参数
		if (StringUtil.isNullOrBlank(clientId) || StringUtil.isNullOrBlank(token)) {
			return JsonUtils.getFailedResponse("-1", "参数不对");
		}

		OauthAccessTokenVO oatOutputVO = oatDAO.queryByToken(clientId, token);
		// 校验token
		if (oatOutputVO == null || oatOutputVO.getAvailable_time().getTime() < System.currentTimeMillis()
				|| oatOutputVO.getStatus() != OAUTH_ACCESS_TOKEN.STATUS.ON) {
			return JsonUtils.getFailedResponse("-2", "token不存在或者已经失效");
		}

		/**
		 * 获取用户信息并返回
		 */
		int userId = oatOutputVO.getUser_id();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> userMap = UserClient.getUserInfoByUserId(userId);
		resultMap.put("data", userMap);

		return JsonUtils.getSuccessResponse("1", "获取用户信息成功", resultMap);
	}

}
