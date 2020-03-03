package mutithread.concurrent.chapter03;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangjinglong
 * @date 2020-02-16-14:24
 */

public class ThreadisInterrupted {
    public static void main(String[] args) throws InterruptedException{
        Thread thread=new Thread(){
            @Override
            public void run() {
                while(true){
                    //do nothing ,just  empty loop.
                }
            }
        };
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.printf("Thread is interrupted ?  %s \n",thread.isInterrupted());
        thread.interrupt();
        System.out.printf("Thread is interrupted? %s \n",thread.isInterrupted());

    }
}
