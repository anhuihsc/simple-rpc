package com.simple.rpc.core.transport.netty;

import com.simple.rpc.core.transport.InFlightRequests;
import com.simple.rpc.core.transport.ResponseFuture;
import com.simple.rpc.core.transport.command.Command;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author wansong
 * @date 2021/7/19
 */
@ChannelHandler.Sharable
public class ResponseInvocation extends SimpleChannelInboundHandler<Command> {
    private final InFlightRequests inFlightRequests;

    ResponseInvocation(InFlightRequests inFlightRequests) {
        this.inFlightRequests = inFlightRequests;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Command response) {
        ResponseFuture future = inFlightRequests.remove(response.getHeader().getRequestId());
        if(null != future) {
            future.getFuture().complete(response);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        Channel channel = ctx.channel();
        if(channel.isActive())ctx.close();
    }
}
