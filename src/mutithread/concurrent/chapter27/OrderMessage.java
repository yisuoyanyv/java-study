package mutithread.concurrent.chapter27;

import java.util.Map;

/**
 * 主要处理order方法，从params中获取接口的参数，然后执行真正的OrderService的order方法
 * @author zhangjinglong
 * @date 2020-02-23-18:57
 */

public class OrderMessage extends MethodMessage {
    public OrderMessage(Map<String,Object> params,OrderService orderService){
        super(params,orderService);
    }

    @Override
    public void execute() {
        //获取参数
        String account=(String) params.get("account");
        long orderId=(long) params.get("orderId");

        //执行真正的order方法
        orderService.order(account,orderId);
    }
}
