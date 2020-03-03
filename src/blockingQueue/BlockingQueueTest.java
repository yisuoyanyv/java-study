package blockingQueue;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


/**
 * 程序在一个目录及其它的所有子目录下搜索所有文件，并打印出包含指定关键字的行
 * @author zhangjinglong
 * @date 2020-02-14-19:32
 */

public class BlockingQueueTest {

    private static final int FILE_QUEUE_SIZE=10;
    private static final int SEARCH_THREADS=100;
    private static final File DUMMY=new File("");

    private static BlockingQueue<File> queue=new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);
    public static void main(String[] args) {
        try(Scanner in=new Scanner(System.in)){
            System.out.printf("Enter has directory (e.g. /opt/jdk1.9.0/src):");
            String directory=in.nextLine();
            System.out.printf("Enter keyword (e.g. volatile):");
            String keyword=in.nextLine();

            Runnable enumerator=()->{
                try{
                    enumerate(new File(directory));
                    queue.put(DUMMY);
                }catch (InterruptedException e){

                }

            };
            new Thread(enumerator).start();

            for (int i = 1; i <=SEARCH_THREADS; i++) {
                Runnable searcher=()->{
                    try{
                        boolean done=false;
                        while(!done){
                            File file=queue.take();
                            if(file==DUMMY){
                                queue.put(DUMMY);
                                done=true;
                            }else{
                                search(file,keyword);
                            }
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }catch (InterruptedException e){

                    }
                };
                new Thread(searcher).start();

            }
        }

    }

    /**
     * Recursively enumerates all files in a given directory and its subdirectories.
     * @param directory  the directory in which to start
     * @throws InterruptedException
     */
    public static void enumerate(File directory) throws InterruptedException{
        File[] files=directory.listFiles();
        for(File file:files){
            if(file.isDirectory()) enumerate(file);
            else queue.put(file);
        }
    }

    /**
     * Searches a file for a given keyword and prints all matching lines.
     * @param file  the file to search
     * @param keyword the keyword to search for
     * @throws IOException
     */
    public static void search(File file,String keyword) throws IOException {
        try(Scanner in =new Scanner(file,"UTF-8")){
            int lineNumbers=0;
            while(in.hasNext()){
                lineNumbers++;
                String line=in.nextLine();
                if(line.contains(keyword)){
                    System.out.printf("%s:%d:%s%n",file.getPath(),lineNumbers,line);
                }
            }

        }

    }

}
