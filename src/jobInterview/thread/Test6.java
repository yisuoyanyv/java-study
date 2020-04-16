package jobInterview.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangjinglong
 * @date 2020-04-16-9:39 下午
 * <p>
 * 使用 ReentrantLock 实现程序同步
 */

public class Test6 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cd = new CountDownLatch(100);

        Storage6 storage = new Storage6();
        //创建100个线程给source++ ，每个线程执行10次
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int x = 0; x < 10; x++) {
                    storage.addSource();
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
        System.out.println("最终source：" + storage.source);
    }
}

/**
 * 创建要个Storge（业务类）
 */
class Storage6 {
    int source = 0;

    //创建一个锁对象  无参默认是非公平锁  ReentrantLock是悲观锁
    Lock lock = new ReentrantLock();

    public void addSource() {
//        source++;// temp=source+1;   source=temp;
        lock.lock();
        int temp = source + 1;
        source = temp;
        lock.unlock();
    }
}