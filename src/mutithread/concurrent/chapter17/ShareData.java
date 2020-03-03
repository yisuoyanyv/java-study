package mutithread.concurrent.chapter17;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * 都写锁的使用
 * @author zhangjinglong
 * @date 2020-02-21-20:04
 */

public class ShareData {
    //定义共享数据（资源）
    private final List<Character>  container=new ArrayList<>();

    //构造ReadWriteLock
    private final ReadWriteLock readWriteLock=ReadWriteLock.readWriteLock();
    //创建读取锁
    private  final Lock readLock=readWriteLock.readLock();

    //创建写入锁
    private final Lock writeLock=readWriteLock.writeLock();

    private final int length;

    public ShareData(int length){
        this.length=length;
        for (int i = 0; i < length; i++) {
            container.add(i,'c');
        }
    }

    public char[] read() throws InterruptedException{
        try{
            //首先使用读锁进行lock
            readLock.lock();
            char[] newBuffer=new  char[length];
            for (int i = 0; i < length; i++) {
                newBuffer[i]=container.get(i);
            }
            slowly();
            return newBuffer;
        }finally {
            //当操作解锁之后，将锁释放
            readLock.unlock();
        }
    }

    public void write(char c) throws InterruptedException{
        try{
            //使用写锁进行lock
            writeLock.lock();
            for (int i = 0; i < length; i++) {
                this.container.add(i,c);
            }
            slowly();

        }finally {
            //当所有的操作都完成后，对写锁进行释放
            writeLock.unlock();
        }
    }

    //简单模拟操作的耗时
    private void slowly(){
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
