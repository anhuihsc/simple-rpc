package com.simple.rpc.core.transport.netty;

import com.simple.rpc.core.transport.command.Command;
import com.simple.rpc.core.transport.command.Header;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author wansong
 * @date 2021/7/18
 */
public abstract class CommandDecoder extends ByteToMessageDecoder {
    private final static int LENGTH_FIELD_LENGTH = Integer.BYTES;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (!byteBuf.isReadable(LENGTH_FIELD_LENGTH)) {
            return;
        }
        byteBuf.markReaderIndex();
        int len = byteBuf.readInt() - LENGTH_FIELD_LENGTH;
        if (byteBuf.readableBytes() < len) {
            return;
        }
        Header header = decodeHeader(channelHandlerContext, byteBuf);
        int payloadLen = len - header.length();
        byte[] payload = new byte[payloadLen];
        byteBuf.readBytes(payload);
        list.add(new Command(header, payload));

    }

    protected abstract Header decodeHeader(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf);

}
