package mutithread.concurrent.chapter05;

import java.util.concurrent.TimeUnit;

/**
 * Synchronized 关键词缺点： 阻塞时长无法控制，并且线程不能被中断
 * @author zhangjinglong
 * @date 2020-02-16-23:11
 */

public class SynchronizedDefect {
    public synchronized void syncMethod(){
        try{
            TimeUnit.HOURS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        SynchronizedDefect defect=new SynchronizedDefect();
        Thread t1=new Thread(defect::syncMethod,"T1");
        //mark sure the t1 started.
        t1.start();

        TimeUnit.MILLISECONDS.sleep(2);

        Thread t2=new Thread(defect::syncMethod,"T2");
        t2.start();

        TimeUnit.MILLISECONDS.sleep(2);
        t2.interrupt();
        System.out.println(t2.isInterrupted());
        System.out.println(t2.getState());
    }
}
