package cn.oauth.open.cache;

import cn.oauth.open.cache.vo.CodeCacheVo;

public class Cache {
	private String key;// 缓存ID
	private CodeCacheVo value;// 缓存数据
	private long createTime;// 缓存开始时间
	private long cacheTime;// 缓存时间

	public Cache() {
		super();
	}

	public Cache(String key, CodeCacheVo value, long createTime) {
		this.key = key;
		this.value = value;
		this.createTime = createTime;
	}

	public String getKey() {
		return key;
	}

	public long getCreateTime() {
		return createTime;
	}

	public Object getValue() {
		return value;
	}

	public void setKey(String string) {
		key = string;
	}

	public void setCreateTime(long l) {
		createTime = l;
	}

	public void setValue(CodeCacheVo vo) {
		value = vo;
	}
	public void setCacheTime(long cacheTime) {
		this.cacheTime = cacheTime;
	}

	public long getCacheTime() {
		return cacheTime;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("Cache [");
		sb.append("key=").append(key)
		  .append(",value=").append(value)
		  .append(",createTime=").append(createTime)
		  .append(",cacheTime=").append(cacheTime)
		  .append("]");
		return sb.toString();
	}
}