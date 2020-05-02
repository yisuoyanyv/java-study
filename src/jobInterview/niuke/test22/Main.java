package jobInterview.niuke.test22;

/**
 * @author zhangjinglong
 * @date 2020-04-28-7:28 下午
 */

public class Main {
    public static void main(String[] args) {
        //岛屿  二维数组 1表示岛屿，0表示水

        int[][] arr = new int[][]{{1, 1, 0, 1}, {0, 1, 1, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}};

        //  1  1  0  1
        //  0  1  1  1
        //  0  0  0  0
        //  0  0  0  0

        //依次遍历每个元素，判断该元素的上方
        int count = 0;// 小岛数量
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 0) continue;
                // 判断第一个元素为1的情况
                if (arr[i][j] == 1 && i == 0 && j == 0) {
                    count++;
                }
                //判断除去第一行和第一列元素的其他元素
                if (arr[i][j] == 1 && i > 0 && j > 0 && arr[i - 1][j] == 0 && arr[i][j - 1] == 0) {
                    count++;
                }
                //判断第一行元素
                if (arr[i][j] == 1 && i == 0 && j > 0 && arr[i][j - 1] == 0) {
                    count++;
                }
                //判断第一列元素
                if (arr[i][j] == 1 && j == 0 && i > 0 && arr[i - 1][j] == 0) {
                    count++;
                }
            }

        }
        System.out.println(count);
    }
}
