package cn.oauth.open.vo;

import java.io.Serializable;
import java.util.Date;

public class OauthAccessTokenVO implements Serializable{

	private static final long serialVersionUID = 4530259044936656697L;
	
	private int user_id;		/*`user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',*/
	private String user_name;	/* `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名称',*/
	private String client_id;	/*`client_id` varchar(256) NOT NULL DEFAULT '' COMMENT '客户端id',*/
	private String token_id;		/*`token_id` varchar(256) NOT NULL DEFAULT '' COMMENT '令牌',*/
	private Date create_time;	/*`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',*/
	private Date available_time;		/*`available_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '有效时间',*/
	private Date update_time;	/*`update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',*/
	private int status;		/*`status` int(4) NOT NULL COMMENT '状态，1正常，-1删除，0屏蔽',*/
	private String memo;	/*`memo` varchar(256) DEFAULT '' COMMENT '附加信息',*/
	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int userId) {
		user_id = userId;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String userName) {
		user_name = userName;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String clientId) {
		client_id = clientId;
	}

	public String getToken_id() {
		return token_id;
	}

	public void setToken_id(String tokenId) {
		token_id = tokenId;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date createTime) {
		create_time = createTime;
	}

	public Date getAvailable_time() {
		return available_time;
	}

	public void setAvailable_time(Date availableTime) {
		available_time = availableTime;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date updateTime) {
		update_time = updateTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "OauthAccessTokenVO [available_time=" + available_time
				+ ", client_id=" + client_id + ", create_time=" + create_time
				+ ", memo=" + memo + ", status=" + status + ", token_id="
				+ token_id + ", update_time=" + update_time + ", user_id="
				+ user_id + ", user_name=" + user_name + "]";
	}
	
}
