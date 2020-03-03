package mutithread.concurrent.chapter18;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author zhangjinglong
 * @date 2020-02-21-21:05
 * 方法同步增加线程安全性 的累加器
 */

public class IntegerAccumulator {

    private  int init;

    //构造时传入初始值
    public IntegerAccumulator(int init){
        this.init=init;
    }

    //对初始值增加1
    public int add(int i){
        this.init+=i;
        return this.init;
    }

    //返回当前的初始值
    public int getValue(){
        return this.init;
    }

    public static void main(String[] args) {
        //定义类加器。并且将设置初始值为0
        IntegerAccumulator accumulator=new IntegerAccumulator(0);
        //定义三个线程，并且分别启动
        IntStream.range(0,3).forEach(i->new Thread(()->{
            int inc=0;
            while(true){
                int oldValue;
                int result;
                //使用class实例作为同步锁
                synchronized (IntegerAccumulator.class) {
                    oldValue=accumulator.getValue();
                    //然后调用add方法技术
                    result=accumulator.add(inc);
                }
                System.out.println(oldValue+" + "+inc+"="+result);
                //经过验证，如果不合理，则输出错误信息
                if(inc+oldValue!=result){
                    System.err.println("ERROR:"+oldValue+"+"+inc+"="+result);
                }
                inc++;
                //模拟延迟
                slowly();

            }
        }).start());
    }

    private static void slowly(){
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
