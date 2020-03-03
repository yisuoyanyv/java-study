package mutithread.concurrent.chapter13;

import java.util.concurrent.CountDownLatch;

/**
 * 理解volatile不保证原子性
 * @author zhangjinglong
 * @date 2020-02-19-16:25
 */

public class VolatileTest {
    //使用volatile修饰共享资源i
    private static volatile int i=0;
    //使用在23章的CountDownLatch
    private static final CountDownLatch latch=new CountDownLatch(10);

    private static void inc(){
        i++;
    }

    public static void main(String[] args)  throws InterruptedException{
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int x = 0; x < 1000; x++) {
                    inc();
                }
                //使计数器减1
                latch.countDown();
            }).start();
        }

        //等待所有的线程完成工作
        latch.await();
        System.out.println(i);
    }

}
