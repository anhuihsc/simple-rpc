package com.simple.rpc.core.server;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wansong
 * @date 2021/7/22
 */
public class RpcRequestHandler implements ServiceProviderRegistry{
    private Map<String/*service name*/, Object/*service provider*/> serviceProviders = new HashMap<>();
    @Override
    public <T> void addServiceProvider(Class<? extends T> serviceClass, T serviceProvider) {
        serviceProviders.put(serviceClass.getCanonicalName(),serviceProvider);
    }
}
