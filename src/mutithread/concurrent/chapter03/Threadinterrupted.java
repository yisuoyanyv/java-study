package mutithread.concurrent.chapter03;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangjinglong
 * @date 2020-02-16-15:08
 */

public class Threadinterrupted {
    public static void main(String[] args)  throws InterruptedException{
        Thread thread=new Thread(){
            @Override
            public void run() {
                while(true){
                    System.out.println(Thread.interrupted());
                }
            }
        };

        thread.setDaemon(true);
        thread.start();

        //shortly block make sure the thread is started.
        TimeUnit.MILLISECONDS.sleep(2);
        thread.interrupt();
    }
}
