package mutithread.concurrent.chapter26;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 流水线工人
 * @author zhangjinglong
 * @date 2020-02-23-13:18
 */

public class Worker extends Thread {
    private final ProductionChannel channel;
    //主要用于获取一个随机值，模拟加工一个产品需要耗费一定的时间，当然每个工人操作所花费的时间也可能不一样
    private final static Random random=new Random(System.currentTimeMillis());

    public Worker(String workerName,ProductionChannel channel){
        super(workerName);
        this.channel=channel;
    }

    @Override
    public void run() {
        while(true){
            try{
                //从传送带上获取产品
                Production production=channel.takeProduction();
                System.out.println(getName()+" process the "+production);
                //对产品进行加工
                production.create();
                TimeUnit.SECONDS.sleep(random.nextInt(5));

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
