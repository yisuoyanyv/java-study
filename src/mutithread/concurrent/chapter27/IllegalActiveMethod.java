package mutithread.concurrent.chapter27;

/**
 * 若方法不符合其被转换为Active方法时会抛出该异常
 * @author zhangjinglong
 * @date 2020-02-23-22:47
 */

public class IllegalActiveMethod extends Exception {

    public IllegalActiveMethod(String message){
        super(message);
    }
}
