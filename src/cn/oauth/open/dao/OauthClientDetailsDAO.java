package cn.oauth.open.dao;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.springframework.stereotype.Component;

import cn.oauth.open.constants.Constants;
import cn.oauth.open.vo.OauthClientDetailsVO;

@Component
public class OauthClientDetailsDAO {
	/**
	 * 获取应用信息
	 * 
	 * @param clientId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public OauthClientDetailsVO getClientDetails(String clientId) {
		String sql = "SELECT * FROM oauth_client_details WHERE client_id=? limit 1";
		return (OauthClientDetailsVO) DBOperate.queryQuietly(Constants.OAUTH_DB_SLAVE, sql, new BeanHandler(
				OauthClientDetailsVO.class), clientId);
	}

}
