package mutithread.concurrent.chapter09;

/**
 * @author zhangjinglong
 * @date 2020-02-18-20:53
 */

public class ClassInit {
    //父类中有静态变量value
    static class Parent{
        static  int value=10;
        static{
            value=20;
        }
    }

    //子类使用父类的静态变量为自己的静态变量赋值
    static class Child extends Parent{
        static int i=value;
    }

    public static void main(String[] args) {
        System.out.println(Child.i);
    }
}
