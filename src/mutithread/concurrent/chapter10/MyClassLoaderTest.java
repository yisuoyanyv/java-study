package mutithread.concurrent.chapter10;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zhangjinglong
 * @date 2020-02-18-22:20
 */

public class MyClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException,InstantiationException,
            IllegalAccessException,NoSuchMethodException, InvocationTargetException {
        //声明一个MyClassLoader
        MyClassLoader classLoader=new MyClassLoader();
        //使用Myclassloader加载HelloWorld
        Class<?> aClass=classLoader.loadClass("HelloWord");
        System.out.println(aClass.getClassLoader());
        Object helloWorld=aClass.newInstance();
        System.out.println(helloWorld);
        Method welcomMethod=aClass.getMethod("welcome");
        String result=(String) welcomMethod.invoke(helloWorld);
        System.out.println("Result:"+result);
    }
}
