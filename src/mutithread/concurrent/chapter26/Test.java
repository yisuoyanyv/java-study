package mutithread.concurrent.chapter26;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import static java.util.concurrent.ThreadLocalRandom.current;
/**
 * @author zhangjinglong
 * @date 2020-02-23-13:24
 */

public class Test {
    public static void main(String[] args) {
        //流水线上有5个工人
        final ProductionChannel channel=new ProductionChannel(5);
        AtomicInteger productionNo=new AtomicInteger();
        //流水线上有8个工作人员往传送带上不断放置等待加工的半成品
        IntStream.range(1,8).forEach(i->new Thread(()->{
            while(true){
                channel.offerProduction(new Production(productionNo.getAndIncrement()));
                try{
                    TimeUnit.SECONDS.sleep(current().nextInt(5));
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start());
    }
}
