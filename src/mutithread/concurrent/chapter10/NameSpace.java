package mutithread.concurrent.chapter10;

/**
 * @author zhangjinglong
 * @date 2020-02-18-23:28
 */

public class  NameSpace {
    public static void main(String[] args) throws ClassNotFoundException{
        //获取系统类加载器
        ClassLoader classLoader=NameSpace.class.getClassLoader();

        Class<?> aClass=classLoader.loadClass("mutithread.concurrent.chapter10.Test");
        Class<?> bClass=classLoader.loadClass("mutithread.concurrent.chapter10.Test");
        System.out.println(aClass.hashCode());
        System.out.println(bClass.hashCode());
        System.out.println(aClass==bClass);
    }
}
