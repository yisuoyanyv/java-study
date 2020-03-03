package mutithread.concurrent.chapter24;

/**
 * 客户提交的任何业务请求都会被封装成Request对象
 * @author zhangjinglong
 * @date 2020-02-22-22:13
 */

public class Request {

    private final String business;
    public Request(String business){
        this.business=business;
    }

    @Override
    public String toString() {
        return business;
    }
}
