package jobInterview.thread;


import java.util.concurrent.CountDownLatch;

/**
 * @author zhangjinglong
 * @date 2020-04-18-11:43 上午
 * <p>
 * 模拟实现CAS算法
 * <p>
 * <p>
 * 标准的CAS不加锁，底层实现的，速度很快
 * 我们模拟的CAS还是加了锁
 */

public class CASTest1 {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        CountDownLatch cd = new CountDownLatch(1000);
        Storage storage = new Storage();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                for (int x = 0; x < 100; x++) {
//                    storage.incr();
                    storage.incrCAS();
                }
                cd.countDown();

            }).start();
        }

        cd.await();
        System.out.println(storage.i);
        long end = System.currentTimeMillis();
        System.out.println("耗时" + (end - start) + "毫秒");
    }
}

class Storage {
    int i = 0;

    public synchronized void incr() {
        i++;
        //int temp=i+1;
        //i=temp;
    }

    public void incrCAS() {
        //死循环判断，直到修改成功
        while (!compareAndSwap(i, i + 1)) {
        }
    }

    /**
     * 如果期望值和内存中现有的值一致，就将新值赋给内存中的变量，返回true，否则返回false
     *
     * @param exepNum 期望值
     * @param newNum  新值
     * @return
     */
    public synchronized boolean compareAndSwap(int exepNum, int newNum) {
        if (exepNum == this.i) {
            this.i = newNum;
            return true;
        }
        return false;
    }
}