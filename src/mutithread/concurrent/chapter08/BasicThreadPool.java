package mutithread.concurrent.chapter08;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangjinglong
 * @date 2020-02-17-22:16
 */

public class BasicThreadPool extends Thread implements ThreadPool {
    //初始化线程数量
    private final int initSize;

    //线程池最大线程数量
    private final int maxSize;

    //线程池核心线程数量
    private final int coreSize;

    //当前活跃的线程数量
    private int activeCount;

    //创建线程所需要的工厂
    private final ThreadFactory threadFactory;


    //任务队列
    private final RunnableQueue runnableQueue;

    //线程池是否已经被shutdown
    private  volatile boolean isShutdown=false;

    //工作线程队列
    private final Queue<ThreadTask> threadQueue=new ArrayDeque<>();

    private final static DenyPolicy DEFAULT_DENY_POLICY=new DenyPolicy.DiscardDenyPolicy();

    private final static ThreadFactory DEFAULT_THREAD_FACTORY=new DefaultThreadFactory();

    private final long keepAliveTime;

    private final TimeUnit timeUnit;

    //构造时需要传入的参数：初始的线程数量，最大的线程数量，核心线程数量，任务队列的最大数量
    public BasicThreadPool(int initSize,int maxSize,int coreSize,int queueSize){
        this(initSize,maxSize,coreSize,DEFAULT_THREAD_FACTORY,queueSize,DEFAULT_DENY_POLICY
                ,10,TimeUnit.SECONDS);
    }
    //构造时需要传入的参数,该构造函数需要的参数比较多
    public BasicThreadPool(int initSize,int maxSize,int coreSize,ThreadFactory threadFactory,int queueSize,
                           DenyPolicy denyPolicy,long keepAliveTime,TimeUnit timeUnit){
        this.initSize=initSize;
        this.maxSize=maxSize;
        this.coreSize=coreSize;
        this.threadFactory=threadFactory;
        this.runnableQueue=new LinkedRunnableQueue(queueSize,denyPolicy,this);
        this.keepAliveTime=keepAliveTime;
        this.timeUnit=timeUnit;
        this.init();

    }

    //初始化时，先创建initSize个线程
    private void init(){
        start(); //TODO
        for (int i = 0; i < initSize; i++) {
            newThread();
        }
    }


    private void newThread(){
        //创建任务线程，并且启动
        InternalTask internalTask=new InternalTask(runnableQueue);
        Thread thread=this.threadFactory.createThread(internalTask);
        ThreadTask threadTask=new ThreadTask(thread,internalTask);
        threadQueue.offer(threadTask);
        this.activeCount++;
        thread.start();


    }

    private void removeThread(){
        //从线程池中移除某个线程
        ThreadTask threadTask=threadQueue.remove();
        threadTask.internalTask.stop();
        this.activeCount--;
    }

    @Override
    public void run() {
        //run方法继承自Thread，主要用于维护线程数量，比如扩容，回收等工作
        while(!isShutdown && !isInterrupted()){
            try{
                timeUnit.sleep(keepAliveTime);
            }catch (InterruptedException e){
                isShutdown=true;
                break;
            }
            synchronized (this){
                if(isShutdown)
                    break;

                //当前的队列中有任务尚未处理，并且activeCount<coreSize则继续扩容
                if(runnableQueue.size()>0 && activeCount<coreSize){
                    for (int i = initSize; i < coreSize; i++) {
                        newThread();
                    }
                    //continue的目的在于不想让线程的扩容直接达到maxsize
                    continue;
                }

                //当前的队列中有任务尚未处理，并且activeCount<maxSize,则继续扩容
                if(runnableQueue.size()>0 && activeCount<maxSize){
                    for (int i = coreSize; i <maxSize ; i++) {
                        newThread();
                    }
                }

                //如果队列中没有任务，则需要回收，回收至coreSize即可
                if(runnableQueue.size()==0 && activeCount>coreSize){
                    for (int i = coreSize; i < activeCount; i++) {
                        removeThread();
                    }
                }
            }
        }

    }

    @Override
    public void execute(Runnable runnable) {
        if(this.isShutdown)
            throw new IllegalStateException("The thread pool is destroy");
        //提交任务只是简单地往任务队列中插入Runnable
        this.runnableQueue.offer(runnable);
    }

    @Override
    public void shutdown() {
        synchronized (this){
            if(isShutdown) return;
            isShutdown=true;
            threadQueue.forEach(threadTask -> {
                threadTask.internalTask.stop();
                threadTask.thread.interrupt();
            });
            this.interrupt();
        }
    }

    @Override
    public int getInitSize() {
        if(isShutdown)
            throw new IllegalStateException("The thread pool is destroy");
        return this.initSize;
    }

    @Override
    public int getMaxSize() {
        if(isShutdown)
            throw new IllegalStateException("The thread pool is destroy");
        return this.maxSize;
    }

    @Override
    public int getCoreSize() {
        if(isShutdown)
            throw new IllegalStateException("The thread pool is destroy");
        return this.coreSize;
    }

    @Override
    public int getQueueSize() {
        if(isShutdown)
            throw new IllegalStateException("The thread pool is destroy");
        return runnableQueue.size();
    }

    @Override
    public int getActiveCount() {
        if(isShutdown)
            throw new IllegalStateException("The thread pool is destroy");
        return this.activeCount;
    }

    @Override
    public boolean isShutdown() {
        return this.isShutdown;
    }

    //ThreadTask只是 InternalTask和Thread的一个组合
    private static class ThreadTask{
        public ThreadTask(Thread thread,InternalTask internalTask){
            this.thread=thread;
            this.internalTask=internalTask;
        }

        Thread thread;
        InternalTask internalTask;
    }

    private static class DefaultThreadFactory implements ThreadFactory{
        private static final AtomicInteger GROUP_COUNTER=new AtomicInteger(1);
        private static final ThreadGroup group=new ThreadGroup("MyThreadPool-"+GROUP_COUNTER.getAndDecrement());
        private static final AtomicInteger COUNTER=new AtomicInteger(0);

        @Override
        public Thread createThread(Runnable runnable) {
            return new Thread(group,runnable,"thread-pool-"+COUNTER.getAndDecrement());
        }
    }
}
