package cn.oauth.open.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.oauth.open.constants.Constants;
import cn.oauth.open.vo.OauthAccessTokenVO;

@Component
public class OauthAccessTokenDAO {

	private static Logger logger = Logger.getLogger(OauthAccessTokenDAO.class);

	/**
	 * 创建令牌
	 * 
	 * @param vo
	 * @return
	 */
	public boolean createToken(OauthAccessTokenVO vo) {
		String sql = "INSERT INTO oauth_access_token(user_id,user_name,client_id,token_id,create_time,available_time,update_time,status) "
				+ "VALUES(?,?,?,?,now(),?,now(),?) "
				+ "ON DUPLICATE KEY UPDATE token_id=?,available_time=?,update_time=now(),status=?";
		List<Object> params = new ArrayList<Object>();
		params.add(vo.getUser_id());
		params.add(vo.getUser_name());
		params.add(vo.getClient_id());
		params.add(vo.getToken_id());
		params.add(vo.getAvailable_time());
		params.add(vo.getStatus());
		params.add(vo.getToken_id());
		params.add(vo.getAvailable_time());
		params.add(vo.getStatus());
		return DBOperate.updateQuietly(Constants.OAUTH_DB_MASTER, sql, params.toArray()) > 0;
	}

	/**
	 * 删除用户身份认证记录
	 */
	public boolean deleteToken(int userId, String clientId) {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "UPDATE oauth_access_token SET status=" + Constants.OAUTH_ACCESS_TOKEN.STATUS.DELETE
				+ " WHERE user_id=? AND client_id=?";
		conn = ProxoolConnection.getConnectProfile(Constants.OAUTH_DB_MASTER).getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setString(2, clientId);
			int ret = ps.executeUpdate();
			if (ret == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[oauth2]删除用户当前应用授权出错", e);
		} finally {
			DbUtils.closeQuietly(conn, ps, rs);
		}
		return false;
	}

	/**
	 * 修改令牌信息
	 * 
	 * @param vo
	 * @return
	 */
	public boolean updateToken(OauthAccessTokenVO vo) {
		String sql = "UPDATE oauth_access_token SET token_id=?,available_time=?,update_time=now(),status=?,memo=? WHERE user_id=? AND client_id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(vo.getToken_id());
		params.add(vo.getAvailable_time());
		params.add(vo.getStatus());
		params.add(vo.getClient_id());
		params.add(vo.getUser_id());
		return DBOperate.updateQuietly(Constants.OAUTH_DB_MASTER, sql, params) > 0;
	}

	/**
	 * 查询令牌信息
	 * 
	 * @param inputVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public OauthAccessTokenVO queryByUserId(String clientId, int userId) {

		/** sql使用传参的方式，不使用拼接的方式，避免sql注入 */
		String sql = "SELECT * FROM oauth_access_token where client_id=? and user_id=? limit 1";
		return (OauthAccessTokenVO) DBOperate.queryQuietly(Constants.OAUTH_DB_SLAVE, sql, new BeanHandler(
				OauthAccessTokenVO.class), clientId, userId);
	}

	@SuppressWarnings("unchecked")
	public OauthAccessTokenVO queryByToken(String clientId, String token) {

		String sql = "SELECT * FROM oauth_access_token where client_id=? and token_id=? limit 1";
		return (OauthAccessTokenVO) DBOperate.queryQuietly(Constants.OAUTH_DB_SLAVE, sql, new BeanHandler(
				OauthAccessTokenVO.class), clientId, token);
	}
}
