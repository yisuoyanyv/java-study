package mutithread.concurrent.chapter17;

//虽然ReadWriteLock 名字中有lock，但不是lock，主要用于创建 read lock和write lock的
public interface ReadWriteLock {
    //创建 reader锁
    Lock readLock();

    //创建write锁
    Lock writeLock();

    //获取当前有多少线程正在执行写操作  最多是一个
    int getWritingWriters();

    //获取当前有多少线程正在等待获取写入锁  由于获得写锁而导致阻塞
    int getWaitingWriters();

    //获取当前有多少线程正在进行读的操作
    int getReadingReaders();

    //工厂方法，创建ReadWriteLock
    static ReadWriteLock readWriteLock(){
        return new ReadWriteLockImpl();
    }

    //工厂方法，创建ReadWriteLock，并且传入 preferWriter「
    static ReadWriteLock readWriteLock(boolean preferWrite){
        return  new ReadWriteLockImpl(preferWrite);
    }
}
