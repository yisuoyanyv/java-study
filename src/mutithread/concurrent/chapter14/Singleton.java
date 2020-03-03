package mutithread.concurrent.chapter14;

/**
 * 饿汉式
 * @author zhangjinglong
 * @date 2020-02-19-17:13
 *
 * 如果一个类中的成员属性比较少，且占用的内存资源不多，饿汉的方式未尝不可，相反，如果一个类中的成员都是比较重
 * 的资源，那么这种方式就会有些不妥
 * 饿汉式的单例模式可以保证多个线程下的唯一实例，getInstance方法性能比较高，但是无法进行懒加载
 */

//final不允许被继承
public final class Singleton {

    //实例变量
    private byte[] data=new byte[1024];

    //在定义实例对象的时候直接初始化
    private static Singleton instance=new Singleton();

    //私有构造函数，不允许外部new
    private Singleton(){

    }

    public static Singleton getInstance(){
        return instance;
    }
}
