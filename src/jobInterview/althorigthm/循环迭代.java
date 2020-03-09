package jobInterview.althorigthm;

/**
 * @author zhangjinglong
 * @date 2020-03-04-8:47 下午
 */

public class 循环迭代 {

    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        System.out.println(f(40));
        long end=System.currentTimeMillis();
        System.out.println(end-start);
    }

    private static int f(int n){
        if(n<1) throw new IllegalArgumentException(n+"不能小于1");
        if(n==1||n==2) return n;
        int one=2;//初始化为走到第二级台阶的走法
        int two=1;//初始化为走到第一级台阶的走法
        int sum=0;
        for (int i = 3; i <= n; i++) {
            //最后跨两步和最后跨一步的走法
            sum=two+one;
            two=one;
            one=sum;
        }

        return sum;
    }
}
