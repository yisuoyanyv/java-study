package jobInterview.niuke.test22;

/**
 * @author zhangjinglong
 * @date 2020-04-28-7:28 下午
 */

public class Main2 {
    public static void main(String[] args) {
        int[] arr = new int[]{0, -1, 2, -3, 4, 5, -6, 7, -8};
        int max = 0;
        int cur = 0;
        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            if (cur < 0) {
                cur = 0;
            } else {
                max = max < cur ? cur : max;
            }
        }
        System.out.println(max);
    }
}
