package cn.oauth.open.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 提供跟IP相关的一些处理方法。
 * 
 */
public class IPUtil {

	/**
	 * 获取用户终端的IP地址
	 * 
	 * @param request
	 *            http请求
	 * @return
	 */
	public static String getUserIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		} else {
			String[] ips = ip.split(",");
			for (int i = 0; i < ips.length; i++) {
				ip = ips[i].trim();
				if (!(ip.startsWith("10.") || ip.startsWith("192.168") || ip.startsWith("172.16.")
						|| "".equals(ip) || ip.equalsIgnoreCase("unknown"))) {
					return ip.trim();
				}
				ip = null;
			}
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		} else {
			return ip.trim();
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return ip.trim();
	}

}
