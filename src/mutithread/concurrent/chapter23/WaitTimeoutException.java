package mutithread.concurrent.chapter23;

/**
 * @author zhangjinglong
 * @date 2020-02-22-21:51
 */

public class WaitTimeoutException extends Exception{
    public WaitTimeoutException(String message){
        super(message);
    }
}
