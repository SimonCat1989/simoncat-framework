package com.simoncat.framework.redis;

import java.util.Map;
import java.util.concurrent.Callable;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.cache.Cache;

import com.google.common.collect.Maps;

@RequiredArgsConstructor
public class RedisCache implements Cache {

    @NonNull
    private final String cacheName;
    
    private final Map<Object, Object> localCache = Maps.newConcurrentMap();

    @Override
    public String getName() {
        return this.cacheName;
    }

    @Override
    public Object getNativeCache() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ValueWrapper get(Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(Object key, Class<T> type) {
        return (T) localCache.get(key);
    }

    @Override
    public void put(Object key, Object value) {
        this.localCache.put(key, value);
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void evict(Object key) {
        // TODO Auto-generated method stub

    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub

    }

	@Override
	public <T> T get(Object key, Callable<T> valueLoader) {
		// TODO Auto-generated method stub
		return null;
	}

}
