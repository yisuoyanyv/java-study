package mutithread.concurrent.chapter27;

import java.util.Map;

/**
 * 收集每一个接口的方法参数，并且提供execute方法共ActiveDaemonThread直接调用
 * 该对象就是典型的Worker Thread模型中的Product（附有使用说明书的半成品，等待流水线工人的加工）
 * execute方法则是加工该产品的说明书
 * @author zhangjinglong
 * @date 2020-02-23-18:44
 */


public abstract  class MethodMessage {

    //用于收集方法参数，如果有 返回Future类型则一并收集
    protected final Map<String,Object> params;

    protected final OrderService orderService;

    public MethodMessage(Map<String,Object> params,OrderService orderService){
        this.orderService=orderService;
        this.params=params;
    }

    //抽象方法，扮演work thread的说明书
    public abstract void execute();
}
