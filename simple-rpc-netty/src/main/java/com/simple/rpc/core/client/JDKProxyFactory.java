package com.simple.rpc.core.client;

import com.simple.rpc.core.transport.Transport;

import java.lang.reflect.Proxy;

/**
 * @author wansong
 * @date 2021/7/24
 */
public class JDKProxyFactory implements ProxyFactory {
    @Override
    public <T> T geProxy(Transport transport, Class<T> serviceClass) {
        JDKProxyInvocationHandler proxyInvocationHandler = new JDKProxyInvocationHandler(transport);
        Class<?>[] interfaces = serviceClass.getClass().getInterfaces();
        return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(), interfaces, proxyInvocationHandler);
    }
}
