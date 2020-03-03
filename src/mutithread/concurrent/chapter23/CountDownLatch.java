package mutithread.concurrent.chapter23;

import java.util.concurrent.TimeUnit;

/**
 * 无限等待门阀打开的Latch实现
 * 当limit>0,调用await方法的线程将会进入无限的等待
 * @author zhangjinglong
 * @date 2020-02-22-21:14
 */

public class CountDownLatch extends Latch {
    public CountDownLatch(int limit){
        super(limit);
    }

    @Override
    public void await() throws InterruptedException {
        synchronized (this){
            //当limit>0时，当前线程进入阻塞状态
            while(limit>0){
                this.wait();
            }
        }
    }

    @Override
    public void await(TimeUnit unit, long time) throws InterruptedException, WaitTimeoutException {
        if(time<=0){
            throw new IllegalArgumentException("The time is invalid.");
        }
        long remainingNanos=unit.toNanos(time);//将time转换为纳秒
        //等待任务在endNanos纳秒后超时
        final long endNanos=System.nanoTime()+remainingNanos;
        synchronized (this){
            while(limit>0){
                //如果超时则抛出WaitTimeoutException异常
                if(TimeUnit.NANOSECONDS.toMillis(remainingNanos)<0){
                    throw new WaitTimeoutException("The wait time over specify time.");
                }
                //等待remainingNanos,在等待的过程中可能会被中断，需要重新计算remainingNanos
                this.wait(TimeUnit.NANOSECONDS.toMillis(remainingNanos));
                remainingNanos=endNanos-System.nanoTime();
            }
        }

    }

    @Override
    public void countDown() {
        synchronized (this){
            if(limit<=0){
                throw new IllegalStateException(" all of task already arrived");
            }
            //使limit减一，并且通知阻塞线程
            limit--;
            this.notifyAll();
        }
    }

    @Override
    public int getUnarrived() {
        //返回还有多少线程还未完成任务
        return limit;
    }
}
