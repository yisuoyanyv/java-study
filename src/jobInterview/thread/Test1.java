package jobInterview.thread;

/**
 * @author zhangjinglong
 * @date 2020-04-16-8:39 下午
 * <p>
 * Java创建线程的三种方式
 */

public class Test1 {
    public static void main(String[] args) {
        //创建线程对象并且启动
        Thread ta = new ThreadA();
        Thread tb = new Thread(new ThreadB());

        //使用lamda表达式实现一个线程
        Thread tm = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Lamda");
            }
        });
        ta.start();
        tb.start();
        tm.start();
    }

}

/**
 * 通过继承Thread创建一个线程
 */
class ThreadA extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("ThreadA");
        }
    }
}

/**
 * 通过实现Runnable实现
 */
class ThreadB implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("ThreadB");
        }
    }
}