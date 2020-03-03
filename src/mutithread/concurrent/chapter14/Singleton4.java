package mutithread.concurrent.chapter14;

import java.net.Socket;
import java.sql.Connection;

/**
 * Double-Check  提供了一种高效的数据同步策略
 * 在首次初始化时加锁，之后则允许多个线程同时进行getInstance方法的调用来获得类的实例
 * @author zhangjinglong
 * @date 2020-02-19-17:13
 *
 * 这种方式既满足了懒加载，又保证了instance实例的唯一性，提高了高效的数据同步策略，
 * 可以允许多个线程同时对getInstance进行访问。
 *
 * 但是，这种方式在多线程的情况下有可能会引起空指针异常。
 * 由于JVM运行时指令重排序和Happens-Before规则，instance，conn和socket三者之间的实例化关系并物前后关系的约束
 * 如instance最先被实例化，而conn和socket并未完成实例化，
 * 未完成实例化的实例调用其方法就会抛出空指针异常
 *
 */

//final不允许被继承
public final class Singleton4 {

    //实例变量
    private byte[] data=new byte[1024];

    //定义实例，但是不直接进行初始化
    private static Singleton4 instance=null;

    Connection conn;

    Socket socket;

    //私有构造函数，不允许外部new
    private Singleton4(){
        this.conn=null;//初始化conn
        this.socket=null;//初始化socket
    }
    public static  Singleton4 getInstance(){
        //当instance 为null 时，进入同步代码块，同时该判断避免了每次都需要进入同步代码块，可以提高效率
        if(null==instance){
            //只有一个线程能够获得Singleton4.class关联的monitor
            synchronized (Singleton4.class){
                //判断如果instance 为null则创建
                if(null==instance){
                    instance=new Singleton4();
                }
            }
        }
        return instance;
    }
}
