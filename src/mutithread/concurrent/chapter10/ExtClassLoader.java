package mutithread.concurrent.chapter10;

/**
 * 拓展类加载器加载的路径
 * @author zhangjinglong
 * @date 2020-02-18-21:44
 */

public class ExtClassLoader {
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.ext.dirs"));

//        /Users/zhangjinglong/Library/Java/Extensions:
//        /Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/jre/lib/ext:
//        /Library/Java/Extensions:
//        /Network/Library/Java/Extensions:
//        /System/Library/Java/Extensions:
//        /usr/lib/java
    }
}
