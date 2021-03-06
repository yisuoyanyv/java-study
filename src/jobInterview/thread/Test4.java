package jobInterview.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangjinglong
 * @date 2020-04-16-9:24 下午
 * <p>
 * synchronized 实例方法，锁的是当前对象 this
 */

public class Test4 {
    public static void main(String[] args) throws InterruptedException {
        Person p = new Person();

        //启动线程执行阻塞方法
        new Thread(() -> {
            try {
                p.sayHello();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.MILLISECONDS.sleep(1);

        //启动线程执行非阻塞方法
        new Thread(() -> {
            p.sayHi();
        }).start();
    }
}

class Person {
    public synchronized void sayHello() throws InterruptedException {
        System.out.println("Hello");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("二狗！");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("吃了么？");
    }

    public synchronized void sayHi() {
        System.out.println("Hi!今天是个好日纸");
    }
}