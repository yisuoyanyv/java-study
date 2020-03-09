package coreJavaVolumeFundamentals.prirotyQueue;

import java.time.LocalDate;
import java.util.PriorityQueue;

/**
 * @author zhangjinglong
 * @date 2020-03-08-10:11 下午
 */

public class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue<LocalDate> pq=new PriorityQueue<>();
        pq.add(LocalDate.of(1994,12,12));
        pq.add(LocalDate.of(1995,12,3));
        pq.add(LocalDate.of(1993,8,3));
        pq.add(LocalDate.of(2000,4,3));
        System.out.println("Iterating over elements...");

        for(LocalDate data:pq){
            System.out.println(data);
        }

        System.out.println("Removing elements");
        while (!pq.isEmpty()){
            System.out.println(pq.remove());
        }

    }
}
