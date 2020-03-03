package mutithread.concurrent.chapter06;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangjinglong
 * @date 2020-02-17-11:45
 */

public class ThreadGroupEnumerateThreadGroups {
    public static void main(String[] args) throws InterruptedException {

        ThreadGroup myGroup1=new ThreadGroup("myGroup1");
        ThreadGroup myGroup2=new ThreadGroup(myGroup1,"myGroup2");

        TimeUnit.MILLISECONDS.sleep(2);
        ThreadGroup mainGroup=Thread.currentThread().getThreadGroup();

        ThreadGroup[] list=new ThreadGroup[mainGroup.activeGroupCount()];

        int recurseSize=mainGroup.enumerate(list);
        System.out.println(recurseSize);

        recurseSize=mainGroup.enumerate(list,false);
        System.out.println(recurseSize);

    }
}
