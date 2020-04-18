package jobInterview.thread;

import sun.misc.Unsafe;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangjinglong
 * @date 2020-04-18-9:18 上午
 * <p>
 * 线程的挂起和恢复
 */

public class UnsafeTest4 {
    public static void main(String[] args) throws InterruptedException {
        Unsafe unsafe = UnsafeUtil.getInstance();
        //创建一个线程对象
        Thread t = new Thread(() -> {
            for (int i = 1; ; i++) {
                System.out.println("ThreadA:" + i);
                //每次i是3倍数的时候，将线程挂起
                if (i % 3 == 0) {
                    System.out.println("park");
                    //(true表示时间以毫秒为单位，false是纳秒），时长）
                    unsafe.park(true, 1000000000000000000L);
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //启动线程
        t.start();

        for (; ; ) {
            //让主线程等待6秒，就唤醒子线程
            TimeUnit.SECONDS.sleep(6);
            //唤醒子线程
            unsafe.unpark(t);
        }


    }
}
