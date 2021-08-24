package leetcode;

/**
 * https://leetcode-cn.com/leetbook/read/illustrate-lcof/xz2hh7/
 * 思路：观察，从矩阵左下角起，元素上边的都比它小，右边的都比它大，遍历比较，碰到边界遍历中止。
 *
 * ps:以左上角为坐标原点，y轴向下，则每个元素的表示为 [y,x],而对应于一个mxn的矩阵，
 */
public class 二维有序矩阵寻找元素 {

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix.length == 0){
            return  false;
        }
        int x = 0;
        int y = matrix.length -1;

        while (x < matrix[0].length && y >=0){
            if(matrix[y][x] > target){
                y--;
            }else  if(matrix[y][x] < target ){
                x++;
            }else{
                return true;
            }
        }
        return false;
    }



    public void print(int[][] matrix){
        for(int i=0;i<matrix.length;i++){
            System.out.println();
            for(int j=0;j<matrix[i].length;j++){
                System.out.print(String.format("%4d",matrix[i][j]));
            }
        }
    }
    public static void main(String[] args) {


        int[][] matrix={
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}
        };

        System.out.println( new 二维有序矩阵寻找元素().findNumberIn2DArray(matrix,5) );
        System.out.println( new 二维有序矩阵寻找元素().findNumberIn2DArray(matrix,20) );

    }
}
