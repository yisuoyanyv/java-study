package mutithread.concurrent.chapter17;

/**
 * @author zhangjinglong
 * @date 2020-02-21-00:33
 */
//ReadLock 被设置为包可见
class ReadLock  implements Lock{
    private final ReadWriteLockImpl readWriteLock;

    ReadLock(ReadWriteLockImpl readWriteLock){
        this.readWriteLock=readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        //使用Mutex作为锁
        synchronized (readWriteLock.getMutex()){
            //若此时有线程在进行写操作，或者与线程在等待并且偏向锁的标识为true时
            //就会无法获得读锁，只能被挂起
            while(readWriteLock.getWritingWriters()>0
            || (readWriteLock.getPreferWriter() && readWriteLock.getWaitingWriters()>0)){
                readWriteLock.getMutex().wait();
            }
            //成功获得读锁，并且使readingReader的数量增加
            readWriteLock.incrementReadingReaders();
        }
    }

    @Override
    public void unlock() {
        //使用Mutex作为锁，并且进行同步
        synchronized (readWriteLock.getMutex()){
            //释放锁的过程就是使得当前reading的数量减一
            //将perferwriter设置为true，可以使得writer线程获得更多的机会
            //通知唤醒与Mutex关联monitor waitset中的线程
            readWriteLock.decrementReadingReaders();
            readWriteLock.changePrefer(true);
            readWriteLock.getMutex().notifyAll();
        }
    }
}
