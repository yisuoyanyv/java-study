package jobInterview.thread.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangjinglong
 * @date 2020-04-18-4:46 下午
 * 自己模拟一个AQS
 * <p>
 * 4.添加一个acquire方法
 * 获取锁的几种情况：
 * a.第一个线程，不需要排队，不需要等待，不需要队列，直接获取锁
 * b.锁被占用，需要排队，但是当前没有队列，需要新建队列，并且入队
 * c.锁被占用，需要排队，而且队列中已经有其他线程，则直接入队等待
 * 5.根据"道格利"的思路准备 其他的三个方法(tryAquire,acquireQueued,addWaiter)
 */

public class AQS2 {
    //头元素
    private volatile Node head;
    //尾元素
    private volatile Node tail;
    //资源状态（一把锁） 1表示锁被占用 0表示锁空闲
    private volatile int status;

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }


    /**
     * 获取锁
     *
     * @param arg
     */
    public final void acquire(int arg) {
        /**
         * 1.如果tryAcquire返回true，说明已经获取到锁了，则不再执行后面的程序（创建队列，创建Node元素，排队）
         * 2.如果tryAcquire返回false，说明锁被占用，执行后面的的程序进行排队
         */
        if (!tryAcquire(arg) &&
                acquireQueued(addWaiter(), arg))
            //中断当前线程
            Thread.currentThread().interrupt();
    }

    /**
     * 判断传入的Node是否要排队，如果要排队则排队，如果不需要则直接获取锁
     *
     * @param addWaiter
     * @param arg
     * @return 如果排队成功（线程开始等待）返回true，没有等待返回false
     */
    private boolean acquireQueued(Object addWaiter, int arg) {
        return false;
    }

    /**
     * 根据当前的线程，创建一个Node对象，并将该对象放入队列
     *
     * @return
     */
    private Object addWaiter() {
        return null;
    }

    /**
     * 尝试获取锁
     *
     * @param arg
     * @return 如果获取到锁，返回true，否则返回false
     */
    private boolean tryAcquire(int arg) {
        return false;
    }

    private static class Node {
        //指向上一个元素
        private Node prev;
        //指向下一个元素
        private Node next;

        //当前元素维护的线程对象
        Thread thread;
        //状态(1,运行 2.等待 3.取消）
        int threadStatus;
        //定义几个状态常量
        static final int DEFAULT = 0;
        static final int WAIT = 1;
        static final int CANNELED = -1;
        //准备几个构造方法

        public Node() {
        }

        public Node(Thread thread) {
            this.thread = thread;
        }

        public Node(Thread thread, int threadStatus) {
            this(thread);
            this.threadStatus = threadStatus;
        }


    }

    public static void main(String[] args) {

    }
}
