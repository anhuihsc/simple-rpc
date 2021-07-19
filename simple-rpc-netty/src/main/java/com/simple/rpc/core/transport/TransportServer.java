package com.simple.rpc.core.transport;

/**
 * @author wansong
 * @date 2021/7/19
 */
public interface TransportServer {
    void start(RequestHandlerRegistry requestHandlerRegistry, int port) throws Exception;
    void stop();
}
