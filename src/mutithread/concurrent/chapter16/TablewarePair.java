package mutithread.concurrent.chapter16;

/**
 * 将刀叉进行封装，改进交叉锁问题
 * @author zhangjinglong
 * @date 2020-02-20-23:04
 */

public class TablewarePair {
    private final Tableware leftTool;
    private final Tableware rightTool;

    public TablewarePair(Tableware leftTool,Tableware rightTool){
        this.leftTool=leftTool;
        this.rightTool=rightTool;
    }

    public Tableware getLeftTool(){
        return leftTool;
    }

    public Tableware getRightTool(){
        return rightTool;
    }


}
