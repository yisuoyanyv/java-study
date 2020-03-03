package mutithread.concurrent.chapter05;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangjinglong
 * @date 2020-02-16-22:38
 */

public class EventClient {
    public static void main(String[] args) {
        final EventQueue eventQueue=new EventQueue();

        new Thread(()->{
            for(;;){
                eventQueue.offer(new EventQueue.Event());
            }
        },"Producer").start();

        new Thread(()->{
            for(;;){
                eventQueue.take();
                try{
                    TimeUnit.MILLISECONDS.sleep(10);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"Consumer").start();
    }
}
