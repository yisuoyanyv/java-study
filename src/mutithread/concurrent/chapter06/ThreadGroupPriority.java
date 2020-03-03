package mutithread.concurrent.chapter06;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangjinglong
 * @date 2020-02-17-16:54
 */

public class ThreadGroupPriority {

    public static void main(String[] args) throws InterruptedException{
        /**
         * Create a thread gourp and thread.
         *
         */
        ThreadGroup group=new ThreadGroup("group1");

        Thread thread=new Thread(group,()->{
            while(true){
                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"thread");

        thread.setDaemon(true);
        thread.start();

        System.out.println("group.GetMaxPriority()="+group.getMaxPriority());
        System.out.println("thread.getPriority()="+thread.getPriority());
        //改变group的最大优先级
        group.setMaxPriority(3);
        System.out.println("group.GetMaxPriority()="+group.getMaxPriority());
        System.out.println("thread.getPriority()="+thread.getPriority());

    }
}
