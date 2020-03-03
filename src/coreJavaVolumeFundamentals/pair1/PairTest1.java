package coreJavaVolumeFundamentals.pair1;

/**
 * @author zhangjinglong
 * @date 2020-03-02-8:08 下午
 */

public class PairTest1 {
    public static void main(String[] args) {
        String[] words={"Mary","Had","a","little","lamb"};
        Pair<String> mm=minmax(words);
        System.out.println("min="+mm.getFirst());
        System.out.println("max="+mm.getSecond());
    }

    public static Pair<String> minmax(String[] a){
        if(a==null || a.length==0) return null;
        String min=a[0];
        String max=a[0];
        for(int i=1;i<a.length;i++){
            if(min.compareTo(a[i])>0) min=a[i];
            System.out.println("min:"+min);
            if(max.compareTo(a[i])<0) max=a[i];
            System.out.println("max:"+max);


        }

        return new Pair<>(min,max);
    }
}
