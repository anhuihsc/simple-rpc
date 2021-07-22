package com.simple.rpc.core.server;

/**
 * @author wansong
 * @date 2021/7/22
 */
public interface ServiceProviderRegistry {
    <T> void addServiceProvider(Class<? extends T> serviceClass, T serviceProvider);
}
