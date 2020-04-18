package jobInterview.thread.aqs;

/**
 * @author zhangjinglong
 * @date 2020-04-18-4:46 下午
 * 自己模拟一个AQS
 */

public class AQS1 {
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
