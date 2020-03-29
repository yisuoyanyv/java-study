package coreJavaVolume2AdvancedFeatures.classLoader;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author zhangjinglong
 * @date 2020-03-29-6:48 下午
 * <p>
 * This program demonstrates a custom class loader that decrypts class files
 */

public class ClassLoaderTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new ClassLoaderFrame();
            frame.setTitle("ClassLoaderTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

/**
 * This frame contains two text fields for the name of the class to load and the decryption key
 */
class ClassLoaderFrame extends JFrame {
    private JTextField keyFiled = new JTextField("3", 4);
    private JTextField nameFiled = new JTextField("Calculator", 30);
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    public ClassLoaderFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLayout(new GridBagLayout());
        add(new JLabel("Class"), new GBC(0, 0).setAnchor(GBC.EAST));
        add(nameFiled, new GBC(1, 0).setWeight(100, 0).setAnchor(GBC.WEST));
        add(new JLabel("key"), new GBC(0, 1).setAnchor(GBC.EAST));
        add(keyFiled, new GBC(1, 1).setWeight(100, 0).setAnchor(GBC.WEST));
        JButton loadButton = new JButton("Load");
        add(loadButton, new GBC(0, 2, 2, 1));
        loadButton.addActionListener(event -> runClass(nameFiled.getText(), keyFiled.getText()));
        pack();
    }

    /**
     * Runs the main method of a given class.
     *
     * @param name the class name
     * @param key  the decryption key for the class files
     */
    public void runClass(String name, String key) {
        try {
            ClassLoader loader = new CryptoClassLoader(Integer.parseInt(key));
            Class<?> c = loader.loadClass(name);
            Method m = c.getMethod("main", String[].class);
            m.invoke(null, (Object) new String[]{});

        } catch (Throwable e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

}

/**
 * This class loader loads encrypted class files.
 */
class CryptoClassLoader extends ClassLoader {
    private int key;

    /**
     * Constructs a crypto class loader.
     *
     * @param key the decryption key
     */
    public CryptoClassLoader(int key) {
        this.key = key;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] classBytes = null;
            classBytes = loadClassBytes(name);
            Class<?> cl = defineClass(name, classBytes, 0, classBytes.length);
            if (cl == null) throw new ClassNotFoundException(name);
            return cl;
        } catch (IOException e) {
            throw new ClassNotFoundException(name);
        }
    }

    /**
     * Loads and decrypt the class file bytes.
     *
     * @param name the class name
     * @return an array with the class file bytes.
     * @throws IOException
     */
    private byte[] loadClassBytes(String name) throws IOException {
        String cname = name.replace('.', '/') + ".caesar";
        //加密后的类的字节码文件存放目录和类的名称可能不同，自定的类加载器可实现路径的不一致
        byte[] bytes = Files.readAllBytes(Paths.get("src/coreJavaVolume2AdvancedFeatures/classLoader/", cname));
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (bytes[i] - key);

        }
        return bytes;
    }
}