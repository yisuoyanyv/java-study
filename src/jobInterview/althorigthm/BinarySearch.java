package jobInterview.althorigthm;

public class BinarySearch {

    public  int binarySearch(int[] arr,int target){
        //二分查找

        int low = 0;
        int high = arr.length-1;
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(arr[mid] < target){
                low = mid+1;
            }else if(arr[mid] > target){
                high = mid-1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1,4,7,11,15};
        System.out.println(new BinarySearch().binarySearch(arr,7));
        System.out.println(new BinarySearch().binarySearch(arr,10));
    }
}
