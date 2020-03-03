package mutithread.concurrent.chapter14;

import java.net.Socket;
import java.sql.Connection;

/**
 * Volatile+Double-Check
 * @author zhangjinglong
 * @date 2020-02-19-17:13
 *
 *
 *  * 由于JVM运行时指令重排序和Happens-Before规则，instance，conn和socket三者之间的实例化关系并物前后关系的约束
 *  * 如instance最先被实例化，而conn和socket并未完成实例化，
 *  * 未完成实例化的实例调用其方法就会抛出空指针异常
 *  为防止JVM在运行时指令重排序导致的问题，采用volatile关键字可以满足
 *  至此，多线程下的单例，懒加载以及获取实例的高效性都可满足
 */

//final不允许被继承
public final class Singleton5 {

    //实例变量
    private byte[] data=new byte[1024];

    //定义实例，但是不直接进行初始化  注意使用volatile关键字保证conn和socket先实例化
    private volatile  static Singleton5 instance=null;

    Connection conn;

    Socket socket;

    //私有构造函数，不允许外部new
    private Singleton5(){
        this.conn=null;//初始化conn
        this.socket=null;//初始化socket
    }
    public static Singleton5 getInstance(){
        //当instance 为null 时，进入同步代码块，同时该判断避免了每次都需要进入同步代码块，可以提高效率
        if(null==instance){
            //只有一个线程能够获得Singleton4.class关联的monitor
            synchronized (Singleton5.class){
                //判断如果instance 为null则创建
                if(null==instance){
                    instance=new Singleton5();
                }
            }
        }
        return instance;
    }
}
