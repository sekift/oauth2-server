package cn.oauth.open.cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存这里只是做一个例子，可以自行使用其他缓存工具进行替代。
 *  
 * TODO 有关缓存的自动删除可以自行使用定时器实现，在此省略。
 * 
 * @version:1.0
 */
public class CacheManager {
	private static ConcurrentHashMap<String, Cache> cacheMap = new ConcurrentHashMap<String, Cache>();

    private volatile static CacheManager instance;

    public static CacheManager getInstance() {
        if (instance == null) {
            synchronized (CacheManager.class) {
                if (instance == null)
                    instance = new CacheManager();
            }
        }
        return instance;
    }
    
	// 得到缓存
	private Cache getCache(String key) {
		return (Cache) cacheMap.get(key);
	}

	// 判断是否存在一个缓存
	private boolean hasCache(String key) {
		return cacheMap.containsKey(key);
	}
	
	// 清除指定的缓存
	public void clearOnly(String key) {
		cacheMap.remove(key);
	}

	// 载入缓存
	public void putCache(String key, Cache obj) {
		cacheMap.put(key, obj);
	}

	// 获取缓存信息
	public Cache getCacheInfo(String key) {
		if (hasCache(key)) {
			return getCache(key);
		} else
			return null;
	}

	// 获取缓存中的大小
	public int getCacheSize() {
		return cacheMap.size();
	}
}