package mutithread.concurrent.chapter24;

import mutithread.concurrent.chapter08.BasicThreadPool;
import mutithread.concurrent.chapter08.ThreadPool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 网络聊天服务端程序
 *
 * 用来接收来自客户端的链接，并且与之进行TCP用心交互
 * 当服务器接到了每一次的客户端连接后便会给线程池提交一个任务用于与客户端进行交互
 * 从而提高并发响应能力
 * @author zhangjinglong
 * @date 2020-02-22-22:27
 */

public class ChatServer {
    //服务端端口
    private final int port;

    //定义线程池
    private ThreadPool threadPool;

    //服务队Socket
    private ServerSocket serverSocket;

    //通过构造函数传入端口
    public ChatServer(int port){
        this.port=port;
    }
    //默认使用13312端口
    public ChatServer(){
        this(13312);
    }

    public void startServer() throws IOException{
        //创建线程池，初始化一个线程，核心线程数量为2，最大线程数为4，阻塞队列中最大可加入1000个任务
        this.threadPool=new BasicThreadPool(1,4,2,1000);
        this.serverSocket=new ServerSocket(port);
        this.serverSocket.setReuseAddress(true);
        System.out.println("Chat server is started and listen at port: "+port);
        this.listen();
    }

    private void listen() throws IOException{
        for(;;){
            //accept方法是阻塞方法，当有新的连接进入时才会返回，并且返回的是客户端的连接
            Socket client=serverSocket.accept();
            //将客户端连接作为一个Request封装为对应的Handler然后提交给线程池
            this.threadPool.execute(new ClientHandler(client));
        }
    }
}
