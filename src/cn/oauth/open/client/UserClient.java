package cn.oauth.open.client;

import java.util.HashMap;
import java.util.Map;

public class UserClient {
	/**
	 * TODO 自行实现
	 * 
	 * @param userId
	 * @return
	 */
	public static String getUserNameByUserId(int userId) {
		if (userId <= 0)
			return null;
		return "username";
	}

	/**
	 * TODO 自行实现 
	 * 
	 * @param userId
	 * @return
	 */
	public static Map<String, Object> getUserInfoByUserId(int userId) {
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("user_id", userId);
		map3.put("user_name", "username");
		map3.put("sex", "gender");// 性别
		return map3;
	}

	public static void main(String[] args) {
		System.out.println(getUserNameByUserId(5));
	}
}
