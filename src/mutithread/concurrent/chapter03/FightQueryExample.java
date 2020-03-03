package mutithread.concurrent.chapter03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangjinglong
 * @date 2020-02-16-16:32
 */

public class FightQueryExample {
    //1.合作的各大航空公司
    private static List<String> fightCompany= Arrays.asList("CSA","CEA","HNA");

    public static void main(String[] args) {
        List<String> results=search("SH","BJ");
        System.out.println("==================result===================");
        results.forEach(System.out::println);
    }

    private static List<String> search(String original,String dest){
        final List<String> result=new ArrayList<>();

        //2.创建查询航班信息的线程列表
        List<FightQueryTask> tasks=fightCompany.stream()
                .map(f->createSearchTask(f,original,dest))
                .collect(Collectors.toList());
        //3.分别启动这几个线程
        tasks.forEach(Thread::start);

        //4.分别调用每一个线程的join方法，阻塞当前线程
        tasks.forEach(t->{
            try{
                t.join();
            }catch (InterruptedException e){

            }

        });

    //5.在此之前，当前线程会阻塞住，获取每一个查询线程的结果，并且加入到result中
        tasks.stream().map(FightQueryTask::get).forEach(result::addAll);

        return result;
    }


    private static FightQueryTask createSearchTask(String fight,String origin,String dest){
        return new FightQueryTask(fight,origin,dest);
    }
}
