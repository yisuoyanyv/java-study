package jobInterview.thread;

/**
 * @author zhangjinglong
 * @date 2020-04-16-10:28 下午
 * <p>
 * 死锁的案例-调整资源的顺序，解决死锁问题
 */

public class Test9 {
    public static void main(String[] args) {
        MySource s1 = new MySource("手纸", 4);
        MySource s2 = new MySource("厕所", 5);
        Thread1 t1 = new Thread1(s1, s2);
        Thread2 t2 = new Thread2(s1, s2);
        t1.start();
        t2.start();
    }
}

/**
 * 定义一个资源类型
 */
class MySource implements Comparable {
    private String info;
    private int order;

    public int getOrder() {
        return this.order;
    }

    public MySource(String info, int order) {
        this.info = info;
        this.order = order;
    }

    @Override
    public int compareTo(Object o) {
        if (o == null || !(o instanceof MySource)) {
            throw new RuntimeException("出错");
        }
        MySource s = (MySource) o;
        return new Integer(this.order).compareTo(s.getOrder());
    }

    @Override
    public String toString() {
        return "MySource{" +
                "info='" + info + '\'' +
                '}';
    }
}

class Thread1 extends Thread {
    private MySource s1;
    private MySource s2;

    public Thread1(MySource s1, MySource s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public void run() {
        //锁资源之前，先判断顺序
        if (s1.compareTo(s2) < 0) {
            //交换位置
            MySource s = s1;
            s1 = s2;
            s2 = s;
        }
        synchronized (s1) {
            System.out.println("Thread1获取资源：" + s1);
            synchronized (s2) {
                System.out.println("Thread1获取资源" + s2);
                System.out.println("Thread1正常执行");
            }
        }
    }
}

class Thread2 extends Thread {
    private MySource s1;
    private MySource s2;

    public Thread2(MySource s1, MySource s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public void run() {
        //锁资源之前，先判断顺序
        if (s1.compareTo(s2) < 0) {
            //交换位置
            MySource s = s1;
            s1 = s2;
            s2 = s;
        }
        synchronized (s1) {
            System.out.println("Thread2获取资源：" + s1);
            synchronized (s2) {
                System.out.println("Thread2获取资源" + s2);
                System.out.println("Thread2正常执行");
            }
        }
    }
}