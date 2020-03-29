package coreJavaVolume2AdvancedFeatures.compile;

import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

/**
 * @author zhangjinglong
 * @date 2020-03-28-11:01 下午
 * <p>
 * A Java class that holds the bytecodes in a byte array.
 */

public class ByteArrayJavaClass extends SimpleJavaFileObject {
    private ByteArrayOutputStream stream;

    /**
     * Constructs a new ByteArrayJavaClass
     *
     * @param name
     */
    public ByteArrayJavaClass(String name) {
        super(URI.create("bytes:///" + name), Kind.CLASS);
        stream = new ByteArrayOutputStream();
    }

    @Override
    public OutputStream openOutputStream() throws IOException {
        return stream;
    }

    public byte[] getBytes() {
        return stream.toByteArray();
    }
}
