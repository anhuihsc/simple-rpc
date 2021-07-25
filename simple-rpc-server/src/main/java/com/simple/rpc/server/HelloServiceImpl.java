package com.simple.rpc.server;

import com.simple.rpc.HelloService;

/**
 * @author wansong
 * @date 2021/7/25
 */
public class HelloServiceImpl implements HelloService {
    public String sayHello(String say) {
        System.out.println(say);
        return say + "hello";
    }
}
