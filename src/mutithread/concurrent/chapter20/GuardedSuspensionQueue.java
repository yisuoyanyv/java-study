package mutithread.concurrent.chapter20;

import java.util.LinkedList;

/**
 * Grarded Suspension 设计模式
 * 关注的是当某个条件（临界值）不满足时将操作的线程正确挂起，以防止出现数据不一致或者操作超过临界值的控制范围
 * @author zhangjinglong
 * @date 2020-02-22-16:35
 */

public class GuardedSuspensionQueue {
    //定义存放Integer类型的queue
    private final LinkedList<Integer> queue=new LinkedList<>();

    //定义queue的最大容量为100
    private final int LIMIT=100;

    //往queue中插入数据，如果queue中的元素超过了最大容量，则会陷入阻塞
    public void offer(Integer data) throws InterruptedException{
        synchronized (this){
            //判断queue的当前元素是否超过了LIMIT
            while(queue.size()>=LIMIT){
                //刮起当前线程，使其陷入阻塞
                this.wait();
            }

            //插入元素并且唤醒take线程
            queue.addLast(data);
            this.notifyAll();
        }
    }

    //从队列中获取元素，如果队列测试为空，则会使当前线程阻塞
    public Integer take() throws InterruptedException{
        synchronized (this){
            //判断队列如果为空
            while(queue.isEmpty()){
                //则挂起当前线程
                this.wait();
            }

            //通知offer线程可以继续插入数据了
            this.notifyAll();
            return queue.removeFirst();
        }
    }
}
