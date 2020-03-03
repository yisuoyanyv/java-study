package mutithread.concurrent.chapter05;

/**
 * @author zhangjinglong
 * @date 2020-02-16-23:48
 */
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

import static java.util.concurrent.ThreadLocalRandom.current;
import static java.lang.Thread.currentThread;
public class BooleanLockTest {
    //定义BooleanLock
    private final Lock lock=new BooleanLock();

    //使用 try...finally语句块确保lock每次能被正确释放

    public void syncMethod(){

        try{
            //加锁
            lock.lock();

            int randomInt=current().nextInt(10);
            System.out.println(currentThread()+" get the lock.");
            TimeUnit.SECONDS.sleep(randomInt);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            //释放锁
            lock.unlock();
        }


    }

    public void syncMethodTimeoutable(){
        try{
            lock.lock(1000);
            System.out.println(currentThread()+ " get the lock.");
            int randomInt=current().nextInt(10);
            TimeUnit.SECONDS.sleep(randomInt);
        }catch (InterruptedException | TimeoutException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        BooleanLockTest blt=new BooleanLockTest();
//        IntStream.range(0,10).mapToObj(i->new Thread(blt::syncMethod))
//                .forEach(Thread::start);

        new Thread(blt::syncMethod,"T1").start();
        TimeUnit.MILLISECONDS.sleep(2);
        Thread t2=new Thread(blt::syncMethodTimeoutable,"T2");
        t2.start();
        TimeUnit.MILLISECONDS.sleep(10);


    }


}
