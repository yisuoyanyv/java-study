package mutithread.concurrent.chapter19;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 该类主要作用在于当提交任务时创建一个新的线程来受理该任务，进而达到任务异步执行的效果
 * @author zhangjinglong
 * @date 2020-02-21-22:37
 */

public class FutureServiceImpl<IN,OUT> implements FutureService<IN,OUT> {
    //为执行的线程指定名字前缀（再三强调，为线程起一个特殊的名字是一个非常好的编程习惯）
    private final static String FUTURE_THREAD_PREFIX="FUTURE-";

    private final AtomicInteger nextCounter=new AtomicInteger(0);

    private String getNextName(){
        return FUTURE_THREAD_PREFIX+nextCounter.getAndIncrement();
    }

    @Override
    public Future<?> submit(Runnable runnable) {
        final FutureTask<Void> future=new FutureTask<>();
        new Thread(()->{
            runnable.run();
            //任务执行结束之后将null作为结果传给future
            future.finish(null);

        },getNextName()).start();
        return future;

    }

    @Override
    public Future<OUT> submit(Task<IN, OUT> task, IN input) {
        final FutureTask<OUT> future=new FutureTask<>();
        new Thread(()->{
            OUT result=task.get(input);
            //任务执行结束之后，将真实的结果通过finish方法传递给future
            future.finish(result);
        },getNextName()).start();
        return future;
    }

    @Override
    public Future<OUT> submit(Task<IN, OUT> task, IN input, Callback<OUT> callback) {
        final FutureTask<OUT> future=new FutureTask<>();
        new Thread(()->{
            OUT result=task.get(input);
            //任务执行结束之后，将真实的结果通过finish方法传递给future
            future.finish(result);
            callback.call(result);
        },getNextName()).start();
        return future;
    }
}
