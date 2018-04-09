package cn.oauth.open.config;

/**
 * properties object
 * 
 * @author
 * @date
 */
public interface Config {

	<T> T getItem(String name);

	<T> T getItem(String name, T defaultValue);

}