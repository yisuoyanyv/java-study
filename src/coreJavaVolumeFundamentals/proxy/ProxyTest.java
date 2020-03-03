package coreJavaVolumeFundamentals.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

/**
 * @author zhangjinglong
 * @date 2020-02-29-4:46 下午
 */

public class ProxyTest {
    public static void main(String[] args) {
        Object[] elements=new Object[1000];

        //fill elements with proxies for the integers 1 ... 1000
        for (int i = 0; i < elements.length; i++) {
            Integer value=i+1;
            InvocationHandler handler=new TrackHandler(value);
            Object proxy= Proxy.newProxyInstance(null,new Class[]{Comparable.class},handler);
            elements[i]=proxy;
        }

        //construct a random integer
        Integer key=new Random().nextInt(elements.length)+1;
        //search for the key
        int result= Arrays.binarySearch(elements,key);
        //print match if found
        if(result>0) System.out.println(elements[result]);
    }
}


class TrackHandler implements InvocationHandler{
    private Object target;

    /**
     * Constructs a TraceHandler
     * @param t the implicit parameter of the method call
     */
    public TrackHandler(Object t){
        this.target=t;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //print implicit argument
        System.out.print(target);
        //print method name
        System.out.print("."+method.getName()+"(");
        //print explicit argument
        if(args!=null){
            for (int i = 0; i < args.length; i++) {
                System.out.print(args[i]);
                if(i<args.length-1) System.out.println(",");
            }
        }
        System.out.println(")");

        //invoke actual method
        return method.invoke(target,args);
    }
}
