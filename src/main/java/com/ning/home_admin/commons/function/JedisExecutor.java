package com.ning.home_admin.commons.function;


import com.ning.home_admin.commons.exception.RedisConnectException;

/**
 * @author NKW
 */
@FunctionalInterface
public interface JedisExecutor<T, R> {
    R excute(T t) throws RedisConnectException;
}
