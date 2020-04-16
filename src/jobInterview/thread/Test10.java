package jobInterview.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangjinglong
 * @date 2020-04-16-10:49 下午
 * <p>
 * 活锁的案例
 */

public class Test10 {
    public static void main(String[] args) {
        WC wc = new WC();
        //二狗带山炮一块上厕所
        new Thread(() -> {
            wc.setName("二狗");
            try {
                wc.doIt("二狗", "山炮");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //山炮带二狗一起上厕所
        new Thread(() -> {
            wc.setName("山炮");
            try {
                wc.doIt("山炮", "二狗");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}

class WC {
    //当前可以上厕所的人
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    //表示当前上厕所人的状态
    private boolean isNeed = true;

    /**
     * 上厕所的方法
     *
     * @param preName   准备要上厕所的人
     * @param otherName 另外一个要上厕所的人
     */
    public synchronized void doIt(String preName, String otherName) throws InterruptedException {
        //判断是否要上厕所
        while (isNeed) {
            while (!preName.equals(name)) {
                //如果准备上厕所的人和允许上厕所的人名字不一致，则等待
                this.wait();
            }
            //先礼让
            //判断是否有另外一个人需要上厕所
            if (otherName != null) {
                //将当前可以上厕所的人的名字修改为另一个人
                this.name = otherName;
                System.out.println(preName + "对 " + otherName + "说： 我不急，你先请！");
                TimeUnit.SECONDS.sleep(1);
                //并且唤醒另外一个线程
                this.notifyAll();
            } else {
                System.out.println(preName + "愉快的上厕所");
                isNeed = false;
            }
        }
    }
}