package mutithread.concurrent.chapter14;

/**
 * 枚举方式   使用枚举类实现单例设计模式
 * @author zhangjinglong
 * @date 2020-02-19-20:44
 *枚举类不能够懒加载，对Singleton主动使用
 * 比如调用其中对静态方法则INSTANCE会立即得到实例化
 */
//枚举类型本身就是final的，不允许被继承
public enum Singleton7 {
    INSTANCE;

    //实例变量
    private byte[] data=new byte[1024];

    Singleton7(){
        System.out.printf("INSTANCE will be initialized immediately");
    }

    public static void method(){
        //调用该方法则会主动使用Single，INSTANCE将会被实例化
    }

    public static Singleton7 getInstance(){
        return INSTANCE;
    }
}
