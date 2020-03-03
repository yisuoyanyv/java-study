package mutithread.concurrent.chapter03;

/**
 * @author zhangjinglong
 * @date 2020-02-16-14:08
 */

public class CurrentThread {
    public static void main(String[] args) {
        Thread thread=new Thread(){
            @Override
            public void run() {
                //always true
                System.out.println(Thread.currentThread()==this);
            }
        };
        thread.start();

        String name=Thread.currentThread().getName();
        System.out.println("main".equals(name));
    }
}
