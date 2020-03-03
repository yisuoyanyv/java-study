package mutithread.concurrent.chapter23;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 模拟了程序员乘坐不同的交通工具到达城市广场的情形
 * @author zhangjinglong
 * @date 2020-02-22-21:24
 */

//程序员旅游线程
public class ProgrammerTravel extends Thread {

    //门阀
    private final Latch latch;
    //程序员
    private final String programmer;
    //交通工具
    private final String transportation;

    //通过构造函数传入
    public ProgrammerTravel(Latch latch,String programmer,String transportation){
        this.latch=latch;
        this.programmer=programmer;
        this.transportation=transportation;
    }

    @Override
    public void run() {
        System.out.println(programmer+" start take the transportation [ "+transportation+" ]");
        try{
            //程序员乘坐交通工具花费在路上的时间（使用随机数字模拟）
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));

        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(programmer+" arrived by "+transportation);
        //完成任务时使计数器减一
        latch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        //定义Latch ，limit为4
        Latch latch=new CountDownLatch(4);
        new ProgrammerTravel(latch,"Alex","Bus").start();
        new ProgrammerTravel(latch,"Gavin","Walking").start();
        new ProgrammerTravel(latch,"Jack","Subway").start();
        new ProgrammerTravel(latch,"Dillon","Bicyle").start();

        try{
            latch.await(TimeUnit.SECONDS,5);
            System.out.println("===== all of programmer arrived=====");
        }catch (WaitTimeoutException e){
            e.printStackTrace();
        }


        //当前线程（main线程会进入阻塞，直到四个程序员全部都到达目的地

//        latch.await();
//        System.out.println("===== all of programmer arrived=====");
    }
}
