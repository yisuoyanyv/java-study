package mutithread.concurrent.chapter15;

@FunctionalInterface
public interface Task<T> {

    //任务执行接口，该接口运行有返回值
    T call();
}
