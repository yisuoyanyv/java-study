package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/two-sum/
 *
 * 给定一个整数数组 nums和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * ```
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class T001towsum {
    public static void main(String[] args) {
        int[] arrs=new int[]{2,7,11,15,3,6};
        int[] result=towSum(arrs,26);

        for (int i = 0; i < result.length; i++) {
            System.out.printf(result[i]+" ");
        }
    }

    public static int[] towSum(int[] nums,int target){

        Map<Integer,Integer> map=new HashMap<>();


        for (int i = 0; i < nums.length; i++) {
            int complement=target-nums[i];
            if(map.containsKey(complement)){
                return new int[]{map.get(complement),i};
            }
            map.put(nums[i],i);
        }

        throw new IllegalArgumentException("No two sum solution");
    }


}
