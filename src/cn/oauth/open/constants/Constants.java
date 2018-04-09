package cn.oauth.open.constants;

/**
 * @ClassName: Constants
 * @Description:常量类
 * 
 */
public class Constants {
	// 主库
	public final static String OAUTH_DB_MASTER = "oauth_master";
	// 从库
	public final static String OAUTH_DB_SLAVE = "oauth_slave";

	// 缓存前缀
	public static final String CACHE_CODE = "cache_code_";

	// 缓存时间
	public static final long CACHE_TIME = 60 * 60 * 1000L;// 1小时

	// 应用详细信息表常数类
	public static final class OAUTH_CLIENT_DETAILS {

		// 状态常量
		public static final class STATUS {
			/**
			 * 屏蔽
			 */
			public static final int OFF = 0;

			/**
			 * 正常
			 */
			public static final int ON = 1;

			/**
			 * 删除
			 */
			public static final int DELETE = -1;
		}
	}

	// 令牌信息表常数类
	public static final class OAUTH_ACCESS_TOKEN {

		// 状态常量
		public static final class STATUS {
			/**
			 * 屏蔽
			 */
			public static final int OFF = 0;

			/**
			 * 正常
			 */
			public static final int ON = 1;

			/**
			 * 删除
			 */
			public static final int DELETE = -1;
		}

		// 授权方式常量
		public static final class TYPE {
			public static final String AUTHOR = "authorization_code";
		}

		/** accessToken的有效期为30天 */
		public static final Long TIME = 30 * 24 * 60 * 60 * 1000L;
	}
}
