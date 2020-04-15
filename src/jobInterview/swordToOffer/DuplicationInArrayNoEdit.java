package jobInterview.swordToOffer;

/**
 * @author zhangjinglong
 * @date 2020-04-15-10:11 下午
 * 不修改数组找出重复的数字
 * <p>
 * 使用二分法思想
 */

public class DuplicationInArrayNoEdit {
    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 4, 3, 2, 7, 6};

        System.out.printf("重复的数字为" + getDuplication(arr, arr.length));

    }

    public static int getDuplication(final int[] numbers, int length) {
        if (numbers == null || length <= 0) {
            return -1;
        }

        int start = 1;
        int end = length - 1;
        while (end >= start) {
            int middle = ((end - start) >> 1) + start;
            int count = countRange(numbers, length, start, middle);
            if (end == start) {
                if (count > 1)
                    return start;
                else
                    break;
            }

            if (count > (middle - start + 1))
                end = middle;
            else
                start = middle + 1;

        }

        return -1;
    }

    //计算数组中满足在start和end范围内的数字个数
    private static int countRange(final int[] numbers, int length, int start, int end) {
        if (numbers == null)
            return 0;
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (numbers[i] >= start && numbers[i] <= end) {
                ++count;
            }
        }
        return count;
    }
}
