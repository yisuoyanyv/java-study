package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/leetbook/read/illustrate-lcof/55nwa6/
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *  
 *
 * 限制：
 *
 * 0 <= matrix.length <= 100
 * 0 <= matrix[i].length <= 100
 * 注意：本题与主站 54 题相同：https://leetcode-cn.com/problems/spiral-matrix/
 *
 * 相关标签
 * 数组
 * 矩阵
 * 模拟
 *
 * 作者：画手大鹏
 * 链接：https://leetcode-cn.com/leetbook/read/illustrate-lcof/55nwa6/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 *
 */
public class 剑指Offer29顺时针打印矩阵 {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
       int[] integers = spiralOrder(matrix);
        for (Integer integer : integers) {
            System.out.print(String.valueOf(integer));
        }


    }

    /**
     * 定义top,bottom,left，right四个边界变量
     * 从左到右，从上到下，从右到左，从下到上
     * 每次固定一个维度，使用top,right,bottom,left四个边界值替代
     * 每个遍历完成后，需要判断边界，是否需要返回
     * @param matrix
     * @return
     */
    public static int[] spiralOrder(int[][] matrix) {
        if(matrix.length == 0 ) return new int[0];
        int left = 0,right = matrix[0].length-1,top=0,bottom=matrix.length-1,x=0;
        int[] res = new int[(right+1) * (bottom+1)];

        while(true) {
            //从左到右
            for (int i = left; i <= right; i++) {
                res[x++] = matrix[top][i];
            }
            //判断下一步的边界
            if (++top > bottom) break;

            //从上到下
            for (int i = top; i <= bottom; i++) {
                res[x++] = matrix[i][right];
            }
            //判断下一步的边界
            if (left > --right) break;

            //从右到左
            for (int i = right; i >= left; i--) {
                res[x++] = matrix[bottom][i];
            }
            //判断下一步的边界
            if (top > --bottom) break;

            //从下到上
            for (int i = bottom; i >= top; i--) {
                res[x++] = matrix[i][left];
            }
            //判断下一步的边界
            if (++left > right) break;
        }


       print(matrix);
        return res;
    }

    public  static void print(int[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(String.format("%3d",matrix[i][j]));
            }

            System.out.println();
        }
    }

}

