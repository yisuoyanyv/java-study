package mutithread.concurrent.chapter07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 防止系统重复启动
 * @author zhangjinglong
 * @date 2020-02-17-20:51
 */

public class PreventDuplicated {

    private final static String LOCK_PATH="/users/zhangjinglong/test/locks/";
    private final static String LOCK_FILE=".lock";
    private final static String PERMISSIONS="rw-------";


    public static void main(String[] args) throws IOException {
        //2.检查是否存在.lock文件
        checkRunning();
        //1.注入Hook线程，在程序退出时删除lock文件
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("The programe received kill SIGNAL.");
            getLockFile().toFile().delete();
        }));

        //简单模拟当前程序在运行
        for(;;){
            try{
                TimeUnit.SECONDS.sleep(60);
                System.out.println("program is running.");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private static void checkRunning() throws IOException{
        Path path=getLockFile();
        if(!Paths.get(LOCK_PATH).toFile().exists()){
            Files.createDirectory(Paths.get(LOCK_PATH));
        }
        if(path.toFile().exists())
            throw new RuntimeException("The program already running,");
        Set<PosixFilePermission> perms= PosixFilePermissions.fromString(PERMISSIONS);
        Files.createFile(path,PosixFilePermissions.asFileAttribute(perms));
    }

    private static Path getLockFile(){
        return Paths.get(LOCK_PATH,LOCK_FILE);
    }
}
