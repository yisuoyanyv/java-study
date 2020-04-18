package jobInterview.thread.aqs;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author zhangjinglong
 * @date 2020-04-18-4:46 下午
 * 自己模拟一个AQS
 * <p>
 * 6.实现tryAcquire方法
 * a.判断锁释放是空闲状态，判断status的值
 * b.如果是空闲状态，则判断自己是否需要排队。如果不需要排队就直接获取锁（使用Unsafe修改status），如果需要排队就返回false
 * c.如果不是空闲状态，则判断自己是否重入，如果是重入，则status+1，并且返回true
 * d.其他情况全部返回false
 * 判断是否需要排队方法 hasQueuedPredecessors（）
 * 通过修改状态获取锁的方法compareAndSetState（）
 */

public class AQS3 {
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

    //当前占有锁的线程对象
    private Thread exclusiveOwnerThread;

    public Thread getExclusiveOwnerThread() {
        return exclusiveOwnerThread;
    }

    public void setExclusiveOwnerThread(Thread exclusiveOwnerThread) {
        this.exclusiveOwnerThread = exclusiveOwnerThread;
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
     * 获取锁 原子操作完成锁状态的修改
     *
     * @param i
     * @param arg
     * @return 获取成功返回true，不成功。。。
     */
    private boolean compareAndSetState(int i, int arg) {
        return unsafe.compareAndSwapLong(this, statusOffset, i, arg);

    }

    /**
     * 判断自己是否要排队
     *
     * @return 如果返回true，说明要排队，如果返回false，说明可以直接获取锁
     */
    private boolean hasQueuedPredecessors() {
        //h==t
        //  h=null t=null,队列还没有创建
        //  h=t,队列中只有一个元素。尝试获取锁。
        //h!=t
        //  h!=null t=null  h.next==null 初始化
        //  h!=null   h.next.thread==thread 可以获取锁，当前的线程就是head.next

        Node h = head;//头元素
        Node t = tail;//尾元素
        Node s;//头元素的下一个元素
        return h != t && (((s = h.next) == null) || (s.thread != Thread.currentThread()));
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
        //获取当前线程对象
        Thread thread = Thread.currentThread();
        //获取当前锁的状态
        int c = getStatus();
        if (c == 0) {//当前的锁处于释放状态
            //判断自己是否要排队，如果不需要排队，则直接获取锁，如果需要排队就返回false；
            if (!hasQueuedPredecessors() && compareAndSetState(0, arg)) {
                //获取锁成功
                setExclusiveOwnerThread(thread);//设置占有资源的线程为当前线程
                System.out.println(thread.getName() + "获取锁成功！");
                return true;
            }

        } else if (thread == getExclusiveOwnerThread()) {
            //当前尝试获取锁的线程就是占有锁的线程（锁重入）
            c = getStatus() + arg;
            setStatus(c);
            return true;
        }
        return false;
    }

    //创建一个Unsafe对象
    private static Unsafe unsafe;
    //记录status的偏移量
    private static long statusOffset = 0;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
            //获取status属性的偏移量
            statusOffset = unsafe.staticFieldOffset(AQS3.class.getDeclaredField("status"));

        } catch (Exception e) {
            e.printStackTrace();
        }
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
