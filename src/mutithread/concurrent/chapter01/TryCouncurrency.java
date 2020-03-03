package mutithread.concurrent.chapter01;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangjinglong
 * @date 2020-02-15-20:46
 */

public class TryCouncurrency {
    public static void main(String[] args) {

//        enjoymusic();


        //通过匿名内部类的方式创建线程，并且重写其中的run方法
       /* new Thread(){
            @Override
            public void run() {
                enjoymusic();
            }
        }.start();*/

       new Thread(TryCouncurrency::enjoymusic).start();//java 8 Lambda
        browseNews();

    }


    /**
     * Browse the latest news
     */
    private static void browseNews(){
        for(;;){
            System.out.println("Un-hhh, the good news.");
            sleep(1);
        }
    }

    /**
     * Listening and enjoy the music
     */
    private static void enjoymusic(){
        for(;;){
            System.out.println("Un-hhh, the nice music.");
            sleep(1);
        }
    }

    /**
     * Simulate the wait and ignore exception.
     * @param seconds
     */
    private static void sleep(int seconds){
        try{
            TimeUnit.SECONDS.sleep(seconds);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
