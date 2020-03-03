package mutithread.concurrent.chapter05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

/**
 * @author zhangjinglong
 * @date 2020-02-16-23:24
 */

public class BooleanLock implements Lock {

    private Thread currentThread;
    private boolean locked=false;
    private final List<Thread> blockedList=new ArrayList<>();

    @Override
    public void lock() throws InterruptedException {
        synchronized (this){
            //暂存当前线程

            while(locked){
                final Thread tempThread=Thread.currentThread();
                try{
                    if(!blockedList.contains(tempThread))
                        blockedList.add(tempThread);
                    this.wait();
                }catch (InterruptedException e){
                    //如果当前线程在wait时被中断，则从blockedList中将其删除，避免内存泄露
                    blockedList.remove(tempThread);
                    //继续抛出中断异常
                    throw e;
                }
            }

            blockedList.remove(Thread.currentThread());
            this.locked=true;
            this.currentThread=Thread.currentThread();//记录获取锁的线程
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this){
            if(mills<=00){
                this.lock();
            }
            else{
                long remainingMills=mills;
                long endMills=System.currentTimeMillis()+remainingMills;
                while(locked){
                    if(remainingMills<=0)
                        throw new TimeoutException("can not get the lock during "+mills);
                    if(!blockedList.contains(Thread.currentThread()))
                        blockedList.add(Thread.currentThread());
                    this.wait(remainingMills);
                    remainingMills=endMills-System.currentTimeMillis();
                }

                blockedList.remove(Thread.currentThread());
                this.locked=true;
                this.currentThread=Thread.currentThread();


            }
        }
    }

    @Override
    public void unlock() {

        synchronized (this){
            if(currentThread==Thread.currentThread()){
                this.locked=false;
                Optional.of(Thread.currentThread().getName()+" release the lock.")
                        .ifPresent(System.out::println);
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return Collections.unmodifiableList(blockedList);
    }
}
