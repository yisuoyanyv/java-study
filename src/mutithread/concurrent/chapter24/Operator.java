package mutithread.concurrent.chapter24;

import mutithread.concurrent.chapter08.BasicThreadPool;
import mutithread.concurrent.chapter08.ThreadPool;

/**
 * @author zhangjinglong
 * @date 2020-02-22-22:20
 */

public class Operator {

    //使用线程池替代为每个请求创建线程
    private final ThreadPool threadPool=new BasicThreadPool(2,6,4,1000);

    public void call(String business){
        //使用线程池的方式
        TaskHandler taskHandler=new TaskHandler(new Request(business));
        threadPool.execute(taskHandler);


//        //为每个请求创建一个线程去处理
//        TaskHandler taskHandler=new TaskHandler(new Request(business));
//        new Thread(taskHandler).start();
    }
}
