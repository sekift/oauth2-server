package cn.oauth.open.util;

import org.apache.log4j.Logger;

import cn.oauth.open.config.XmlProperties;

/**
 * 配置工具类
 */
public class ConfigUtil {

	/**
	 * 日志实例
	 */
	private static final Logger logger = Logger.getLogger(ConfigUtil.class);

	/**
	 * 应用配置
	 */
	private static XmlProperties appConfig = null;

	/**
	 * 初始化操作
	 */
	static {

		// 加载应用配置数据
		appConfig = new XmlProperties();
		appConfig.setSourceURL(ConfigUtil.class
				.getResource("/config/app_config.xml"));
		appConfig.setTimingReload(true);
		appConfig.initialize();
	}

	/**
	 * 获取应用配置值
	 * 
	 * @param key
	 * @return
	 */
	public static Object getConfigValue(String key) {
		Object result = null;
		try {
			if (appConfig != null) {
				result = appConfig.getValue(key);
			}
		} catch (Exception e) {
			logger.error("获取应用配置值出错！", e);
		}
		return result;
	}
}


