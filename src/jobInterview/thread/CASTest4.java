package jobInterview.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author zhangjinglong
 * @date 2020-04-18-1:03 下午
 * <p>
 * CAS的  ABA问题解决
 * <p>
 * CAS 不加锁，但是消耗CPU资源，
 * 每个线程占用资源的时间很长
 * <p>
 * 适用场景：不怎么频繁修改，不是长时间操作，很少产生自旋，乐观锁
 */

public class CASTest4 {


    public static void main(String[] args) throws InterruptedException {

//        AtomicInteger ai=new AtomicInteger(1);
        AtomicStampedReference<Integer> asr = new AtomicStampedReference<>(1, 1);
        new Thread(() -> {
            //修改数据
            //取出当前值
            int ex = asr.getReference();
            //计算
            int n = ex + 1;
            //取出当前的版本（印记）
            int stamp = asr.getStamp();
            //新版本
            int newStamp = stamp + 1;
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = asr.compareAndSet(ex, n, stamp, newStamp);
            System.out.println("子线程修改结果：" + b);
        }).start();

        TimeUnit.MILLISECONDS.sleep(5);
        //将1修改为2
        asr.compareAndSet(asr.getReference(), asr.getReference() + 1, asr.getStamp(), asr.getStamp() + 1);
        System.out.println("主线程：" + asr.getReference());
        //将2修改为1
        asr.compareAndSet(asr.getReference(), asr.getReference() - 1, asr.getStamp(), asr.getStamp() + 1);
        System.out.println("主线程：" + asr.getReference());


    }
}
