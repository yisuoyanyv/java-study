package jobInterview.basic;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zhangjinglong
 * @date 2020-04-19-6:15 下午
 */

public class CGLibTest {
    //调用
    public static void main(String[] args) {
        TestCGLib testCGLib = new TestCGLib();
        MyInterfaceImpl o = (MyInterfaceImpl) testCGLib.getInstance(MyInterfaceImpl.class);
        System.out.println(o.getInfo());
    }
}

interface MyInterface {
    String getInfo();
}

class MyInterfaceImpl implements MyInterface {
    @Override
    public String getInfo() {
        return "hello CGLib";
    }
}

//代理
class TestCGLib implements MethodInterceptor {

    //返回代理对象
    public Object getInstance(Class claxx) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(claxx);
        //回调方法
        enhancer.setCallback(this);
        //创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        methodProxy.invokeSuper(o, objects);
        return method.getName();
    }
}