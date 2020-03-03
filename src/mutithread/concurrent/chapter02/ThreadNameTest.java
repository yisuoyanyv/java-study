package mutithread.concurrent.chapter02;

import java.util.stream.IntStream;

/**
 * @author zhangjinglong
 * @date 2020-02-15-22:42
 */

public class ThreadNameTest {
    private static final String PREFIX="ALEX-";
    public static void main(String[] args) {
        IntStream.range(0,5).mapToObj(ThreadNameTest::createThread)
                .forEach(Thread::start);

    }

    private static Thread createThread(final int intName){
        return new Thread(()->System.out.println(Thread.currentThread().getName())
                ,PREFIX+intName);
    }
}
