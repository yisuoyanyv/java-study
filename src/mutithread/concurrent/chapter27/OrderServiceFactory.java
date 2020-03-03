package mutithread.concurrent.chapter27;

/**
 * 将ActiveMessageQueue定义成static的目的是，保持在整个JVM进程中是唯一的，
 * 并且ActiveDaemonThread会在此刻启动
 * @author zhangjinglong
 * @date 2020-02-23-22:16
 */

public class OrderServiceFactory {
    private final static ActiveMessageQueue activeMessageQueue=new ActiveMessageQueue();

    //不允许外部通过new的方式构建
    private OrderServiceFactory(){

    }

    //返回OrderServiceProxy
    public static OrderService toActiveObject(OrderService orderService){
        return new OrderServiceProxy(orderService,activeMessageQueue);
    }


    public static void main(String[] args) throws InterruptedException{
        //在创建OrderService时需要传递OrderService接口的具体实现
        OrderService orderService=OrderServiceFactory.toActiveObject(new OrderServiceImpl());

        orderService.order("hello",543434);
        //立即返回
        System.out.println("Return immediately");

        Thread.currentThread().join();
    }
}
