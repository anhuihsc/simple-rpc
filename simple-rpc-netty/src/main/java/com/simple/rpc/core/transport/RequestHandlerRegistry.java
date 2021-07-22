package com.simple.rpc.core.transport;

import com.simple.rpc.api.spi.ServiceSupport;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wansong
 * @date 2021/7/19
 */
public class RequestHandlerRegistry {

    private Map<Integer, RequestHandler> handlerMap = new HashMap<>();
    private static RequestHandlerRegistry instance = new RequestHandlerRegistry();

    public static RequestHandlerRegistry getInstance() {
        return instance;
    }


    private RequestHandlerRegistry() {
        Collection<RequestHandler> requestHandlers = ServiceSupport.loadAll(RequestHandler.class);
        for (RequestHandler requestHandler : requestHandlers) {
            handlerMap.put(requestHandler.type(), requestHandler);
        }
    }


    public RequestHandler get(int type) {
        return handlerMap.get(type);
    }
}
