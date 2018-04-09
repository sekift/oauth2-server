package cn.oauth.open.vo;

import java.io.Serializable;
import java.util.Date;

public class OauthClientDetailsVO implements Serializable{
	
	private static final long serialVersionUID = -5331949616939365580L;
	
	private int id;		/*`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',*/
	private String client_id;	/*`client_id` varchar(256) NOT NULL DEFAULT '' COMMENT '客户端ID',*/
	
	private String client_name;  /*`client_name` varchar(100) NOT NULL DEFAULT '' COMMENT '客户端名称',*/
	private String client_uri;	/*  `client_uri` varchar(256) NOT NULL DEFAULT '' COMMENT '客户端首页',*/
	private String client_secret;	/* `client_secret` varchar(256) NOT NULL DEFAULT '' COMMENT '客户端秘钥',*/
	private String authorized_grant_types;	/*`authorized_grant_types` varchar(256) NOT NULL DEFAULT '' COMMENT '授权类型',*/
	private Date create_time;	/* `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',*/
	private Date update_time;	/* `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',*/
	private int status;		/* `status` tinyint(4) NOT NULL COMMENT '状态，1可用，-1已删除，0屏蔽',*/
	private String memo; 	/* `memo` varchar(256) DEFAULT '' COMMENT '附件信息',*/
	
	@Override
	public String toString() {
		return "OauthClientDetailsVO [authorized_grant_types="
				+ authorized_grant_types + ", client_id=" + client_id
				+ ", client_name=" + client_name + ", client_secret="
				+ client_secret + ", client_uri=" + client_uri
				+ ", create_time=" + create_time + ", id=" + id + ", memo="
				+ memo + ", status=" + status + ", update_time=" + update_time
				+ "]";
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date createTime) {
		create_time = createTime;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date updateTime) {
		update_time = updateTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String clientId) {
		client_id = clientId;
	}
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String clientName) {
		client_name = clientName;
	}
	public String getClient_uri() {
		return client_uri;
	}
	public void setClient_uri(String clientUri) {
		client_uri = clientUri;
	}
	public String getClient_secret() {
		return client_secret;
	}
	public void setClient_secret(String clientSecret) {
		client_secret = clientSecret;
	}
	public String getAuthorized_grant_types() {
		return authorized_grant_types;
	}
	public void setAuthorized_grant_types(String authorizedGrantTypes) {
		authorized_grant_types = authorizedGrantTypes;
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
}
