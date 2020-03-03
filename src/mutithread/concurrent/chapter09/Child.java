package mutithread.concurrent.chapter09;

/**
 * @author zhangjinglong
 * @date 2020-02-18-19:34
 */

public class Child extends Parent {
    static{
        System.out.println("The Child will be initialized");
    }

    public static int x=10;
}
