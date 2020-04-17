package jobInterview.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangjinglong
 * @date 2020-04-17-10:14 下午
 * <p>
 * Condition的精准唤醒
 */

public class Test14 {
    public static void main(String[] args) {
        Coder coder = new Coder();
        //编写源程序
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    coder.step1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //编译源程序
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    coder.step2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //执行字节码
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    coder.step3();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

class Coder {
    //表示当前的状态
    int status = 1;//表示步骤

    //准备一把锁
    Lock lock = new ReentrantLock();
    //获取监视器
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public void step1() throws InterruptedException {
        lock.lock();
        if (status != 1) {
            condition1.await();
        }
        System.out.println("1>编写源代码");
        //修改步骤
        status = 2;
        //唤醒第二个监视器
        condition2.signal();
        lock.unlock();
    }

    public void step2() throws InterruptedException {
        lock.lock();
        if (status != 2) {
            condition2.await();
        }
        System.out.println("2>将源代码编译为字节码文件");
        //修改步骤
        status = 3;
        //唤醒第三个监视器
        condition3.signal();
        lock.unlock();
    }

    public void step3() throws InterruptedException {
        lock.lock();
        if (status != 3) {
            condition3.await();
        }
        System.out.println("3>执行字节码文件");
        //修改步骤
        status = 1;
        condition1.signal();
        lock.unlock();
    }
}