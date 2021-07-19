package com.simple.rpc.core.transport;

import java.io.Closeable;
import java.net.SocketAddress;
import java.util.concurrent.TimeoutException;

/**
 * @author wansong
 * @date 2021/7/19
 */
public interface TransportClient extends Closeable{
    Transport createTransport(SocketAddress address, long connectionTimeout) throws InterruptedException, TimeoutException;
    @Override
    void close();
}
