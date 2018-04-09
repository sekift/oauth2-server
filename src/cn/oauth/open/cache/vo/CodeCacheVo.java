package cn.oauth.open.cache.vo;

import java.io.Serializable;

/**
 * 第三方授权临时code的缓存对象
 */

public class CodeCacheVo implements Serializable {
	
	private static final long serialVersionUID = 3004571311769108650L;
	private String rad;//随机数
	private Integer userId;//用户ID
	private String userName;//用户名
	
	@Override
	public String toString() {
		return "CodeCacheVo [rad=" + rad + ", userId=" + userId + ", userName="
				+ userName + "]";
	}
	public String getRad() {
		return rad;
	}
	public void setRad(String rad) {
		this.rad = rad;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
