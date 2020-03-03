package mutithread.concurrent.chapter01;

/**
 * @author zhangjinglong
 * @date 2020-02-15-21:44
 */

public class TicketWindow extends Thread {
    //柜台名称
    private final String name;

    //最多受理50笔业务
    private static final int MAX=50;

    private static int index=1; //使用static 修饰线程共享资源，待改进

    public TicketWindow(String name){
        this.name=name;
    }

    @Override
    public void run() {
        while(index<=MAX){
            System.out.println("柜台:"+name+" 当前的号码是："+(index++));
        }
    }

    public static void main(String[] args) {
        TicketWindow ticketWindow1=new TicketWindow("一号出号机");
        ticketWindow1.start();

        TicketWindow ticketWindow2=new TicketWindow("二号出号机");
        ticketWindow2.start();

        TicketWindow ticketWindow3=new TicketWindow("三号出号机");
        ticketWindow3.start();

        TicketWindow ticketWindow4=new TicketWindow("四号出号机");
        ticketWindow4.start();
    }
}
