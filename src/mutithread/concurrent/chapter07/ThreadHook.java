package mutithread.concurrent.chapter07;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangjinglong
 * @date 2020-02-17-20:45
 */

public class ThreadHook {
    public static void main(String[] args) {
        //为程序注入钩子线程
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                try{
                    System.out.println("The hook thread 1 is running. ");
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("The hook thread 1 will exit. ");
            }
        });


        //钩子线程可以注册多个
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                try{
                    System.out.println("The hook thread 2 is running. ");
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("The hook thread 2 will exit. ");
            }
        });


        System.out.println("The programe will is stopping.");
    }
}
