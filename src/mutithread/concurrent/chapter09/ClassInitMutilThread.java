package mutithread.concurrent.chapter09;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author zhangjinglong
 * @date 2020-02-18-20:57
 */

public class ClassInitMutilThread {

    static{
        try{
            System.out.println("The ClassInit static code block will be invoke.");
            TimeUnit.MINUTES.sleep(10);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        IntStream.range(0,5).forEach(i->new Thread(ClassInit::new));
    }
}
