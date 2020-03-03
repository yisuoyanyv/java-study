package mutithread.concurrent.chapter27;

import mutithread.concurrent.chapter19.Future;

import java.lang.reflect.Method;

/**
 * 相比较于MethodMessage,ActiveMessage更加通用
 * ActiveMessage也是用于收集接口方法信息和具体的调用方法的
 * @author zhangjinglong
 * @date 2020-02-23-22:50
 */

//包可见，ActiveMessage只在框架内部使用，不会对外暴露

 class ActiveMessage {
     //接口方法的参数
    private final Object[] objects;

    //接口方法
    private final Method method;

    //有返回值的方法，会返回ActiveFuture<?>类型
    private final ActiveFuture<Object> future;

    //具体的Service接口
    private final Object service;


    //构造ActiveMessage是由Builer来完成的
    private ActiveMessage(Builder builder){
        this.objects=builder.objects;
        this.method=builder.method;
        this.future=builder.future;
        this.service=builder.service;
    }

    //ActiveMessage的方法通过反射的方式调用执行的具体实现
    public void execute(){
        try{
            //执行接口的方法
            Object result=method.invoke(service,objects);
            if(future!=null){
                //如果是有返回值的接口方法，则需要通过get方法获得最终的结果
                Future<?> realFuture=(Future<?>)result;
                Object realResult=realFuture.get();
                //将结果交给ActiveFuture，接口方法的线程会得到返回
                future.finish(realResult);

            }
        }catch (Exception e){
            //如果发生异常，那么有返回值的方法将会被显式地指定结果为null，无返回值的接口方法则会忽略该异常
            if(future!=null){
                future.finish(null);
            }
        }
    }


    //Builder主要负责对ActiveMessage的构建，是一种典型的Gof Builder设计模式
    static class Builder{
        private Object[] objects;

        private Method method;

        private ActiveFuture<Object> future;

        private Object service;

        public Builder useMethod(Method method){
            this.method=method;
            return this;
        }
        public Builder returnFuture(ActiveFuture<Object> future){
            this.future=future;
            return this;

        }

        public Builder withObjects(Object[] objects){
            this.objects=objects;
            return this;
        }

        public Builder forService(Object service){
            this.service=service;
            return this;
        }

        //构建ActiveMessage实例
        public ActiveMessage build(){
            return new ActiveMessage(this);
        }
    }

}
