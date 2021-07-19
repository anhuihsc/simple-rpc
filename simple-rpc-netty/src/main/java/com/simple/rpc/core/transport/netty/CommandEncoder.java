package com.simple.rpc.core.transport.netty;

import com.simple.rpc.core.transport.command.Command;
import com.simple.rpc.core.transport.command.Header;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author wansong
 * @date 2021/7/19
 */
public class CommandEncoder extends MessageToByteEncoder {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        if (!(o instanceof Command)) {
            throw new Exception(String.format("Unknown type: %s!", o.getClass().getCanonicalName()));
        }
        Command command = (Command) o;
        byteBuf.writeInt(Integer.BYTES + command.getHeader().length() + command.getPayload().length);
        encodeHeader(channelHandlerContext, command.getHeader(), byteBuf);
        byteBuf.writeBytes(command.getPayload());


    }

    protected void encodeHeader(ChannelHandlerContext channelHandlerContext, Header header, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(header.getType());
        byteBuf.writeInt(header.getVersion());
        byteBuf.writeInt(header.getRequestId());
    }
}
