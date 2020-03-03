package mutithread.concurrent.chapter27;

import mutithread.concurrent.chapter19.Future;
import mutithread.concurrent.chapter19.FutureService;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangjinglong
 * @date 2020-02-23-13:51
 */

//执行线程中将要被使用的类
public class OrderServiceImpl implements OrderService {
    @ActiveMethod
    @Override
    //使用在19章中实现的Future、返回结果
    public Future<String> findOrderDetails(long orderId) {
        return FutureService.<Long,String>newService().submit(input->{
            try{
                //通过休眠来模拟该方法的执行比较耗时
                TimeUnit.SECONDS.sleep(5);
                System.out.println("process the orderID ->"+orderId);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "The order Details Information";
        },orderId,null);
    }
    @ActiveMethod
    @Override
    public void order(String account, long orderId) {
        try{
            TimeUnit.SECONDS.sleep(5);
            System.out.println("process the order for account "+account+",orderId "+orderId);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
