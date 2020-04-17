package jobInterview.thread;

/**
 * @author zhangjinglong
 * @date 2020-04-17-9:46 下午
 * <p>
 * 生产者消费者问题 -虚假唤醒
 */

public class Test12 {

    public static void main(String[] args) {
        Storage12 storage = new Storage12();


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
class Storage12 {
    int source = 0;

    //生产
    public synchronized void produce() throws InterruptedException {
        //检查库存
//        if(source==1){//为了防止虚假唤醒，需要将if改为while
        while (source == 1) {//为了防止虚假唤醒，需要将if改为while
            //有库存（还没有消费）就等待
            this.wait();
        }
        //生产
        source++;
        System.out.println(Thread.currentThread().getName() + "生产一个产品，当前库存：" + source);
        //唤醒消费线程
        this.notifyAll();
    }

    //消费
    public synchronized void consumer() throws InterruptedException {
        //检查库存
//        if(source==0){//为了防止虚假唤醒，需要将if改为while
        while (source == 0) {
            this.wait();
        }
        source--;
        System.out.println(Thread.currentThread().getName() + "消费一个产品，当前库存：" + source);
        //唤醒生产线程
        this.notifyAll();
    }
}