package com.simple.rpc.server;

import com.simple.rpc.HelloService;
import com.simple.rpc.api.NameService;
import com.simple.rpc.api.RpcAccessPoint;
import com.simple.rpc.core.NettyRpcAccessPoint;

import java.io.Closeable;
import java.io.File;
import java.net.URI;

/**
 * @author wansong
 * @date 2021/7/25
 */
public class Server {
    public static void main(String [] args) throws Exception {

        String serviceName = HelloService.class.getCanonicalName();
        File tmpDirFile = new File(System.getProperty("java.io.tmpdir"));
        File file = new File(tmpDirFile, "simple_rpc_name_service.data");
        HelloService helloService = new HelloServiceImpl();
        try(RpcAccessPoint rpcAccessPoint = new NettyRpcAccessPoint();
            Closeable ignored = rpcAccessPoint.startServer()) {
            NameService nameService = rpcAccessPoint.getNameService(file.toURI());
            assert nameService != null;
            URI uri = rpcAccessPoint.addServiceProvider(helloService, HelloService.class);
            nameService.registerService(serviceName, uri);
            System.out.println("start success");
            //noinspection ResultOfMethodCallIgnored
            System.in.read();
        }
    }
}
