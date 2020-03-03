package future;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author zhangjinglong
 * @date 2020-02-14-22:02
 */

public class FutureTest {

    public static void main(String[] args) {
        try(Scanner in=new Scanner(System.in)){
            System.out.printf("Enter base directory (e.g. /usr/local/xxx/src):");
            String directory=in.nextLine();
            System.out.printf("Enter keyword (e.g. volatile):");
            String keyword=in.nextLine();

            MathCounter counter=new MathCounter(new File(directory),keyword);
            FutureTask<Integer> task=new FutureTask<>(counter);
            Thread t=new Thread(task);
            t.start();

            try{
                System.out.printf(task.get()+" matching files.");
            }catch (ExecutionException e){
                e.printStackTrace();
            }catch (InterruptedException e){

            }


        }
    }
}

/**
 * This task count the files in a directory and its subdirectories contain a given keyword
 */
class MathCounter implements Callable<Integer>{
    private File directory;
    private String keyword;

    /**
     * Constructs a MathCounter
     * @param directory directory the directory in which to start the search
     * @param keyword the keyword to look for
     */
    public MathCounter(File directory,String keyword){
        this.directory=directory;
        this.keyword=keyword;
    }

    @Override
    public Integer call(){
        int count=0;
        try{
            File[] files=directory.listFiles();
            List<Future<Integer>> results=new ArrayList<>();

            for(File file:files){
                if(file.isDirectory()){
                    MathCounter counter=new MathCounter(file,keyword);
                    FutureTask<Integer> task=new FutureTask<>(counter);
                    results.add(task);
                    Thread t=new Thread(task);
                    t.start();
                }else{
                    if(search(file)) count++;
                }
            }

            for(Future<Integer> result:results){
                try{
                    count+=result.get();
                }catch (ExecutionException e){
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e){

        }
        return count;
    }

    /**
     * Searche a file for a give file
     * @param file the file to search
     * @return true if he keyword is contained in the file
     */
    public boolean search(File file){
        try(Scanner in =new Scanner(file,"UTF-8")) {
            boolean found=false;
            while(!found && in.hasNext()){
                String line=in.nextLine();
                if(line.contains(keyword)) found=true;
            }

            return found;

        }catch (IOException e){
           return false;
        }
    }
}