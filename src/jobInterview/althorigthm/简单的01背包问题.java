package jobInterview.althorigthm;

/**
 * @author zhangjinglong
 * @date 2020-04-11-11:43 下午
 */

public class 简单的01背包问题 {
    public static final int N = 6;//背包可选的物品数+1
    public static final int W = 21;//背包容量+1
    public static final int[] w = {0, 2, 3, 4, 5, 9};//5个物品的重量，第一个不是物品，默认0
    public static final int[] v = {0, 3, 4, 5, 8, 10};//5个物品的价格，第一个不是价格，默认0

    public static int[][] B = new int[N][W];//需要求解的规划表，默认初始值为0

    public static void knapsack() {
        int k, c;//k表示第几个物品，c表示背包容量
        for (k = 1; k < N; k++) {
            for (c = 1; c < W; c++) {
                if (w[k] > c) {//如果当前物品重量大于背包容量
                    B[k][c] = B[k - 1][c];
                } else {//如果当前物品重量小于背包容量
                    int value1 = B[k - 1][c - w[k]] + v[k];//如果选择将当前物品放入背包，背包里的物品累计价格
                    int value2 = B[k - 1][c];//如果选择 不将当前物品放入背包，背包里的物品累计价格
                    B[k][c] = Integer.max(value1, value2);//将两种选择的最大值赋给当前元素值
                }
            }
        }

    }

    public static void main(String[] args) {
        knapsack();
        System.out.printf("%d\n", B[5][20]);

    }


}
