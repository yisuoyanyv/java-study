package mutithread.concurrent.chapter24;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * TaskHandler 用于处理每一个提交的Request 请求，由于TaskHandler将被Thread执行
 * 因此需要实现Runnable接口
 * @author zhangjinglong
 * @date 2020-02-22-22:15
 */

public class TaskHandler implements Runnable {
    //需要处理的request对象
    private final Request request;

    public TaskHandler(Request request){
        this.request=request;
    }

    @Override
    public void run() {
        System.out.println("Begin handler "+request);
        slowly();
        System.out.println("End Handler " +request);
    }

    //模拟请求处理比较耗时，使线程进入短暂的休眠阶段
    private void slowly(){
        try{
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
