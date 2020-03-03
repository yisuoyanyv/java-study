package mutithread.concurrent.chapter15;

/**
 * 定义了在任务的生命周期中会被触发的接口，其中EmptyLifecycle是一个空的实现，主要
 * 让使用者保持对Thread类的使用习惯
 * @param <T>
 */
public interface TaskLifecycle<T> {
    //任务启动时会出发onStart方法
    void onStart(Thread thread);

    //任务正在运行时会触发onRunning方法
    void onRunning(Thread thread);

    //任务结束时会触发onFinish方法，其中result时任务执行结束后的结果
    void onFinish(Thread thread,T result);

    //任务执行报错时会触发onError方法
    void onError(Thread thread,Exception e);

    //生命周期接口的空实现（Adapter）
    class EmptyLifecycle<T> implements TaskLifecycle<T>{
        @Override
        public void onStart(Thread thread) {
            //do nothing
        }

        @Override
        public void onRunning(Thread thread) {
            //do nothing
        }

        @Override
        public void onFinish(Thread thread, T result) {
            //do nothing
        }

        @Override
        public void onError(Thread thread, Exception e) {
            //do nothing
        }
    }


}
