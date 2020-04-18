package jobInterview.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangjinglong
 * @date 2020-04-18-10:47 上午
 * <p>
 * Volatile关键字修改共享变量的可见性
 */

public class VolatileTest1 {

    //静态变量
    volatile static int i = 3;

    public static void main(String[] args) throws InterruptedException {
        //开启一个线程
        new Thread(() -> {
            System.out.println("子线程开启。。。");
            for (; ; ) {
                if (i != 3) {
                    break;
                }
            }

            System.out.println("子线程结束。。。");
        }).start();
        TimeUnit.SECONDS.sleep(1);

        //在主线程中将i的值修改
        i = 4;
        System.out.println("主线程修改i的值为：" + i);


    }
}
