package mutithread.concurrent.chapter03;

/**
 * 测试 sleep 方法的实际休眠时间
 * @author zhangjinglong
 * @date 2020-02-16-13:43
 */

public class ThreadSleep {
    public static void main(String[] args) {
        new Thread(()->{
            long startTime=System.currentTimeMillis();
            sleep(2_000L);
            long endTime=System.currentTimeMillis();
            System.out.println(String.format("Total spend %d ms",(endTime-startTime)));
        }).start();

        long startTime=System.currentTimeMillis();
        sleep(3_000L);
        long endTime=System.currentTimeMillis();
        System.out.println(String.format("Main thread total spend %d ms",(endTime-startTime)));
    }

    private static void sleep(long ms){
        try{
            Thread.sleep(ms);
        }catch (InterruptedException e){

        }
    }
}
