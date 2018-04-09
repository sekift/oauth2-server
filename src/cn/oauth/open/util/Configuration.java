package cn.oauth.open.util;

import java.util.ResourceBundle;

public class Configuration {
	

	private static Object lock              = new Object();
	private static Configuration config     = null;
	private static ResourceBundle rb        = null;
	private static final String CONFIG_FILE = "config/user";
	
	private Configuration() {
		rb = ResourceBundle.getBundle(CONFIG_FILE);
	}
	
	public static Configuration getInstance() {
		synchronized(lock) {
			if(null == config) {
				config = new Configuration();
			}
		}
		return (config);
	}
	
	@SuppressWarnings("static-access")
	public static void reload(){
		rb.clearCache();
		config = new Configuration();
	}
	
	public String getValue(String key){
		return (rb.getString(key));
	}
	
	public String getValue(String key, String defaultValue){
		String value = null;
		try{
			value = rb.getString(key);
		}catch(Exception e) {
		}
		return (value==null) ? defaultValue : value;
	}
}
