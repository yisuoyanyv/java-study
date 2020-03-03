package mutithread.concurrent.chapter01;

import sun.security.krb5.internal.Ticket;

/**
 * @author zhangjinglong
 * @date 2020-02-15-22:24
 */

public class TicketWindowRunnable implements Runnable {
    private int index=1;//不做static修饰   TODO 存在线程安全问题
    private final static int MAX=5000;

    @Override
    public void run() {
        while(index<=MAX){
            System.out.println(Thread.currentThread()+" 的号码是："+(index++));
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        //四个叫号线程使用了同一个Runnable接口
        final TicketWindowRunnable task=new TicketWindowRunnable();

        Thread windowThread1=new Thread(task,"一号窗口");
        Thread windowThread2=new Thread(task,"二号窗口");
        Thread windowThread3=new Thread(task,"三号窗口");
        Thread windowThread4=new Thread(task,"四号窗口");

        windowThread1.start();
        windowThread2.start();
        windowThread3.start();
        windowThread4.start();
    }
}
