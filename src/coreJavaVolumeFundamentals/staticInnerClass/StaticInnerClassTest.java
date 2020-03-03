package coreJavaVolumeFundamentals.staticInnerClass;

/**
 * @author zhangjinglong
 * @date 2020-02-29-4:21 下午
 */

public class StaticInnerClassTest {

    public static void main(String[] args) {
        double[] d=new double[20];
        for (int i = 0; i < d.length; i++) {
            d[i]=100*Math.random();
        }

        ArrayAlg.Pair p= ArrayAlg.minmax(d);
        System.out.println("min="+p.getFirst());
        System.out.println("max="+p.getSecond());
    }
}

class ArrayAlg{
    /**
    A pair of floatint-point numbers
     */
    public static class Pair{
        private double first;
        private double second;

        /**
         * Constructs a pair from two floating-point numbers
         * @param f the first  number
         * @param s the second number
         */
        public Pair(double f,double s){
            first=f;
            second=s;
        }

        /**
         * Returns the first number of the pair
         * @return
         */
        public double getFirst() {
            return first;
        }

        /**
         * return the second number of the pair
         * @return
         */
        public double getSecond() {
            return second;
        }


    }

    public static Pair minmax(double[] values){
        double min=Double.POSITIVE_INFINITY;
        double max=Double.NEGATIVE_INFINITY;
        for(double v:values){
            if(min>v) min=v;
            if(max<v) max=v;
        }
        return new Pair(min,max);
    }
}