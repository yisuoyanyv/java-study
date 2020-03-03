package mutithread.concurrent.chapter16;

/**
 * 吃面引起的死锁
 * @author zhangjinglong
 * @date 2020-02-20-22:51
 *
 * 餐具类
 */

public class Tableware {
    private final String toolName;

    public Tableware(String toolName){
        this.toolName=toolName;
    }

    @Override
    public String toString() {
        return "Tool:" +toolName;
    }
}
