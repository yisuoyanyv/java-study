package juc.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//演示线程池三种常用分类
public class ThreadPoolDemo1 {
    public static void main(String[] args) {
        //一池五线程
//        final ExecutorService threadPool1 = Executors.newFixedThreadPool(5); // 5个窗口

        //一池一线程
//        final ExecutorService threadPool2 = Executors.newSingleThreadExecutor();

        //一池可扩容线程
        final ExecutorService threadPool3 = Executors.newCachedThreadPool();

        try{
            //10个顾客请求
            for (int i = 1; i <= 10; i++) {
                //执行
                threadPool3.execute(()->{
                    System.out.println(Thread.currentThread().getName()+" 办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭
            threadPool3.shutdown();
        }
    }
}
