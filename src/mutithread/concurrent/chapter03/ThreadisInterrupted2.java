package mutithread.concurrent.chapter03;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangjinglong
 * @date 2020-02-16-14:24
 */

public class ThreadisInterrupted2 {
    public static void main(String[] args) throws InterruptedException{
        Thread thread=new Thread(){
            @Override
            public void run() {
                while(true){
                    try{
                        TimeUnit.MINUTES.sleep(1);
                    }catch (InterruptedException e){
                        //ignore the exception
                        //here the interrupt flag will be clear.
                        System.out.printf("I am be interrupted?  %s \n",isInterrupted());
                    }
                }
            }
        };

        thread.setDaemon(true);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.printf("Thread is interrupted? %s \n",thread.isInterrupted());
        thread.interrupt();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.printf("Thread is interrupted? %s \n",thread.isInterrupted());



    }
}
