package mutithread.concurrent.chapter06;

/**
 * @author zhangjinglong
 * @date 2020-02-17-17:13
 */

public class ThreadGropDestroy {
    public static void main(String[] args) {
        ThreadGroup group=new ThreadGroup("TestGroup");

        ThreadGroup mainGroup=Thread.currentThread().getThreadGroup();

        System.out.println("group.isDestroyed="+group.isDestroyed());
        mainGroup.list();

        group.destroy();
        System.out.println("group.isDestroyed="+group.isDestroyed());
        mainGroup.list();


    }
}
