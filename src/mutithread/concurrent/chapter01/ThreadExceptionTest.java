package mutithread.concurrent.chapter01;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangjinglong
 * @date 2020-02-15-21:26
 */

public class ThreadExceptionTest {
    public static void main(String[] args) {
        Thread thread=new Thread(){
            @Override
            public void run() {
                try{
                    TimeUnit.SECONDS.sleep(2);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        try{
            TimeUnit.SECONDS.sleep(3);//休眠主要是保证thread结束生命周期

        }catch (InterruptedException e){
            e.printStackTrace();
        }
        thread.start();//企图重新激活该线程
        //已经结束的线程不能被重新start，否则出现java.lang.IllegalThreadStateException
    }
    public static void main1(String[] args) {
        Thread thread=new Thread(){
            @Override
            public void run() {
                try{
                    TimeUnit.SECONDS.sleep(2);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        thread.start();//启动线程
        thread.start();//再次启动
        //一个Thread对象start两次会出现java.lang.IllegalThreadStateException，导致程序退出
    }
}
