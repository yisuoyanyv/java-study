package mutithread.concurrent.chapter24;

import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.net.Socket;

/**
 * @author zhangjinglong
 * @date 2020-02-23-00:27
 */

public class SocketCleaningTracker {
    //定义ReferenceQueue
    private static final ReferenceQueue<Object> queue=new ReferenceQueue<>();

    static{
        //启动Cleaner线程
        new Cleaner().start();
    }

    public static void track(Socket socket){
        new Tracker(socket,queue);
    }
    private static class Cleaner extends Thread{
        private Cleaner(){
            super("SocketCleaningTracker");
            setDaemon(true);
        }

        @Override
        public void run() {
            for(;;){
                try{
                    //当Tracker被垃圾回收器回收时会加入Queue中
                    Tracker tracker=(Tracker)queue.remove();
                    tracker.close();
                }catch (InterruptedException e){

                }
            }
        }
    }

    //Tracker是一个PhantomReference的子类
    private static class Tracker extends PhantomReference<Object>{
        private final Socket socket;
        Tracker(Socket socket,ReferenceQueue<? super Object> queue){
            super(socket,queue);
            this.socket=socket;
        }

        public void close(){
            try{
                socket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
