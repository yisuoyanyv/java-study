package mutithread.concurrent.chapter15;

import java.util.concurrent.TimeUnit;

/**
 * 任务监控的关键
 * @author zhangjinglong
 * @date 2020-02-20-20:34
 */

public class ObservableThread<T> extends Thread implements Observable {

    private  final TaskLifecycle<T> lifecycle;

    private final Task<T> task;

    private Cycle cycle;

    //指定Task的实现，默认情况下用EmptyLifecycle
    public ObservableThread(Task<T> task){
        this(new TaskLifecycle.EmptyLifecycle<T>(),task);
    }

    //指定TaskLifecycle的同时指定Task
    public ObservableThread(TaskLifecycle<T> lifecycle,Task<T> task){
        super();
        //Task不允许为null
        if(task==null){
            throw new IllegalArgumentException("The task is required.");
        }

        this.lifecycle=lifecycle;
        this.task=task;
    }

    //重写父类的run方法，并且将其修饰为final类型，不允许子类再次对其进行重写，run方法
    //在线程的运行期间，可监控任务在执行过程中的各个生命周期阶段，任务每过一个阶段相当于发生了
    //一次时间
    @Override
    public final void run() {
        //在执行线程逻辑单元的时候，分别触发相应的事件
        this.update(Cycle.STARTED,null,null);
        try{
            this.update(Cycle.RUNNING,null,null);


            T result=this.task.call();
            this.update(Cycle.DONE,result,null);
        }catch (Exception e){
            this.update(Cycle.ERROR,null, e);
        }

    }

    //update方法用于通知事件的监听者，此时任务在执行过程中发生了什么
    //最主要的通知是异常的处理
    private void update(Cycle cycle,T result,Exception e){
        this.cycle=cycle;
        if(lifecycle==null){
            return;
        }
        try{
            switch (cycle){
                case STARTED:
                    this.lifecycle.onStart(currentThread());
                    break;
                case RUNNING:
                    this.lifecycle.onRunning(currentThread());
                    break;
                case DONE:
                    this.lifecycle.onFinish(currentThread(),result);
                    break;
                case ERROR:
                    this.lifecycle.onError(currentThread(),e);
                    break;
            }
        }catch (Exception ex){
            if(cycle==Cycle.ERROR){
                throw ex;
            }
        }
    }

    @Override
    public Cycle getCycle() {
        return this.cycle;
    }


}
