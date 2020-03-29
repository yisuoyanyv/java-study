package coreJavaVolume2AdvancedFeatures.compile;

import java.util.Map;

/**
 * @author zhangjinglong
 * @date 2020-03-29-10:35 上午
 * <p>
 * A class loader that loads classes from a map whose keys are class names and whose values are byte code arrays.
 */

public class MapClassLoader extends ClassLoader {
    private Map<String, byte[]> classes;

    public MapClassLoader(Map<String, byte[]> classes) {
        this.classes = classes;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classBytes = classes.get(name);
        if (classBytes == null) throw new ClassNotFoundException(name);
        Class<?> cl = defineClass(name, classBytes, 0, classBytes.length);
        if (cl == null) throw new ClassNotFoundException(name);
        return cl;
    }
}
