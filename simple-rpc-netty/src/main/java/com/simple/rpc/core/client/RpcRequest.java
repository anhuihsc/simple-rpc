package com.simple.rpc.core.client;

/**
 * @author wansong
 * @date 2021/7/24
 */
public class RpcRequest {
    private String interfaceName;
    private String methodName;
    private byte [] serializedArguments;

    public RpcRequest(String interfaceName, String methodName, byte[] serializedArguments) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.serializedArguments = serializedArguments;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public byte[] getSerializedArguments() {
        return serializedArguments;
    }
}
