package mutithread.concurrent.chapter27;

import mutithread.concurrent.chapter19.Future;

import java.util.Map;

/**
 * @author zhangjinglong
 * @date 2020-02-23-18:51
 */

public class FindOrderDetailsMessage extends MethodMessage {
    public FindOrderDetailsMessage(Map<String,Object> params,OrderService orderService){
        super(params,orderService);
    }

    @Override
    public void execute() {
        Future<String> realFuture=orderService.findOrderDetails((Long) params.get("orderId"));
        ActiveFuture<String> activeFuture=(ActiveFuture<String>) params.get("activeFuture");
        try{
            String result=realFuture.get();
            activeFuture.finish(result);
        }catch (InterruptedException e){
            activeFuture.finish(null);
        }
    }
}
