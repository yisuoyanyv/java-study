package jobInterview.sort;

public class selectionSort {

    /**
     * 选择排序
     *
     * 写程序，由简单到复杂
     * 由粗到细
     * 步步测试
     * @param args
     */
    public static void main(String[] args) {
        int[] arr=new int[]{8,3,2,9,1,7,5,6,4};

        for (int i = 0; i < arr.length-1; i++) {
            int minPos=i;
            for (int j = i+1; j < arr.length; j++) {
                minPos=arr[j]<arr[minPos]?j:minPos;  //  if (arr[j] < arr[minPos])    minPos=j;
            }
            swap(arr,i,minPos);
            System.out.println("第 "+ i +"次，结果为：");
            System.out.println("minPos:"+minPos);
            print(arr);
            System.out.println("\n");
        }
    }

    public static void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    public static void print(int[] arr ){
        for (int k = 0; k < arr.length; k++) {
            System.out.print(arr[k]+" ");
        }
    }
}
