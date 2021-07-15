package com.simple.rpc.api;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;

/**
 * @author wansong
 * @date 2021/7/14
 */
public interface NameService {

    Collection<String> supportedSchemes();

    void connect(URI nameServiceUri);

    void registerService(String serviceName, URI uri) throws IOException;

    URI lookService(String serviceName);

}
