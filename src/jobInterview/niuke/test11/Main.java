package jobInterview.niuke.test11;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author zhangjinglong
 * @date 2020-04-11-5:39 下午
 * https://www.nowcoder.com/practice/f9c6f980eeec43ef85be20755ddbeaf4?tpId=37&tqId=21239&rp=0&ru=%2Fta%2Fhuawei&qru=%2Fta%2Fhuawei%2Fquestion-ranking
 * 购物单
 * <p>
 * <p>
 * 题目描述
 * 王强今天很开心，公司发给N元的年终奖。王强决定把年终奖用于购物，他把想买的物品分为两类：主件与附件，附件是从属于某个主件的，下表就是一些主件与附件的例子：
 * 主件	附件
 * 电脑	打印机，扫描仪
 * 书柜	图书
 * 书桌	台灯，文具
 * 工作椅	无
 * 如果要买归类为附件的物品，必须先买该附件所属的主件。每个主件可以有 0 个、 1 个或 2 个附件。附件不再有从属于自己的附件。王强想买的东西很多，为了不超出预算，他把每件物品规定了一个重要度，分为 5 等：用整数 1 ~ 5 表示，第 5 等最重要。他还从因特网上查到了每件物品的价格（都是 10 元的整数倍）。他希望在不超过 N 元（可以等于 N 元）的前提下，使每件物品的价格与重要度的乘积的总和最大。
 * 设第 j 件物品的价格为 v[j] ，重要度为 w[j] ，共选中了 k 件物品，编号依次为 j 1 ， j 2 ，……， j k ，则所求的总和为：
 * v[j 1 ]*w[j 1 ]+v[j 2 ]*w[j 2 ]+ … +v[j k ]*w[j k ] 。（其中 * 为乘号）
 * 请你帮助王强设计一个满足要求的购物单。
 * <p>
 * <p>
 * <p>
 * <p>
 * 输入描述:
 * 输入的第 1 行，为两个正整数，用一个空格隔开：N m
 * <p>
 * （其中 N （ <32000 ）表示总钱数， m （ <60 ）为希望购买物品的个数。）
 * <p>
 * <p>
 * 从第 2 行到第 m+1 行，第 j 行给出了编号为 j-1 的物品的基本数据，每行有 3 个非负整数 v p q
 * <p>
 * <p>
 * （其中 v 表示该物品的价格（ v<10000 ）， p 表示该物品的重要度（ 1 ~ 5 ）， q 表示该物品是主件还是附件。如果 q=0 ，表示该物品为主件，如果 q>0 ，表示该物品为附件， q 是所属主件的编号）
 * <p>
 * <p>
 * <p>
 * <p>
 * 输出描述:
 * 输出文件只有一个正整数，为不超过总钱数的物品的价格与重要度乘积的总和的最大值（ <200000 ）。
 * 示例1
 * 输入
 * 复制
 * 1000 5
 * 800 2 0
 * 400 5 1
 * 300 5 1
 * 400 3 0
 * 500 2 0
 * 输出
 * 复制
 * 2200
 * <p>
 * 1.带约束条件带0-1背包问题  转化为分组背包问题。
 */

public class Main {

    public static void main(String[] args) {
        //测试通过，转化为分组背包问题。
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();//总钱数
        int m = input.nextInt();//商品个数

        int[] f = new int[n + 1];
        // 分组，goods[i][0]为主件，goods[i][1]为附件1，goods[i][2]为附件2
        Good[][] goods = new Good[60][4];

        for (int i = 1; i <= m; i++) {
            int v = input.nextInt();
            int p = input.nextInt();
            int q = input.nextInt();
            Good t = new Good(v, v * p);
            if (q == 0) {
                goods[i][0] = t;
            } else {
                if (goods[q][1] == null) {
                    goods[q][1] = t;
                } else {
                    goods[q][2] = t;
                }
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = n; j >= 0 && goods[i][0] != null; j--) {
                //以下代码从分组中选择价值最大的。共五种情况：不选主件，选主件，选附件1和主件，选附件2和主件，选附件1和附件2和主件
                Good master = goods[i][0];
                int max = f[j];
                if (j >= master.v && max < f[j - master.v] + master.vp) {  //主件选与不选
                    max = f[j - master.v] + master.vp;
                }

                int vt;
                if (goods[i][1] != null) {  //附件1选与不选
                    if (j >= (vt = master.v + goods[i][1].v) &&
                            max < f[j - vt] + master.vp + goods[i][1].vp) {
                        max = f[j - vt] + master.vp + goods[i][1].vp;
                    }
                }
                if (goods[i][2] != null) {

                    // 附件2+主件的情况
                    if (j >= (vt = master.v + goods[i][2].v) && max < f[j - vt] + master.vp + goods[i][2].vp) {
                        max = f[j - vt] + master.vp + goods[i][2].vp;
                    }


                    //附件2+附件1+主件的情况
                    if (j >= (vt = master.v + goods[i][1].v + goods[i][2].v) &&
                            max < f[j - vt] + master.vp + goods[i][1].vp + goods[i][2].vp) {
                        max = f[j - vt] + master.vp + goods[i][1].vp + goods[i][2].vp;
                    }
                }

                f[j] = max;


            }
        }

        System.out.println(f[n]);
    }


    //********************************************第二种解法，有问题TODO*******************
    //加了限制条件的背包问题
    //这个方法的参数意义是按照背包问题定义的
    public static int getMaxValue(int[] val, int[] weight, int[] q, int n, int w) {
        int[][] dp = new int[n + 1][w + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= w; j++) {
                if (q[i - 1] == 0) {//主件
                    if (weight[i - 1] <= j) {//用j这么多钱，去买i件商品，可以获取最大价值
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1]] + val[i - 1]);

                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }

                } else {//附件  TODO 这里只考虑到一个附件+主件的情况
                    if (weight[i - 1] + weight[q[i - 1]] <= j) {//附件的话加上主件一起算
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1] - weight[q[i - 1]]] + val[i - 1] + val[q[i - 1]]);

                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }

                }
            }
        }
        return dp[n][w];
    }

    public static void main2(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            int n = input.nextInt();//总钱数
            int m = input.nextInt();//商品个数
            int[] p = new int[m];//price 价格
            int[] v = new int[m];//value 重要度
            int[] q = new int[m];//主or附件
            for (int i = 0; i < m; i++) {
                p[i] = input.nextInt();
                v[i] = input.nextInt() * p[i];
                q[i] = input.nextInt();
            }

            System.out.println(getMaxValue(v, p, q, m, n));
        }
    }
}


class Good {
    int v;
    int vp;

    public Good(int v, int vp) {
        this.v = v;
        this.vp = vp;
    }
}