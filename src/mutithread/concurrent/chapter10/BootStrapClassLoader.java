package mutithread.concurrent.chapter10;

/**
 * 根类加载器测试
 * @author zhangjinglong
 * @date 2020-02-18-21:39
 */

public class BootStrapClassLoader {
    public static void main(String[] args) {
        System.out.println("Bootstrap:"+String.class.getClassLoader());

        System.out.println(System.getProperty("sun.boot.class.path"));
    }
}
