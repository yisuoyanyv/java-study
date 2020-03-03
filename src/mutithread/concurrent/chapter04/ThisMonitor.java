package mutithread.concurrent.chapter04;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangjinglong
 * @date 2020-02-16-21:27
 */

public class ThisMonitor {

    public synchronized void method1(){
        System.out.println(Thread.currentThread().getName()+" enter to method1");
        try{
            TimeUnit.MINUTES.sleep(10);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public  void method2(){
        synchronized(this){
            System.out.println(Thread.currentThread().getName()+" enter to method2");
            try{
                TimeUnit.MINUTES.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }


    }

    public static void main(String[] args) {

        ThisMonitor thisMonitor=new ThisMonitor();
        new Thread(thisMonitor::method1,"T1").start();
        new Thread(thisMonitor::method2,"T2").start();

    }


}
