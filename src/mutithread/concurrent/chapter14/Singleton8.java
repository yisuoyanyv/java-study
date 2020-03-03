package mutithread.concurrent.chapter14;

/**
 *
 * 增加懒加载的特性，类似于Holder的方式
 * @author zhangjinglong
 * @date 2020-02-19-20:52
 */

public class Singleton8 {
    //实例变量
    private byte[] data=new byte[1024];

    private Singleton8(){

    }

    //使用枚举冲淡holder
    private enum EnumHolder{
        INSTANCE;
        private Singleton8 instance;

        EnumHolder(){
            this.instance=new Singleton8();
        }

        private Singleton8 getInstance(){
            return instance;
        }
    }

    public static Singleton8 getInstance(){
        return EnumHolder.INSTANCE.getInstance();
    }

}
