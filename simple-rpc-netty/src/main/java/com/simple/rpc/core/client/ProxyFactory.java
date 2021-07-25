package com.simple.rpc.core.client;

import com.simple.rpc.core.transport.Transport;

/**
 * @author wansong
 * @date 2021/7/24
 */
public interface ProxyFactory {

    <T> T geProxy(Transport transport, Class<T> serviceClass);
}
