package mutithread.concurrent.chapter08;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangjinglong
 * @date 2020-02-17-23:16
 */

public class ThreadPoolTest {
    public static void main(String[] args)  throws InterruptedException{
        //定义线程池，初始化线程数为2，核心线程数为4，最大线程为6，任务队列最多允许1000个任务
        final ThreadPool threadPool=new BasicThreadPool(2,6,4,100);
        //定义20个任务并且提交给线程池
        for (int i = 0; i < 20; i++) {
            threadPool.execute(()->{
                try{
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName()+" is running and done.");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }

        for(;;){
            //不断输出线程池的信息
//            System.out.println("getActiveCount:"+coreJavaVolumeFundamentals.threadPool.getActiveCount());
//            System.out.println("getQueueSize:"+coreJavaVolumeFundamentals.threadPool.getQueueSize());
//            System.out.println("getCoreSize:"+coreJavaVolumeFundamentals.threadPool.getCoreSize());
//            System.out.println("getMaxSize:"+coreJavaVolumeFundamentals.threadPool.getMaxSize());
//            System.out.println("=======================================");
//            TimeUnit.SECONDS.sleep(5);

            TimeUnit.SECONDS.sleep(12);
            //线程池在12秒后被shutdow
            threadPool.shutdown();
            //使main线程join,方便通过工具观测线程堆栈信息
            Thread.currentThread().join();

        }
    }
}
