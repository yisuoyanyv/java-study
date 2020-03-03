package mutithread.concurrent.chapter18;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhangjinglong
 * @date 2020-02-21-20:55
 */

public class ArrayListStream {
    public static void main(String[] args) {
        //定义一个list并且使用Arrays的方式进行初始化
        List<String> list= Arrays.asList("Java","Thread","Concurrenty","Scala","Clojure");
        //获取并行的stream，然后通过map函数对list中的数据进行加工，然后输出
        list.parallelStream().map(String::toUpperCase).forEach(System.out::println);
        list.forEach(System.out::println);
    }
}
