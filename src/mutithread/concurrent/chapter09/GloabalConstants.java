package mutithread.concurrent.chapter09;

import java.util.Random;

/**
 * @author zhangjinglong
 * @date 2020-02-18-19:44
 */

public class GloabalConstants {
    static{
        System.out.println("The GlobalConstants will be initialized.");
    }

    //在其他类中使用MAX不会导致GlobalConstants的初始化，静态代码块不会输出
    public   final static int MAX=10;

    //虽然RANDOM是静态变量，但是由于计算复杂，只有初始化之后才能得到结果，因此在其他类中使用RANDOM
    // 会导致GloabalConstants的 初始化
    public final static int RANDOM=new Random().nextInt();
}
