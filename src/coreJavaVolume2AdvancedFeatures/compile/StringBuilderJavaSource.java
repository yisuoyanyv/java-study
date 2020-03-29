package coreJavaVolume2AdvancedFeatures.compile;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

/**
 * @author zhangjinglong
 * @date 2020-03-28-10:54 下午
 * <p>
 * A Java source that holds the code in a string builder
 */

public class StringBuilderJavaSource extends SimpleJavaFileObject {
    private StringBuilder code;

    public StringBuilderJavaSource(String name) {
        super(URI.create("string:///" + name.replace(".", "/") + Kind.SOURCE.extension), Kind.SOURCE);
        code = new StringBuilder();
    }

    public CharSequence getCharContent(boolean ingoreEncodingErrors) {
        return code;
    }

    public void append(String str) {
        code.append(str);
        code.append('\n');
    }

}
