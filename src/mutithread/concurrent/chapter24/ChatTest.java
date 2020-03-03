package mutithread.concurrent.chapter24;

import java.io.IOException;

/**
 * @author zhangjinglong
 * @date 2020-02-22-22:53
 */

public class ChatTest {
    public static void main(String[] args)  throws IOException {
        new ChatServer().startServer();
    }
}
