package mutithread.concurrent.chapter08;

/**
 * @author zhangjinglong
 * @date 2020-02-17-21:43
 */

public class RunnableDenyException extends RuntimeException {
    public RunnableDenyException(String message){
        super(message);
    }
}
