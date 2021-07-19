package com.simple.rpc.core.transport.netty;

import com.simple.rpc.core.transport.InFlightRequests;
import com.simple.rpc.core.transport.RequestHandler;
import com.simple.rpc.core.transport.RequestHandlerRegistry;
import com.simple.rpc.core.transport.command.Command;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author wansong
 * @date 2021/7/19
 */
public class RequestInvocation extends SimpleChannelInboundHandler<Command> {

    private final RequestHandlerRegistry requestHandlerRegistry;

    RequestInvocation(RequestHandlerRegistry requestHandlerRegistry) {
        this.requestHandlerRegistry = requestHandlerRegistry;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Command request) throws Exception {
        RequestHandler handler = requestHandlerRegistry.get(request.getHeader().getType());
        if (null != handler) {
            Command response = handler.handle(request);
            if (null != response) {
                channelHandlerContext.writeAndFlush(response).addListener((ChannelFutureListener) channelFuture -> {
                    if (!channelFuture.isSuccess()) {
                        channelHandlerContext.channel().close();
                    }
                });
            }
        } else {
            throw new Exception(String.format("No handler for request with type: %d!", request.getHeader().getType()));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        Channel channel = ctx.channel();
        if (channel.isActive()) ctx.close();
    }
}
