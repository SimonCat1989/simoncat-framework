package com.simoncat.framework.synclock;

/**
 * Define the synchronized locker API.
 * 
 * @author Simon LUO
 * @see https://my.oschina.net/dengfuwei/blog/1600681
 */
public interface SyncLocker {

    public static final long TIMEOUT_MILLIS = 30_000;

    public static final int RETRY_TIMES = Integer.MAX_VALUE;

    public static final long SLEEP_MILLIS = 500;

    boolean lock(String key, long expire, int retryTimes, long sleepMillis);

    boolean releaseLock(String key);

    default boolean lock(String key) {
        return lock(key, TIMEOUT_MILLIS, RETRY_TIMES, SLEEP_MILLIS);
    };

    default boolean lock(String key, int retryTimes) {
        return lock(key, TIMEOUT_MILLIS, retryTimes, SLEEP_MILLIS);
    };

    default boolean lock(String key, int retryTimes, long sleepMillis) {
        return lock(key, TIMEOUT_MILLIS, retryTimes, sleepMillis);
    };

    default boolean lock(String key, long expire) {
        return lock(key, expire, RETRY_TIMES, SLEEP_MILLIS);
    };

    default boolean lock(String key, long expire, int retryTimes) {
        return lock(key, expire, retryTimes, SLEEP_MILLIS);
    };
}
