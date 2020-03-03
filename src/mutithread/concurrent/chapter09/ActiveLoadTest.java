package mutithread.concurrent.chapter09;

/**
 * @author zhangjinglong
 * @date 2020-02-18-19:35
 */

public class ActiveLoadTest {
    public static void main(String[] args) {
        //调用子类的静态变量，会初始化子类和父类
//        System.out.println(Child.x);


        //通过子类调用父类的静态变量，只会初始化父类，不会初始化子类
        System.out.println(Child.y);
    }
}
