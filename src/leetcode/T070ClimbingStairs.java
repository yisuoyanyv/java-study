package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/climbing-stairs/
 *
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 * 示例 1：
 * ```
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * ```
 * 示例 2：
 * ```
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 * ```
 */
public class T070ClimbingStairs {
    public static void main(String[] args) {

        long startTime=System.currentTimeMillis();

        System.out.println(climbStairs(10000));
//        System.out.println(climbStairs2(10000));


        long endTime=System.currentTimeMillis();

        System.out.println("程序执行共耗时："+ (endTime-startTime)+" 毫秒" );

    }

    // f(1)=1   f(2)=2   f(3）=3  f(4)=5=f(3)+f(2)

    // 直接用递归会超时，需要将中间结果存在hashmap中。
    public static int climbStairs(int n) {
        if(n==1) return 1;
        if(n==2) return 2;

        Map<Integer,Integer> map=new HashMap<>();
        map.put(1,1);
        map.put(2,2);
        for (int i = 3; i <= n; i++) {
            if(!map.containsKey(n)){
                map.put(i,map.get(i-1)+map.get(i-2));
            }

        }
        return map.get(n);
    }

    // 使用数组的实现方式取代上面的map
    public static int climbStairs2(int n) {
        if(n==1) return 1;
        if(n==2) return 2;
        int[] dp=new int[n+1];
        dp[1]=1;
        dp[2]=2;
        for (int i = 3; i <= n; i++) {
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }



}
