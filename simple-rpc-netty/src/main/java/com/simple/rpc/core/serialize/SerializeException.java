package com.simple.rpc.core.serialize;

/**
 * @author wansong
 * @date 2021/7/15
 */
public class SerializeException extends RuntimeException {

    public SerializeException(String msg) {
        super(msg);
    }
    public SerializeException(Throwable throwable){ super(throwable);}
}
