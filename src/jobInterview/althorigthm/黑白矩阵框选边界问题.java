package jobInterview.althorigthm;

public class 黑白矩阵框选边界问题 {

    //m行n列的矩阵
    static int m=4;
    static int n=9;

    static int[][] arr= {
            {0,0,0,1,1,1,0,1,1},
            {0,0,1,1,1,0,0,0,1},
            {0,0,1,0,0,1,0,0,1},
            {0,0,1,0,0,0,1,1,1},
    };
    //标记是否访问过
    static boolean[][] inq= new boolean[m][n];
    //定义上下左右四个方向的移动数组
    static int[] X={0,0,-1,1};
    static int[] Y={-1,1,0,0};

    static{
        //初始化标记矩阵
        init(inq,m,n,false);
    }

    public static void dfs(int x,int y){
        //标记已访问
        inq[y][x]=true;
        //上下左右临近坐标dfs
        for (int i =0;i<4;i++){
            int newX=x+X[i];
            int newY=y+Y[i];
            if(judge(newX,newY)){ //如果newX,newY可以访问
                dfs(newX,newY);
            }
        }
    }

    public static void main(String[] args) {
        //遍历打印原始矩阵
        printArr(arr);
        //记录联通区域个数
        int count=0;

        for (int y = 0; y < m; y++) {
            for (int x = 0; x < n; x++) {
                //如果元素为1，且从未被标记
                if(arr[y][x]==1 && inq[y][x]==false){
                    count++;//联通区域个数+1
                    dfs(x,y);
                }
            }
        }
        System.out.println("联通区域块的个数为："+count);

    }

    public static BOX getBoundingBox(int m, int n, int pic[][]){
        return null;
    }

    public static class BOX{
        int min_x;
        int min_y;
        int max_x;
        int max_y;

    }

    public  static boolean judge(int x,int y){
        if(x>=n || x<0 || y>=m || y<0) return false;
        if(arr[y][x] == 0 || inq[y][x]== true) return false;
        return true;
    }

    public static void printArr(int[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println("");
        }
    }

    public static void init(boolean[][] arr,int m,int n,boolean initValue){
        for (int i = 0; i <m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = initValue;
            }
        }
    }


}
