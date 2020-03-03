package mutithread.concurrent.chapter27;

import mutithread.concurrent.chapter19.Future;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ActiveServiceFactory是通过Active Objects的核心类，其负责生成Service的代理以及构建ActiveMessage
 * @author zhangjinglong
 * @date 2020-02-23-23:17
 */

public class ActiveServiceFactory  {
    //定义ActiveMessageQueue,用于存放ActiveMessage
    private final static ActiveMessageQueue2 queue=new ActiveMessageQueue2();

    public static <T> T active(T instance){
        //生成service的代理类
        Object proxy= Proxy.newProxyInstance(instance.getClass().getClassLoader(),
                instance.getClass().getInterfaces(),new ActiveInvocationHandler<>(instance));
        return (T) proxy;
    }

    public static void main(String[] args) throws InterruptedException{
        OrderService orderService=active(new OrderServiceImpl());
        Future<String> future=orderService.findOrderDetails(23321);
        System.out.println("i will be returned immediately");
        System.out.println(future.get());
    }

    //ActiveInvocationHandler是InvocationHandler的子类，生成Proxy时需要使用到
    private static class ActiveInvocationHandler<T> implements InvocationHandler{
        private final T instance;
        ActiveInvocationHandler(T instance){
            this.instance=instance;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //如果接口方法被@ActiveMethod标记，则会转换为ActiveMessage
            if(method.isAnnotationPresent(ActiveMethod.class)){
                //检查该方法是否符合规范
                this.checkMethod(method);
                ActiveMessage.Builder builder=new ActiveMessage.Builder();
                builder.useMethod(method).withObjects(args).forService(instance);
                Object result=null;
                if(this.isReturnFutureType(method)){
                    result=new ActiveFuture<>();
                    builder.returnFuture((ActiveFuture) result);
                }
                //将ActiveMessage加入至队列中
                queue.offer(builder.build());
                return result;
            }else{
                //如果是普通方法（没有使用@ActiveMethod标记），则会正常执行
                return method.invoke(instance,args);
            }
        }

        //检查有返回值的方法是否为Future，否则将会抛出
        //IllegalActiveMethod异常
        private void checkMethod(Method method) throws IllegalActiveMethod{
            //有返回值，必须是ActiveFuture类型的返回值
            if(!isReturnVoidType(method) && !isReturnFutureType(method)){
                throw new IllegalActiveMethod("the method [ "+method.getName()+
                        " return type must be void/Future");
            }
        }

        //判断方法是否为Future返回类型
        private boolean isReturnFutureType(Method method){
            return method.getReturnType().isAssignableFrom(Future.class);
        }

        //判断方法是否无返回类型
        private boolean isReturnVoidType(Method method){
            return method.getReturnType().equals(Void.TYPE);
        }
    }
}
