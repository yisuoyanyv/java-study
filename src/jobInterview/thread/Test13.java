package jobInterview.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangjinglong
 * @date 2020-04-17-10:03 下午
 * <p>
 * 生产者消费者问题-Lock实现
 */

public class Test13 {
    public static void main(String[] args) {
        Storage13 storage = new Storage13();


        //生产线程
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    storage.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "生产线程-1").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    storage.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "生产线程-2").start();

        //消费线程
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    storage.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "消费线程-1").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    storage.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "消费线程-2").start();
    }

}

/**
 * 生产者消费者问题
 * 检查库存，生产/等待，唤醒
 */
class Storage13 {


    int source = 0;
    //准备一把锁
    Lock lock = new ReentrantLock();
    //获取一个Conditin对象
    Condition condition = lock.newCondition();

    //生产
    public void produce() throws InterruptedException {
        lock.lock();//加锁
        //检查库存
//        if(source==1){//为了防止虚假唤醒，需要将if改为while
        while (source == 1) {//为了防止虚假唤醒，需要将if改为while
            //有库存（还没有消费）就等待
//            this.wait();
            condition.await();
        }
        //生产
        source++;
        System.out.println(Thread.currentThread().getName() + "生产一个产品，当前库存：" + source);
        //唤醒消费线程
//        this.notifyAll();
        condition.signalAll();
        lock.unlock();//解锁
    }

    //消费
    public void consumer() throws InterruptedException {
        lock.lock();//加锁
        //检查库存
//        if(source==0){//为了防止虚假唤醒，需要将if改为while
        while (source == 0) {
//            this.wait();
            condition.await();
        }
        source--;
        System.out.println(Thread.currentThread().getName() + "消费一个产品，当前库存：" + source);
        //唤醒生产线程
//        this.notifyAll();
        condition.signalAll();
        lock.unlock();
    }
}