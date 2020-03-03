package mutithread.concurrent.chapter25;

/**
 * @author zhangjinglong
 * @date 2020-02-22-23:15
 */

public class Reference {

    //1M
    private final byte[] data=new byte[2<<19];

    @Override
    protected void finalize() throws Throwable {
        System.out.println("the reference will be GC.");
    }
}
