package jobInterview.thread;

import mutithread.concurrent.chapter23.CountDownLatch;

/**
 * @author zhangjinglong
 * @date 2020-04-17-9:27 下午
 * 生产者消费者问题 （线程间通信）
 * <p>
 * notify和notifyAll wait 必须在synchronized 块中
 */

public class Test11 {
    public static void main(String[] args) {
        Storage11 storage = new Storage11();


        //生产线程
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    storage.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //消费线程
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    storage.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}

/**
 * 生产者消费者问题
 * 检查库存，生产/等待，唤醒
 */
class Storage11 {
    int source = 0;

    //生产
    public synchronized void produce() throws InterruptedException {
        //检查库存
        if (source == 1) {
            //有库存（还没有消费）就等待
            this.wait();
        }
        //生产
        source++;
        System.out.println("生产一个产品，当前库存：" + source);
        //唤醒消费线程
        this.notifyAll();
    }

    //消费
    public synchronized void consumer() throws InterruptedException {
        //检查库存
        if (source == 0) {
            this.wait();
        }
        source--;
        System.out.println("消费一个产品，当前库存：" + source);
        //唤醒生产线程
        this.notifyAll();
    }
}