package mutithread.concurrent.chapter07;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangjinglong
 * @date 2020-02-17-17:37
 */

public class CaptureThreadException {
    public static void main(String[] args) {

        //1.设置回调接口
        Thread.setDefaultUncaughtExceptionHandler((t,e)->{
            System.out.println(t.getName()+" occur exception" );
            e.printStackTrace();
        });

        final Thread thread=new Thread(()->{
            try{
                TimeUnit.SECONDS.sleep(2);
            }catch (InterruptedException e){

            }
            //2.这里会出现unchecked 异常
            System.out.println(1/0);
        },"Test-Thread");
        thread.start();
    }
}
