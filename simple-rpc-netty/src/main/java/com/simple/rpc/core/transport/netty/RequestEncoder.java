package com.simple.rpc.core.transport.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author wansong
 * @date 2021/7/19
 */
public class RequestEncoder extends CommandEncoder {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        super.encode(channelHandlerContext, o, byteBuf);
    }
}
