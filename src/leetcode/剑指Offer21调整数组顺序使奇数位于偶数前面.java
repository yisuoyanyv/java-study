package leetcode;

/**
 * https://leetcode-cn.com/leetbook/read/illustrate-lcof/xzdt4i/
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 *
 *  
 *
 * 示例：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,2,4]
 * 注：[3,1,2,4] 也是正确的答案之一。
 *  
 *
 * 提示：
 *
 * 0 <= nums.length <= 50000
 * 1 <= nums[i] <= 10000
 * 相关标签
 * 数组
 * 双指针
 * 排序
 *
 * Java
 *
 *
 *
 * 作者：画手大鹏
 * 链接：https://leetcode-cn.com/leetbook/read/illustrate-lcof/xzdt4i/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class 剑指Offer21调整数组顺序使奇数位于偶数前面 {
    public static void main(String[] args) {
        int[] arr = new int[]{1,3,4,8,2,5,6};
        arr = exchange(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

    }

    public static int[] exchange(int[] nums) {
        //奇数在前，偶数在后，采用双指针，一个从头，一个从尾
        int head=0;
        int tail=nums.length-1;
        while (head < tail){
            //head从头遍历，直到找到第一个偶数停止
            while (head < tail && nums[head] % 2 ==1 ){
                head++;
            }
            //tail从尾往前遍历，直到找到一个第一个奇数停止
            while (head < tail && nums[tail] % 2 ==0 ){
                tail--;
            }

            //交换head 和 tail所对应的数
            int temp = nums[tail];
            nums[tail] = nums [head];
            nums[head] = temp;
        }

        return nums;
    }
}
