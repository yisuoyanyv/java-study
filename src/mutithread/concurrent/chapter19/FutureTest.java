package mutithread.concurrent.chapter19;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangjinglong
 * @date 2020-02-21-22:55
 */

public class FutureTest {
    //Future 无返回值的任务提交测试
    public static void main(String[] args) throws InterruptedException{
        //定义不需要返回值的FutureService
        FutureService<Void,Void> service=FutureService.newService();
        //submit方法为立即返回的方法
        Future<?> future=service.submit(()->{
            try{
                TimeUnit.SECONDS.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(" I am finish done .");
        });

        //get 方法会使当前线程进入阻塞
        future.get();

        System.out.println("The next task");
    }
}
