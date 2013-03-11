package com.agnie.common.cache;

public interface CacheService {
	void put(String key, Object object);

	void put(String key, Object object, Integer expTime);

	Object get(String key);
}
