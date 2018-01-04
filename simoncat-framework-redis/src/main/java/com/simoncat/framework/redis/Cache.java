package com.simoncat.framework.redis;

public interface Cache<T> {

   T get(String key); 
   
   void put(String key, T value);
}
