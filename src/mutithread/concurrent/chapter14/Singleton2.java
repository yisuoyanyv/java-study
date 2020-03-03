package mutithread.concurrent.chapter14;

/**
 * 懒汉式
 * @author zhangjinglong
 * @date 2020-02-19-17:13
 *

 * 懒汉式的单例模式 不能 保证多个线程下的 单例的唯一性
 */

//final不允许被继承
public final class Singleton2 {

    //实例变量
    private byte[] data=new byte[1024];

    //定义实例，但是不直接进行初始化
    private static Singleton2 instance=null;

    //私有构造函数，不允许外部new
    private Singleton2(){

    }

    public static Singleton2 getInstance(){
        //不能保证多线程下的单例唯一性
        if(null==instance)
            instance=new Singleton2();
        return instance;
    }
}
