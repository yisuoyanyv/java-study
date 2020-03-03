package mutithread.concurrent.chapter27;

import mutithread.concurrent.chapter19.FutureTask;

/**
 * @author zhangjinglong
 * @date 2020-02-23-21:46
 */

public class ActiveFuture<T> extends FutureTask<T> {
    @Override
    protected void finish(T result) {
        super.finish(result);
    }
}
