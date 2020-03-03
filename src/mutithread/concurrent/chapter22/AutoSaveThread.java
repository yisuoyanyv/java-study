package mutithread.concurrent.chapter22;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangjinglong
 * @date 2020-02-22-20:47
 */

public class AutoSaveThread extends Thread {
    private final Document document;

    public AutoSaveThread(Document document){
        super("DocumentAutoSaveThread");
        this.document=document;
    }

    @Override
    public void run() {
        while(true){
            try{
                //每隔3秒自动保存一次文档
                document.save();
                TimeUnit.SECONDS.sleep(3);
            }catch (IOException | InterruptedException e){
                break;
            }
        }
    }
}
