package mutithread.concurrent.chapter10;

/**
 * @author zhangjinglong
 * @date 2020-02-18-21:50
 */

public class ApplicationClassLoader {
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.class.path"));
        System.out.println(ApplicationClassLoader.class.getClassLoader());
    }
}
