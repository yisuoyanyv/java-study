package mutithread.concurrent.chapter06;

/**
 * @author zhangjinglong
 * @date 2020-02-17-10:21
 */

public class ThreadGroupCreator {
    public static void main(String[] args) {
        //1.获取当前线程的group
        ThreadGroup cuurentGroup=Thread.currentThread().getThreadGroup();
        //2.定义一个新的group
        ThreadGroup group1=new ThreadGroup("Group1");

        //3.程序输出true
        System.out.println(group1.getParent()==cuurentGroup);

        //4.定义group2,指定group1为其父group
        ThreadGroup group2=new ThreadGroup(group1,"Group2");

        //5.程序输出true
        System.out.println(group2.getParent()==group1);

    }
}
