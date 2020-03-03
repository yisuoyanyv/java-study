package mutithread.concurrent.chapter14;

/**
 * 懒汉式+同步方法
 * @author zhangjinglong
 * @date 2020-02-19-17:13
 *

 * 在懒汉式的基础上，增加同步的约束 可以 保证多个线程下的 单例的唯一性
 * 但是synchronized关键字天生但排他性导致了getInstance方法只能在同一时刻被一个线程访问，性能低下
 */

//final不允许被继承
public final class Singleton3 {

    //实例变量
    private byte[] data=new byte[1024];

    //定义实例，但是不直接进行初始化
    private static Singleton3 instance=null;

    //私有构造函数，不允许外部new
    private Singleton3(){

    }

    //向getInstance方法加入同步控制，每次只能有一个线程能够进入
    public static synchronized Singleton3 getInstance(){
        if(null==instance)
            instance=new Singleton3();
        return instance;
    }
}
