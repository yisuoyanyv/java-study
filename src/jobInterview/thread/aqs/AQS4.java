package jobInterview.thread.aqs;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author zhangjinglong
 * @date 2020-04-18-4:46 下午
 * 自己模拟一个AQS
 * <p>
 * 7.实现addWaiter方法
 * a.使用当前线程创建一个新的Node对象
 * b.判断尾元素是否存在，如果存在就将当前元素加入队列，并且将当前元素修改为尾元素（原子操作unsafe)
 * c.如果尾元素不存在，则说明队列本身就不存在，但是又没有获取到锁，说明目前有一个线程正在占用锁，没有其他线程等待
 * 创建首元素，将当前新元素链接在其后
 * 设置首元素也需要原子操作（unsafe)
 */

public class AQS4 {
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

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
            //获取status属性的偏移量
            statusOffset = unsafe.staticFieldOffset(AQS4.class.getDeclaredField("status"));
            //获取tail属性的偏移量
            tailOffset = unsafe.objectFieldOffset(AQS4.class.getDeclaredField("tail"));
            //获取head属性的偏移量
            headOffset = unsafe.objectFieldOffset(AQS4.class.getDeclaredField("head"));

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
