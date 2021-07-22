package com.simple.rpc.core;

import com.simple.rpc.api.RpcAccessPoint;
import com.simple.rpc.api.spi.ServiceSupport;
import com.simple.rpc.core.server.ServiceProviderRegistry;
import com.simple.rpc.core.transport.RequestHandlerRegistry;
import com.simple.rpc.core.transport.Transport;
import com.simple.rpc.core.transport.TransportClient;
import com.simple.rpc.core.transport.TransportServer;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wansong
 * @date 2021/7/14
 */
public class NettyRpcAccessPoint implements RpcAccessPoint {
    private String host = "localhost";
    private final int port = 9999;
    private final URI uri = URI.create("rpc://" + host + ":" + port);
    private TransportServer server = null;
    private TransportClient client = ServiceSupport.load(TransportClient.class);
    private final Map<URI, Transport> clientMap = new ConcurrentHashMap<>();
    private final ServiceProviderRegistry serviceProviderRegistry = ServiceSupport.load(ServiceProviderRegistry.class);


    @Override
    public <T> T getRemoteService(URI uri, Class<T> serviceClass) {
        return null;
    }

    private String reverse(String str) {
        StringBuilder rev = new StringBuilder(str.length());
        for (int i = str.length() - 1; i >= 0; i--)
            rev.append(str.charAt(i));
        return rev.toString();
    }

    @Override
    public <T> URI addServiceProvider(T service, Class<T> serviceClass) {
        serviceProviderRegistry.addServiceProvider(serviceClass, service);
        return null;
    }

    @Override
    public Closeable startServer() throws Exception {
        if (null == server) {
            server = ServiceSupport.load(TransportServer.class);
            server.start(RequestHandlerRegistry.getInstance(), port);
        }
        return () -> {
            if (null != server) {
                server.stop();
            }
        };
    }

    @Override
    public void close() throws IOException {
        if (null != server) {
            server.stop();
        }
        client.close();
    }
}
