package mutithread.concurrent.chapter16;

/**
 *
 * 吃面引起的死锁问题
 * 使用TablewarePair代替leftTool和rightTool,这样可以避免交叉锁的情况
 * @author zhangjinglong
 * @date 2020-02-20-22:54
 */

public class EatNoodleThreadLast extends Thread {
    private final String name;

/*    //左手边的餐具
    private final Tableware leftTool;
    //右手边的餐具
    private final  Tableware rightTool;*/

    private final TablewarePair tablewarePair;

    /*public EatNoodleThreadLast(String name, Tableware leftTool, Tableware rightTool){
        this.name=name;
        this.leftTool=leftTool;
        this.rightTool=rightTool;
    }*/

    public EatNoodleThreadLast(String name,TablewarePair tablewarePair){
        this.name=name;
        this.tablewarePair=tablewarePair;
    }

    @Override
    public void run() {
        while (true){
            this.eat();
        }
    }

    //吃面条的过程
    private void eat(){
       /* synchronized (leftTool){
            System.out.println(name+" take up "+leftTool+"(left)");
            synchronized (rightTool){
                System.out.println(name+" take up "+rightTool+"(right");
                System.out.println(name+" is eating now");
                System.out.println(name+" put down "+rightTool+"(right");
            }
            System.out.println(name+" put down "+leftTool+"(left)");
        }*/

        synchronized (tablewarePair){
            System.out.println(name+" take up "+tablewarePair.getLeftTool()+"(left)");
            System.out.println(name+" take up "+tablewarePair.getRightTool()+"(right");
            System.out.println(name+" is eating now");
            System.out.println(name+" put down "+tablewarePair.getRightTool()+"(right");
            System.out.println(name+" put down "+tablewarePair.getLeftTool()+"(left)");
        }
    }

    public static void main(String[] args) {
       /* Tableware fork=new Tableware("fork");
        Tableware knife=new Tableware("knife");
        new EatNoodleThreadLast("A",fork,knife).start();
        new EatNoodleThreadLast("B",knife,fork).start();*/
        Tableware fork=new Tableware("fork");
        Tableware knife=new Tableware("knife");
        TablewarePair tablewarePair=new TablewarePair(fork,knife);
        new EatNoodleThreadLast("A",tablewarePair).start();
        new EatNoodleThreadLast("B",tablewarePair).start();
    }
}
