package mutithread.concurrent;

public class Main {
    public static void main(String[] args) {
        // 主线程结束了，用户线程还在运行，jvm存活

        Thread aa = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "::" + Thread.currentThread().isDaemon());
            while (true) {

            }
        }, "aa");
        //设置守护线程
        aa.setDaemon(true);
        aa.start();
        System.out.println(Thread.currentThread().getName() +  "::" + Thread.currentThread().isDaemon()+" over");
    }


}
