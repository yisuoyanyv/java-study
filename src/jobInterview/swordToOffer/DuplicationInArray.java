package jobInterview.swordToOffer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangjinglong
 * @date 2020-04-15-9:03 下午
 * <p>
 * 找出数组中重复的数字
 * <p>
 * TODO 下面的写法只能找出重复的数字，返回其中一个，不能返回所有重复的数字
 */

public class DuplicationInArray {
    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 0, 2, 5, 3};
//        int[] arr={};

        ArrayList<Integer> result = new ArrayList<>();
        if (dealWithDuplication(arr, result)) {
            System.out.println("find Duplication in arr");
            for (int i : result) {
                System.out.println(i);
            }
        } else {
            System.out.println("no Duplication in arr");
        }


    }

    public static boolean dealWithDuplication(int[] input, ArrayList<Integer> result) {

        if (input == null || input.length == 0) {
            return false;
        }

        for (int i = 0; i < input.length; i++) {
            if (input[i] < 0 || input[i] > input.length - 1) {
                return false;
            }
        }

        for (int i = 0; i < input.length; i++) {
            while (input[i] != i) {
                if (input[i] == input[input[i]]) {
                    result.add(input[i]);
                    return true;
                }

                //swap the input[i] and input[input[i]]
                // 2 3 1 0 2 5 3
                int temp = input[i];//temp=2
                input[i] = input[temp];//input[0]=1
                input[temp] = temp;//input[2]=2
            }
        }
        if (result.isEmpty()) {
            return true;
        }
        return false;
    }
}
