package jobInterview.thread;

/**
 * @author zhangjinglong
 * @date 2020-04-16-10:16 下午
 * <p>
 * 死锁的案例
 */

public class Test8 {
    public static void main(String[] args) {
        Object paper = new Object();
        Object wc = new Object();

        Ergou ergou = new Ergou(paper, wc);
        Shanpao shanpao = new Shanpao(paper, wc);
        //启动两个线程
        ergou.start();
        shanpao.start();
    }
}

class Ergou extends Thread {
    private Object paper;
    private Object wc;

    public Ergou(Object paper, Object wc) {
        this.paper = paper;
        this.wc = wc;
    }

    @Override
    public void run() {
        //锁对象
        synchronized (wc) {
            System.out.println("二狗已经冲入厕所。。。");
            synchronized (paper) {
                System.out.println("二狗获取到手纸。。。。。");
                System.out.println("二狗愉快地上厕所，感觉身心愉悦。。。");
            }
        }
        super.run();
    }
}

class Shanpao extends Thread {
    private Object paper;
    private Object wc;

    public Shanpao(Object paper, Object wc) {
        this.paper = paper;
        this.wc = wc;
    }

    @Override
    public void run() {
        synchronized (paper) {
            System.out.println("山炮拿到了手纸。。。。");
            synchronized (wc) {
                System.out.println("山炮冲入厕所。。。。");
                System.out.println("山炮愉快地上厕所，感觉身心愉悦。。。");
            }
        }
    }
}