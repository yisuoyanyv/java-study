package mutithread.concurrent.chapter15;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangjinglong
 * @date 2020-02-20-21:01
 */

public class ObservableThreadTest1 {
    public static void main(String[] args) {
        Observable observableThread=new ObservableThread<>(()->{
            try{
                TimeUnit.SECONDS.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("finished done");
            return null;
        });

        observableThread.start();
    }
}
