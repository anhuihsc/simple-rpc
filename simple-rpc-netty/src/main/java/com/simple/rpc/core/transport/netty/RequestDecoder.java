package com.simple.rpc.core.transport.netty;

import com.simple.rpc.core.transport.command.Header;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author wansong
 * @date 2021/7/19
 */
public class RequestDecoder extends CommandDecoder {
    @Override
    protected Header decodeHeader(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) {
        return new Header(
                byteBuf.readInt(),
                byteBuf.readInt(),
                byteBuf.readInt()
        );
    }
}
