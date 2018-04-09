package cn.oauth.open.cache;

import cn.oauth.open.cache.vo.CodeCacheVo;
import cn.oauth.open.constants.Constants;

/**
 * 内存缓存
 * 
 * code临时缓存服务，key为clientId + code
 */
public class CodeCacheService {
	private static CodeCacheService instance = new CodeCacheService();

	private CodeCacheService() {
	}

	public static CodeCacheService getInstance() {
		return instance;
	}

	public boolean set(String key, CodeCacheVo vo) {
		Cache cache = new Cache();
		cache.setKey(key);
		cache.setValue(vo);
		cache.setCreateTime(System.currentTimeMillis());
		cache.setCacheTime(Constants.CACHE_TIME);

		CacheManager.getInstance().putCache(Constants.CACHE_CODE + key, cache);
		return true;
	}

	public CodeCacheVo get(String key) {
		Cache cache = CacheManager.getInstance().getCacheInfo(Constants.CACHE_CODE + key);
		CodeCacheVo vo = null;
		if (null != cache) {
			long now = System.currentTimeMillis();
			long createTime = cache.getCreateTime();
			long cacheTime = cache.getCacheTime();
			// 判断缓存是否过期
			if (now - createTime < cacheTime) {
				vo = (CodeCacheVo) cache.getValue();
			} else {
				// 过期删除缓存
				delete(key);
			}
		}
		return vo;
	}

	public boolean delete(String key) {
		CacheManager.getInstance().clearOnly(Constants.CACHE_CODE + key);
		return true;
	}

}
