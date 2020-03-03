package mutithread.concurrent.chapter11;
import static java.lang.Thread.currentThread;
/**
 * @author zhangjinglong
 * @date 2020-02-19-14:36
 */

public class MainThreadClassLoader {
    public static void main(String[] args) {
        System.out.println(currentThread().getContextClassLoader());
    }
}
