package jobInterview.singleton;

/**
 * @author zhangjinglong
 * @date 2020-03-03-11:47 下午
 * 
 * 懒汉式：
 *  延迟创建这个实例对象
 *  （1）构造器私有化
 *  （2）用一个静态变量保存这个唯一的实例
 *  （3）提供一个静态方法，获取这个实例对象
 */

public class Singleton4 {
    private static Singleton4 instance;
    private Singleton4(){
        
    }
    
    public static Singleton4 getInstance(){
        if(instance==null){
            instance=new Singleton4();
        }
        return instance;
    }
    
}
