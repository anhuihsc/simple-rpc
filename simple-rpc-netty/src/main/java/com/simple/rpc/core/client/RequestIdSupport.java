package com.simple.rpc.core.client;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wansong
 * @date 2021/7/25
 */
public class RequestIdSupport {
    private final static AtomicInteger nextRequestId = new AtomicInteger(0);
    public static int next() {
        return nextRequestId.getAndIncrement();
    }
}
