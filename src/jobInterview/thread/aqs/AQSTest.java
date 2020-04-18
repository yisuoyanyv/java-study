package jobInterview.thread.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author zhangjinglong
 * @date 2020-04-18-9:41 下午
 * <p>
 * 测试AQS程序
 */

public class AQSTest {
    public static void main(String[] args) throws InterruptedException {
        Storage st = new Storage();
        CountDownLatch cd = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                for (int x = 0; x < 10; x++) {
                    st.incr();
                }
                cd.countDown();
            }, "Thread-" + i).start();
        }
        cd.await();
        System.out.println("最终的资源数：" + st.resource);

    }
}

class Storage {
    MyLock lock = new MyLock();
    int resource = 1;

    public void incr() {
        lock.lock();
        try {
            resource++;
            System.out.println("当前 resource为：" + resource);
        } finally {
            lock.unlock();
        }

    }

}

//定义一把自己的锁
class MyLock implements Lock {
    AQS6 aqs = new AQS6();


    @Override
    public void lock() {
        aqs.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        aqs.release(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}