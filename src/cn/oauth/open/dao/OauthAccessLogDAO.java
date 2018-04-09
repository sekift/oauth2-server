package cn.oauth.open.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.oauth.open.constants.Constants;

/**
 * 记录用户授权日志
 */

@Component
public class OauthAccessLogDAO {
	private static Logger logger = Logger.getLogger(OauthAccessLogDAO.class);

	/**
	 * 记录日志 按照年月分表
	 * 
	 * @param vo
	 * @return
	 */
	public void writeLog(int userId, String userName, String clientId, String userIp, String ssoWsid) {
		String tableName = String.format("oauth_access_log_%1$tY%1$tm", new Date());
		String sql = "INSERT INTO " + tableName + "(user_id,user_name,client_id,create_time,user_ip,sso_wsid,memo)"
				+ " VALUES(?,?,?,now(),?,?,?) ";
		List<Object> params = new ArrayList<Object>();
		params.add(userId);
		params.add(userName);
		params.add(clientId);
		params.add(userIp);
		params.add(ssoWsid);
		params.add("");

		try {
			DBOperate.update(Constants.OAUTH_DB_MASTER, sql, params.toArray());
		} catch (Exception e) {
			logger.error("[oauth2]记录访问日志出错", e);
		}
	}
	
}
