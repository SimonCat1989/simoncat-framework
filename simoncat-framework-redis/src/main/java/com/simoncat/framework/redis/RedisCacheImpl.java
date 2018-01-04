package com.simoncat.framework.redis;

import java.util.HashMap;
import java.util.Map;

public class RedisCacheImpl implements Cache<Object> {

    Map<String, Object> localCache = new HashMap<>();
    
    @Override
    public Object get(String key) {
        return localCache.get(key);
    }

    @Override
    public void put(String key, Object value) {
        localCache.put(key, value);
    }

}
