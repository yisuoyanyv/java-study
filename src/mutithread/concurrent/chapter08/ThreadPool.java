package mutithread.concurrent.chapter08;

/**
 * 定义一个线程池应该具备的基本操作和方法
 */
public interface ThreadPool {

    //提交任务到线程池
    void execute(Runnable runnable);

    //关闭线程池
    void shutdown();

    //获取线程池到初始化大小
    int getInitSize();

    //获取线程池最大的线程数
    int getMaxSize();

    //获取线程池的核心线程数量
    int getCoreSize();

    //获取线程池中用于缓存任务队列的大小
    int getQueueSize();

    //获取线程池中活跃线程的数量
    int getActiveCount();

    //查看线程池是否已经被shutdow
    boolean isShutdown();
}
