package mutithread.concurrent.chapter17;
import static java.lang.Thread.currentThread;
/**
 * @author zhangjinglong
 * @date 2020-02-21-20:19
 * 对ShareData的使用
 */

public class ReadWriteLockTest {
    //This is the example for read write lock
    private final static String text="This is the example for readwrite lock";

    public static void main(String[] args) {
        //定义共享数据
        final ShareData shareData=new ShareData(50);

        //创建两个线程进行数据写操作
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for(int index=0;index<text.length();index++){
                    try{
                        char c=text.charAt(index);
                        shareData.write(c);
                        System.out.println(currentThread()+" write "+c);

                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        //创建10个线程进行数据读操作
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while(true){
                    try{
                        System.out.println(currentThread()+ " read "+new String(shareData.read()));
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
