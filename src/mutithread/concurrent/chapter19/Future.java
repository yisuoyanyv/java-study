package mutithread.concurrent.chapter19;

public interface Future<T> {
    //返回计算后的结果
    T get() throws InterruptedException;

    //判断任务是否完成
    boolean done();
}
