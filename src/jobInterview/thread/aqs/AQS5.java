package jobInterview.thread.aqs;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author zhangjinglong
 * @date 2020-04-18-4:46 下午
 * 自己模拟一个AQS
 * <p>
 * 8.acquireQueued方法
 * a.执行这个方法，说明node已经入队，但是未必是等待状态，这个方法就是要让线程等待或者获取锁
 * b.判断当前元素的上一个元素是否是head元素，如果是，就尝试获取锁，如果获取成功，就把当前元素置为head元素，返回true
 * c.如果上一个元素不是head元素，或者获取锁失败，就判断当前元素是否需要等待，如果需要则等待，如果不需要就自旋
 * 这里需要实现两个方法 shouldParkAfterFailedAcquire()  parkAndCheckInterrupt()
 */

public class AQS5 {
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
     * @param node
     * @param arg
     * @return 如果排队成功（线程开始等待）返回true，没有等待返回false
     */
    private boolean acquireQueued(Node node, int arg) {
        //自旋操作
        while (true) {
            //判断上一个元素是否是head元素
            if (node.prev == head && tryAcquire(arg)) {
                //如果上个元素是head元素，则尝试获取锁，如果获取成功
                setHead(node);//将当前元素设置为头元素 头元素就被排除在队列外
                //修改当前node的状态（原子操作）
                compareAndSetNodeStatus(node, node.threadStatus, Node.DEFAULT);
                return false;//最终的出口
            }
            //判断是否要排队等待
            if (shouldParkAfterFailedAcquire(node) && parkAndCheckInterrupt(node)) {
                return true;
            }

        }
    }

    /**
     * 排队等待
     *
     * @param node
     * @return
     */
    private boolean parkAndCheckInterrupt(Node node) {
        //修改node的状态为WAIT
        compareAndSetNodeStatus(node, node.threadStatus, Node.WAIT);
        //中断当前的线程
        unsafe.park(false, 0);
        return Thread.interrupted();
    }

    /**
     * 判断当前线程是否需要排队等待
     *
     * @param node
     * @return
     */
    private boolean shouldParkAfterFailedAcquire(Node node) {
        //如果上一个元素是头元素，是不需要等待的
        //如果上一个元素本身就是等待状态，当前线程就必须等待了
        //如果上一个元素不是头元素，也不是等待状态，也没有获取锁，当前线程就等待
        //获取上个元素
        Node prev = node.prev;
        if (prev != null && prev != head && prev.threadStatus != Node.CANNELED) {
            //如果上个元素不是头元素，不是取消状态，就等待
            return true;
        }

        //判断上个元素是否是取消状态，如果是，则将其从队列中去除
        if (prev != null && prev.threadStatus == Node.CANNELED) {
            do {
                node.prev = prev = prev.prev;
            } while (prev.threadStatus == Node.CANNELED);
            prev.next = node;
        }

        return false;
    }

    /**
     * 原子操作修改node的状态
     *
     * @param node
     * @param threadStatus
     * @param aDefault
     */
    private boolean compareAndSetNodeStatus(Node node, int threadStatus, int aDefault) {
        return unsafe.compareAndSwapObject(node, threadStatusOffset, threadStatus, aDefault);
    }

    /**
     * 设置头元素
     *
     * @param node
     */
    private void setHead(Node node) {
        head = node;
        head.thread = null;
        head.prev = null;
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
    private Node addWaiter() {
        //根据当前线程创建一个node
        Node node = new Node(Thread.currentThread());
        //取出队列的尾元素
        Node prev = tail;//新元素的上一元素就是队尾元素
        if (prev != null) {
            //队尾元素存在
            node.prev = prev;//新元素的上一个元素就是队尾元素
            //原子操作替换队尾元素
            if (compareAndSetTail(prev, node)) {
                prev.next = node;
                return node;
            }
        }
        //说明入队失败（1、队列不存在 2、队列正在初始化，还没有队尾元素）
        enq(node);
        return node;
    }

    /**
     * 自旋的完成入队操作
     * 如果是一个线程来创建队列，则：
     * 循环第一次，创建了队头元素，
     * 循环第二次，创建了第二个元素，第二个元素就是新元素，新元素入队。
     *
     * @param node
     * @return
     */
    private Node enq(Node node) {
        while (true) {
            //获取队尾元素
            Node t = tail;
            if (t == null) {//队列没有初始化或者队列正在初始化
                //如果队列不存在，创建新队列。 先放堆头元素
                //原子操作设置队头元素，队头元素为空元素
                if (compareAndSetHead(new Node())) {
                    //如果创建成功，将head和tail指向同一元素
                    tail = head;
                }
            } else {
                //原子操作替换队尾元素
                if (compareAndSetTail(t, node)) {
                    node.prev = t;
                    t.next = node;
                    return node;
                }
            }
        }

    }

    /**
     * 原子操作创建队头元素
     *
     * @param node
     * @return 成功返回true，不成功返回false
     */
    private boolean compareAndSetHead(Node node) {
        //队头必须是null才能创建成功
        return unsafe.compareAndSwapObject(this, headOffset, null, node);
    }

    /**
     * 原子操作实现队尾元素的替换
     *
     * @param prev
     * @param node
     * @return
     */
    private boolean compareAndSetTail(Node prev, Node node) {
        return unsafe.compareAndSwapObject(this, tailOffset, prev, node);
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

    //队尾元素属性tail偏移量
    private static long tailOffset = 0;

    //队头元素属性head偏移量
    private static long headOffset = 0;
    //获取node属性threadStatus的偏移量
    private static long threadStatusOffset = 0;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
            //获取status属性的偏移量
            statusOffset = unsafe.staticFieldOffset(AQS5.class.getDeclaredField("status"));
            //获取tail属性的偏移量
            tailOffset = unsafe.objectFieldOffset(AQS5.class.getDeclaredField("tail"));
            //获取head属性的偏移量
            headOffset = unsafe.objectFieldOffset(AQS5.class.getDeclaredField("head"));
            //获取threadStatusOffset属性的偏移量
            threadStatusOffset = unsafe.objectFieldOffset(Node.class.getDeclaredField("threadStatus"));

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
