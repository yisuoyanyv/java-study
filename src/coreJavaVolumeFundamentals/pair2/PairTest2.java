package coreJavaVolumeFundamentals.pair2;

import coreJavaVolumeFundamentals.pair1.Pair;

import java.time.LocalDate;

/**
 * @author zhangjinglong
 * @date 2020-03-02-8:35 下午
 */

public class PairTest2 {
    public static void main(String[] args) {
        LocalDate[] birthdays={
                LocalDate.of(1999,12,12),
                LocalDate.of(2000,1,1),
                LocalDate.of(2020,2,2)
        };

        Pair<LocalDate> mm=Arraylg.minmax(birthdays);
        System.out.println("min="+mm.getFirst());
        System.out.println("max="+mm.getSecond());
    }


}
class Arraylg{
    /**
     * Gets the minium and maximum of an arrays of objects of type T
     * @param a an array of objects of type T
     * @param <T>
     * @return
     */
    public static <T extends Comparable>Pair minmax(T[] a){
        if(a==null ||a.length==0) return null;
        T min=a[0];
        T max=a[0];
        for (int i = 1; i < a.length; i++) {
            if(min.compareTo(a[i])>0) min=a[i];
            if(max.compareTo(a[i])<0) max=a[i];
        }
        return new Pair(min,max);
    }

}