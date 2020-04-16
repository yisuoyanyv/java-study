package jobInterview.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhangjinglong
 * @date 2020-04-16-9:51 下午
 * <p>
 * ReentrantReadWriteLock实现同步方法
 */

public class Test7 {
    public static void main(String[] args) {
        Storage7 storage = new Storage7();
        //创建四个线程，两个读线程，两个写线程
        Thread tr1 = new Thread(() -> {
            try {
                storage.get("kkx");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "读线程1");

        Thread tr2 = new Thread(() -> {
            try {
                storage.get("mr");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "读线程2");

        Thread tw1 = new Thread(() -> {
            try {
                storage.put("xy", "小樱");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "写线程1");
        Thread tw2 = new Thread(() -> {
            try {
                storage.put("xwz", "小丸子");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "写线程2");

//        //并发读取
//        tr1.start();
//        tr2.start();
//        //并发读写
//        tr1.start();
//        tw1.start();

        //写并发
        tw1.start();
        tw2.start();
        tr1.start();


    }

}

/**
 * 业务类
 */
class Storage7 {

    //创建一个读写锁
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private Map<String, String> resource = new HashMap<>();

    {
        resource.put("kkx", "卡卡西");
        resource.put("zz", "佐助");
        resource.put("mr", "鸣人");
    }

    /**
     * 读取数据的方法
     *
     * @param key
     * @return
     * @throws InterruptedException
     */
    public String get(String key) throws InterruptedException {
        //加锁（开启读锁）
        lock.readLock().lock();
        String result = null;
        System.out.println(Thread.currentThread().getName() + "开始获取数据。。。。");
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Thread.currentThread().getName() + "要获取的key：" + key);
        TimeUnit.SECONDS.sleep(1);
        result = resource.get(key);
        System.out.println(Thread.currentThread().getName() + "获取的数据是：" + result);
        //解锁
        lock.readLock().unlock();
        return result;
    }


    /**
     * 写数据
     *
     * @param key
     * @param value
     * @throws InterruptedException
     */
    public void put(String key, String value) throws InterruptedException {
        //加锁（加的是写锁）
        lock.writeLock().lock();
        System.out.println("开始设置数据：");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("要设置的数据是：[" + key + ":" + value + "]");
        TimeUnit.SECONDS.sleep(1);
        resource.put(key, value);
        System.out.println("设置完成");

        //解锁
        lock.writeLock().unlock();

    }

}