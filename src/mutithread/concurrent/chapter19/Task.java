package mutithread.concurrent.chapter19;

/**
 * Task接口主要是提供给调用者实现计算逻辑之用的，可以接受一个参数并且返回最终的计算结果
 * 函数式接口
 */
@FunctionalInterface
public interface Task<IN,OUT> {

    //给定一个参数，经过计算返回结果
    OUT get(IN input);

}
