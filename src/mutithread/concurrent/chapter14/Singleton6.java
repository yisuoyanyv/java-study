package mutithread.concurrent.chapter14;

import java.net.Socket;
import java.sql.Connection;

/**
 *
 * Holder方式
 * 借助类加载的特点
 * @author zhangjinglong
 * @date 2020-02-19-17:13
 *
 *     //在Single类中并没有instance的静态成员，而是将其放到了静态内部类Holder之中
 *     //因此在Singleton6类的初始化过程中并不会创建Singleton6的实例
 *     //Holder类中定义了Singleton6的静态变量，并且进行了实例化
 *     //当Holer被主动引用的时候则会创建Singleton6的实例
 *     //Singleton6实例的创建过程在JAVA程序编译时期收集至<clinit>()方法中，
 *     //该方法又是同步方法，同步方法可以保证内存的可见性、JVM指令的顺序性和原子性
 *     Holder方式的单例设计是最好的设计之一，也是目前使用比较广的设计之一
 */

//final不允许被继承
public final class Singleton6 {

    //实例变量
    private byte[] data=new byte[1024];

    //私有构造函数，不允许外部new
    private Singleton6(){
    }

    //在静态内部类中持有Singleton6的实例，并且可以被直接初始化
    private static class Holder{
        private static Singleton6 instance=new Singleton6();
    }
    //调用getInstance方法，事实上是获得Holder的instance静态属性
    public static Singleton6 getInstance(){
        return Holder.instance;
    }
}
