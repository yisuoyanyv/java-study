package mutithread.concurrent.chapter27;

import java.util.LinkedList;

/**
 * ActiveMessageQueue对应于Worker-Thread模式的传送带
 * 主要用于传送调用线程通过Proxy提交过来的MethodMessage
 * 但是整个传送带允许存放无限的MethodMessage(没有limit的约束，理论上可以存放
 * 无限的MethodMessage直到发生堆内存溢出的异常）
 * @author zhangjinglong
 * @date 2020-02-23-14:08
 */

public class ActiveMessageQueue2 {
    //与标准的Active Objects 不一样的是，通用的ActiveMessageQueue只需要提交ActiveMessage
    private final LinkedList<ActiveMessage> messages=new LinkedList<>();

    public ActiveMessageQueue2(){
        //启动Worker线程
        new ActiveDaemonThread2(this).start();
    }

    public void offer(ActiveMessage activeMessage){
        synchronized (this){
            messages.addLast(activeMessage);
            //因为只有一个线程负责take数据，因此没有必要使用notifyAll方法
            this.notify();
        }
    }

    protected ActiveMessage take(){
        synchronized (this){
            //当MethodMessage队列中没有Message的时候，执行线程进入阻塞
            while(messages.isEmpty()){
                try{
                    this.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            //获取其中一个MethodMessage并且从队列中移除
            return messages.removeFirst();
        }
    }
}
