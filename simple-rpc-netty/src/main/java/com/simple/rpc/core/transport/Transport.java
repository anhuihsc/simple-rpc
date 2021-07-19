package com.simple.rpc.core.transport;

import com.simple.rpc.core.transport.command.Command;

import java.util.concurrent.CompletableFuture;

/**
 * @author wansong
 * @date 2021/7/15
 */
public interface Transport {
    /**
     * 发送请求命令
     * @param request 请求命令
     * @return 返回值是一个Future，Future
     */
    CompletableFuture<Command> send(Command request);
}
