package mutithread.concurrent.chapter03;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangjinglong
 * @date 2020-02-16-14:17
 */

public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(()->{
            try{
                TimeUnit.MINUTES.sleep(1);
            }catch (InterruptedException e){
                System.out.println("Oh, i am be interrupted");
            }
        });

        thread.start();

        //short block and make sure thread is started.
        TimeUnit.MILLISECONDS.sleep(3000);
        thread.interrupt();

    }
}
