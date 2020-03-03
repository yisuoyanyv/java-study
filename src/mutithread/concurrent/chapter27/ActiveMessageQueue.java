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

public class ActiveMessageQueue {
    //用于存放提交的MethodMessage消息
    private final LinkedList<MethodMessage> messages=new LinkedList<>();

    public ActiveMessageQueue(){
        //启动Worker线程
        new ActiveDaemonThread(this).start();
    }

    public void offer(MethodMessage methodMessage){
        synchronized (this){
            messages.addLast(methodMessage);
            //因为只有一个线程负责take数据，因此没有必要使用notifyAll方法
            this.notify();
        }
    }

    protected MethodMessage take(){
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
