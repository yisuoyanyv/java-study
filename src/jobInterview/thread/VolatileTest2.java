package jobInterview.thread;

import mutithread.concurrent.chapter23.CountDownLatch;

/**
 * @author zhangjinglong
 * @date 2020-04-18-11:10 上午
 * <p>
 * Volatile关键字防止指令重排序
 * <p>
 * 多线程情况下不能确定重排序，没有依赖关系的代码可能会重排
 * 高并发里共享数据一定要用volatile修饰，防止重排序
 * volatile 的write写数据时会加锁，其他线程读不到  只保证write和read的原子性
 */

public class VolatileTest2 {
    static volatile int a, b, x, y;

    public static void main(String[] args) throws InterruptedException {

        for (int i = 1; ; i++) {
            a = b = x = y = 0;
            CountDownLatch cd = new CountDownLatch(2);
            new Thread(() -> {
                //1
                //2
                x = 1;//3
                a = y;//4
                //5
                //6
                cd.countDown();
            }).start();

            new Thread(() -> {
                y = 1;
                b = x;
                cd.countDown();
            }).start();

            cd.await();
            System.out.println("第" + i + "次， a= " + a + ", b = " + b);

            if (a == 0 && b == 0) {
                break;
            }
        }
    }
}
