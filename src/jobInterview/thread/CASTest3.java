package jobInterview.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangjinglong
 * @date 2020-04-18-1:03 下午
 * <p>
 * CAS的ABA问题
 */

public class CASTest3 {


    public static void main(String[] args) throws InterruptedException {

        AtomicInteger ai = new AtomicInteger(1);
        new Thread(() -> {
            //修改数据
            int ex = ai.get();
            int n = ex + 1;
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = ai.compareAndSet(ex, n);
            System.out.println("子线程修改结果：" + b);
        }).start();

        TimeUnit.MILLISECONDS.sleep(5);
        //将1修改为2
        ai.compareAndSet(ai.get(), ai.get() + 1);
        System.out.println("主线程：" + ai.get());
        //将2修改为1
        ai.compareAndSet(ai.get(), ai.get() - 1);
        System.out.println("主线程：" + ai.get());


    }
}
