package mutithread.concurrent.chapter06;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangjinglong
 * @date 2020-02-17-11:04
 */

public class ThreadGroupEnumerateThreads {
    public static void main(String[] args) throws InterruptedException {
        //创建一个ThreadGroup
        ThreadGroup myGroup=new ThreadGroup("MyGroup");
        //创建线程传入threadgroup
        Thread thread=new Thread(myGroup,()->{
            while(true){
                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){

                }
            }
        },"MyThread");
        thread.start();

        TimeUnit.MILLISECONDS.sleep(2);
        ThreadGroup mainGroup=Thread.currentThread().getThreadGroup();

        Thread[] list=new Thread[mainGroup.activeCount()];
        int recurseSize=mainGroup.enumerate(list);
        System.out.println(recurseSize);

        recurseSize=mainGroup.enumerate(list,false);
        System.out.println(recurseSize);
    }
}
