package mutithread.concurrent.chapter18;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author zhangjinglong
 * @date 2020-02-21-21:05
 * 不可变累加器的设计
 */


//不可变对象不允许被继承
public final class IntegerAccumulator2 {

    private final int init;

    //构造时传入初始值
    public IntegerAccumulator2(int init){
        this.init=init;
    }

    //构造新的累加器，需要用到另外一个accumulator和初始值
    public IntegerAccumulator2(IntegerAccumulator2 accumulator2,int init){
        this.init=accumulator2.getValue()+init;

    }
    //每次相加都会产生一个新的IntegerAccumulator2
    public IntegerAccumulator2 add(int i){
        return new IntegerAccumulator2(this,i);
    }

    //返回当前的初始值
    public int getValue(){
        return this.init;
    }

    public static void main(String[] args) {
        //用同样的方式进行测验，这次不加同步锁

        //定义类加器。并且将设置初始值为0
        IntegerAccumulator2 accumulator=new IntegerAccumulator2(0);
        //定义三个线程，并且分别启动
        IntStream.range(0,3).forEach(i->new Thread(()->{
            int inc=0;
            while(true){
                int oldValue=accumulator.getValue();//TODO 这段代码有问题，oldValue 始终为0
                    //然后调用add方法技术
                int result=accumulator.add(inc).getValue();

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
