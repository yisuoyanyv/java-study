package jobInterview.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangjinglong
 * @date 2020-04-16-8:52 下午
 * <p>
 * 无锁状态下高并发的问题
 */

public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cd = new CountDownLatch(100);

        Storage1 storage1 = new Storage1();
        //创建100个线程给source++ ，每个线程执行10次
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int x = 0; x < 10; x++) {
                    storage1.addSource();
                    //模拟耗时操作
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //将cd的数字减一
                cd.countDown();
            }).start();
        }
        //让主线程等待
        cd.await();
        System.out.println("最终source：" + storage1.source);
    }
}


/**
 * 创建要个Storge（业务类）
 */
class Storage1 {
    int source = 0;

    public void addSource() {
//        source++;// temp=source+1;   source=temp;
        int temp = source + 1;
        source = temp;
    }
}