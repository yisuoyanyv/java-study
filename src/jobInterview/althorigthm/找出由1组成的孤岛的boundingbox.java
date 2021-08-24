package jobInterview.althorigthm;

import java.util.ArrayList;
import java.util.List;

/**
 * https://blog.csdn.net/qq_35865125/article/details/79975670?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522162831900516780274140976%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fall.%2522%257D&request_id=162831900516780274140976&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~first_rank_v2~rank_v29-1-79975670.first_rank_v2_pc_rank_v29&utm_term=%E6%89%BE%E5%87%BA%E5%AD%A4%E5%B2%9B%E7%9A%84&spm=1018.2226.3001.4187
 */
public class 找出由1组成的孤岛的boundingbox {


    //m行n列的矩阵
    static int m=4;
    static int n=9;

    //01图像矩阵
    static int[][] pic= {
            {0,0,0,1,1,1,0,1,1},
            {0,0,1,1,1,0,0,0,1},
            {0,0,1,0,0,1,0,0,1},
            {0,0,1,0,0,0,1,1,1},
    };

    //标记pic[i][j]是否访问过，ture表示访问过，默认为false
    static boolean[][] mark= new boolean[m][n];

    static {
        //初始化标记矩阵
        init(mark,m,n,false);
    }

    public static void init(boolean[][] arr,int m,int n,boolean initValue){
        for (int i = 0; i <m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = initValue;
            }
        }
    }

    /**
     *
     * @param row 矩阵行数
     * @param column 矩阵列数
     * @param pic 图像01矩阵
     * @return
     */
    static List<Box> getBoundingBox(int row, int column, int[][] pic){
        List<Box> resultList = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (!mark[i][j] && pic[i][j] == 1) { //如果mark[i][j]没有被访问过，且，pic[i,j]=1，则开始以此元素为始，找出相连接的1的区块
                    Box box = new Box();
                    box.min_x=i;//区块的最上方元素的行
                    box.max_x=i;
                    box.min_y=j;//区块的最左侧元素的列
                    box.max_y=j;
                    expand(i,j, row,column,pic, box, mark);

                    resultList.add(box);
                }
            }
        }
        return resultList;
    }


    //以pic[stRow][stColu]为起始，找出1构成的区块。 递归。
    static void expand(int stRow, int stColu, int row, int column, int[][] pic,
                Box box, boolean[][] mark) {
        if (mark[stRow][stColu] == true) // 如果已经被访问过，则直接返回，因为后面有四个if，是向四个方向发散的
            return;
        mark[stRow][stColu] = true;
        if (stRow + 1< row && pic[stRow + 1][stColu] == 1) {//下方的元素如果是1，则以下方的元素为始，继续递归
            expand(stRow + 1, stColu,  row,  column, pic,
                    box,  mark);
            if(box.max_x< stRow + 1) //更新max_x
                box.max_x = stRow + 1;
        }
        if (stColu + 1< column && pic[stRow][stColu+1] == 1) {
            expand(stRow , stColu + 1, row, column, pic,
                    box, mark);
            if(box.max_y< stColu + 1) //更新max_y
                box.max_y = stColu + 1;
        }

        if (stRow - 1 >=0&&pic[stRow-1][stColu] == 1) {
            expand(stRow-1, stColu, row, column, pic,
                    box, mark);
            if(box.min_x > stRow - 1) //更新min_x
                box.min_x  = stRow - 1;
        }
        if (stColu - 1 >=0&&pic[stRow][stColu - 1] == 1) {
            expand(stRow, stColu - 1, row, column, pic,
                    box, mark);
            if(box.min_y > stColu - 1)  //更新min_y
                box.min_y = stColu - 1;
        }

    }

    public static void main(String[] args) {

        List<Box> boxes = getBoundingBox(m, n, pic);
        boxes.forEach(System.out::println);

    }

    static class Box {

        int min_x;

        int min_y;

        int max_x;

        int max_y;

        public Box(){
            this.max_x=0;
            this.min_x=0;
            this.min_y=0;
            this.max_y=0;
        }

        @Override
        public String toString() {
            return  String.format("((%d,%d),(%d,%d))", min_x ,min_y ,max_x ,max_y);
        }
    };

}
