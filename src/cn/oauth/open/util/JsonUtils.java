package cn.oauth.open.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtils {

	/**
	 * 构造执行失败响应
	 * 
	 * @param code
	 * @param message
	 * @return
	 */
	public static String getFailedResponse(String code, String message) {

		String response = null;

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> responseMap = new HashMap<String, Object>();
			responseMap.put("success", 0);
			responseMap.put("code", code);
			responseMap.put("message", message);
			responseMap.put("data", map);
			response = JsonUtil.toJson(responseMap);
		} catch (Exception e) {
		}

		return response;
	}

	/**
	 * 构造执行成功响应
	 * 
	 * @param code
	 * @param message
	 * @return
	 */
	public static String getSuccessResponse(String code, String message) {

		Map<String, Object> map = new HashMap<String, Object>();
		return getSuccessResponse(code, message, map);
	}

	/**
	 * 构造执行成功响应
	 * 
	 * @param code
	 * @param message
	 * @param list
	 * @return
	 */
	public static String getSuccessResponse(String code, String message,
			List<Object> list) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return getSuccessResponse(code, message, map);
	}

	/**
	 * 构造执行成功响应
	 * 
	 * @param code
	 * @param message
	 * @param map
	 * @return
	 */
	public static String getSuccessResponse(String code, String message, Map<String, Object> map) {

		String response = null;

		try {
			Map<String, Object> responseMap = new HashMap<String, Object>();
			responseMap.put("success", 1);
			responseMap.put("code", code);
			responseMap.put("message", message);
			responseMap.put("data", map);
			response = JsonUtil.toJson(responseMap);
		} catch (Exception e) {
		}

		return response;
	}

	/**
	 * 构造执行成功响应
	 * 
	 * @param code
	 * @param message
	 * @param map
	 * @param list
	 * @return
	 */
	public static String getSuccessResponse(String code, String message,
			Map<String, Object> map, List<Object> list) {
		map.put("list", list);
		return getSuccessResponse(code, message, map);
	}
	
	/**
	 * 把json字符串转换为Map
	 * @param request
	 * @return
	 */
	public static Map<String, Object> getRequestMap(String request){
		@SuppressWarnings("unchecked")
		Map<String, Object> requestMap = JsonUtil.toBean(request, HashMap.class);
		return requestMap;
	}
}
