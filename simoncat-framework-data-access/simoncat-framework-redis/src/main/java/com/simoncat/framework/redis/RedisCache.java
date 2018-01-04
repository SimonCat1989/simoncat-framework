package com.simoncat.framework.redis;

import org.springframework.cache.Cache;

public class RedisCache implements Cache {


    @Override
    public String getName() {
        return null;
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

    @Override
    public <T> T get(Object key, Class<T> type) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        // TODO Auto-generated method stub

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

}
