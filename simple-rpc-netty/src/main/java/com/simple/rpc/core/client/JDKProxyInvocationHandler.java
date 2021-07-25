package com.simple.rpc.core.client;

import com.simple.rpc.core.serialize.SerializeSupport;
import com.simple.rpc.core.transport.Transport;
import com.simple.rpc.core.transport.command.Code;
import com.simple.rpc.core.transport.command.Command;
import com.simple.rpc.core.transport.command.Header;
import com.simple.rpc.core.transport.command.ResponseHeader;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

/**
 * @author wansong
 * @date 2021/7/24
 */
public class JDKProxyInvocationHandler implements InvocationHandler {
    protected final Transport transport;

    public JDKProxyInvocationHandler(Transport transport) {
        this.transport = transport;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Header header = new Header(ServiceTypes.TYPE_RPC_REQUEST, 1, RequestIdSupport.next());
        byte[] bytes = SerializeSupport.serialize(args);
        RpcRequest rpcRequest = new RpcRequest(proxy.getClass().getCanonicalName(), method.getName(), bytes);
        byte[] payload = SerializeSupport.serialize(rpcRequest);
        Command requestCommand = new Command(header, payload);
        try {
            Command responseCommand = transport.send(requestCommand).get();
            ResponseHeader responseHeader = (ResponseHeader) responseCommand.getHeader();
            if (responseHeader.getCode() == Code.SUCCESS.getCode()) {
                return responseCommand.getPayload();
            } else {
                throw new Exception(responseHeader.getError());
            }

        } catch (ExecutionException e) {
            throw new RuntimeException(e.getCause());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
