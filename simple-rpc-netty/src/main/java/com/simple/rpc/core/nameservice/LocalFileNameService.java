package com.simple.rpc.core.nameservice;

import com.simple.rpc.api.NameService;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Collection;
import java.util.Collections;

/**
 * @author wansong
 * @date 2021/7/14
 */
public class LocalFileNameService implements NameService {
    private static Collection<String> schemes = Collections.singleton("file");
    private File file;

    public Collection<String> supportedSchemes() {
        return schemes;
    }

    public void connect(URI nameServiceUri) {
        if (schemes.contains(nameServiceUri.getScheme())) {
            file = new File(nameServiceUri);
        } else {
            throw new RuntimeException("Unsupported scheme!");
        }
    }

    public void registerService(String serviceName, URI uri) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw");
             FileChannel fileChannel = raf.getChannel()) {
            FileLock fileLock = fileChannel.lock();
            try {
                int fileLen = (int) raf.length();
                Metadata metadata;
                byte[] bytes;
                if (fileLen > 0) {
                    bytes = new byte[(int) raf.length()];
                    ByteBuffer buffer = ByteBuffer.wrap(bytes);
                    while (buffer.hasRemaining()) {
                        fileChannel.read(buffer);
                    }
                }
            } finally {
                fileLock.release();
            }
        }

    }

    public URI lookService(String serviceName) {
        return null;
    }
}