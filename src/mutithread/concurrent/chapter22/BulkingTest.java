package mutithread.concurrent.chapter22;

/**
 * @author zhangjinglong
 * @date 2020-02-22-20:58
 */

public class BulkingTest {
    public static void main(String[] args){
        new DocumentEditThread("/users/zhangjinglong/test",
                "balking.txt").start();
    }
}
