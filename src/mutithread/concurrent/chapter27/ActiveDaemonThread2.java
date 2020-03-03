package mutithread.concurrent.chapter27;

/**
 * 该守护线程，主要是从queue中获取Message然后执行execute方法
 * （注意，保持为线程命名的习惯是一个好的编程习惯）
 * @author zhangjinglong
 * @date 2020-02-23-19:10
 */

public class ActiveDaemonThread2 extends Thread {
    private final ActiveMessageQueue2 queue;

    public ActiveDaemonThread2(ActiveMessageQueue2 queue){
        super("ActiveDaemonThread");
        this.queue=queue;
        //ActiveDaemonThread为守护线程
        setDaemon(true);
    }

    @Override
    public void run() {
        for(;;){
            /*
            从MethodMessage队列中获取一个MethodMessage,然后执行execute方法
             */
            ActiveMessage activeMessage=this.queue.take();
            activeMessage.execute();
        }
    }
}
