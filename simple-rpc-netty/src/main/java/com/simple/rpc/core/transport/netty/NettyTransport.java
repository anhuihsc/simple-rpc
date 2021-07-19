package com.simple.rpc.core.transport.netty;

import com.simple.rpc.core.transport.InFlightRequests;
import com.simple.rpc.core.transport.Transport;
import com.simple.rpc.core.transport.command.Command;
import io.netty.channel.Channel;

import java.util.concurrent.CompletableFuture;


/**
 * @author wansong
 * @date 2021/7/15
 */
public class NettyTransport implements Transport {
    private Channel channel;
    private InFlightRequests inFlightRequests;

    NettyTransport(Channel channel, InFlightRequests inFlightRequests) {
        this.channel = channel;
        this.inFlightRequests = inFlightRequests;
    }
    @Override
    public CompletableFuture<Command> send(Command request) {
        return null;
    }

}
