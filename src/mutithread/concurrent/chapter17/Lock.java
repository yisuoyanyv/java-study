package mutithread.concurrent.chapter17;

/**
 * Lock 接口定义了锁的基本操作，加锁和解锁，
 * 显式锁的操作强烈建议与try finally语句块一起使用
 */
public interface Lock {

    //获取显式锁，没有获得锁的线程将被阻塞
    void lock() throws InterruptedException;


    //释放获取的锁
    void unlock();
}
