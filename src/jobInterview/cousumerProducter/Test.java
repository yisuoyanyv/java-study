package jobInterview.cousumerProducter;


/**
 * @author zhangjinglong
 * @date 2020-04-14-8:26 下午
 * <p>
 * 模拟两个消费者，一个生产者 的生产者，消费者模式
 */

public class Test {

    public static void main(String[] args) {
        Buffer b = new Buffer();

        //生产者线程
        Thread p = new Thread(new Producer(b));
        //消费者线程
        Thread c1 = new Thread(new Consumer(b), "c1");
        Thread c2 = new Thread(new Consumer(b), "c2");

        p.start();
        c1.start();
        c2.start();
    }
}

/**
 * 定义缓存类，容量为10
 */
class Buffer {
    int count = 0;
    int[] buf = new int[10];

    public Buffer() {
    }

    public synchronized int add(int element) {

        while (count == buf.length) {
            try {//注意这里的wait，外层需用while循环的方式，使用if会产生bug
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int res = -1;
        if (count < buf.length && count >= 0) {//资源不满，可以继续往缓存放
            res = buf[count++] = element;
            this.notifyAll();//放完后通知其他阻塞的线程

        }
        return res;
    }

    public synchronized int remove() {

        while (count == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int res = -2;
        res = buf[--count];
        notifyAll();

        return res;//消费者无资源消耗，需要等待返回-2
    }
}

class Producer implements Runnable {

    Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;

    }

    public void produce(int i) {
        if (buffer.add(i) == -1) {
            System.out.println("buffer 已经满了，不能再添加");
        } else {
            System.out.println("Producer 生产第" + buffer.add(i) + "个资源");
            try {
                Thread.sleep(200);//模拟生产业务流程
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            this.produce(i);
        }
    }
}


class Consumer implements Runnable {
    Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void consume() {
        if (buffer.remove() == -2) {
            System.out.println("buffer 已经空了，不能再拿");
        } else {
            System.out.println(Thread.currentThread().getName() + ":Consumer正在消费第" + buffer.remove() + "个资源");
            try {
                Thread.sleep(400);//模拟消费业务流程
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            this.consume();
        }
    }
}

