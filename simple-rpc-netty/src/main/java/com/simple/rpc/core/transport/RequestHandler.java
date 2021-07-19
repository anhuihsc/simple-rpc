package com.simple.rpc.core.transport;

import com.simple.rpc.api.spi.ServiceSupport;
import com.simple.rpc.core.transport.command.Command;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wansong
 * @date 2021/7/19
 */
public interface RequestHandler {
    /**
     * 处理请求
     * @param requestCommand 请求命令
     * @return 响应命令
     */
    Command handle(Command requestCommand);

    /**
     * 支持的请求类型
     */
    int type();
}