package mutithread.concurrent.chapter03;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangjinglong
 * @date 2020-02-16-15:18
 */

public class ThreadinterruptedTest {
    public static void main(String[] args) {
        //1.判断当前线程是否被中断
        System.out.println("Main thread is interrupted? "+Thread.interrupted());

        //2.中断当前线程
        Thread.currentThread().interrupt();
        //3.判断当前线程是否已经被中断
        System.out.println("Main thread is interrupted? "+Thread.currentThread().isInterrupted());

        try{
            //4.当前线程执行可中断方法
            TimeUnit.MINUTES.sleep(1);
        }catch (InterruptedException e){
            //5.捕获中断信号
            System.out.println("I will be interrupted still.");
        }
    }
}
