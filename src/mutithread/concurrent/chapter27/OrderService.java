package mutithread.concurrent.chapter27;

import mutithread.concurrent.chapter19.Future;

/**\
 *
 */
public interface OrderService {

    /**
     * 根据订单编号查询订单明细，有入参可有返回值，但是类型必须是Future
     * @param orderId
     * @return
     */
    Future<String> findOrderDetails(long orderId);

    /**
     * 提交订单，没有返回值
     * @param account
     * @param orderId
     */
    void order(String account,long orderId);
}
