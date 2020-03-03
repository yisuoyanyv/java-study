package mutithread.concurrent.chapter08;

/**
 * 提供了创建线程的接口
 */
@FunctionalInterface
public interface ThreadFactory {
    Thread createThread(Runnable runnable);

}
