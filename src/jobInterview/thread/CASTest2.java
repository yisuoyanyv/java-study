package jobInterview.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangjinglong
 * @date 2020-04-18-12:17 下午
 * <p>
 * CAS  AtomicInteger实现i++的原子操作
 */

public class CASTest2 {
    public static void main(String[] args) throws InterruptedException {
        long t1 = System.currentTimeMillis();
        AtomicInteger ai = new AtomicInteger(0);

        CountDownLatch cd = new CountDownLatch(100);

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int x = 0; x < 100; x++) {
                    //ai.get()获取当前值
                    while (!ai.compareAndSet(ai.get(), ai.get() + 1)) {
                    }
                }
                cd.countDown();
            }).start();
        }
        cd.await();
        long t2 = System.currentTimeMillis();

        System.out.println(ai.get());
        System.out.println("耗时" + (t2 - t1) + "毫秒");
    }
}
