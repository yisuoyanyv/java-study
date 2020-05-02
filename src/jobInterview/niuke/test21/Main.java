package jobInterview.niuke.test21;

/**
 * @author zhangjinglong
 * @date 2020-04-25-6:05 下午
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Integer> input = new ArrayList<>();
        if (in.hasNextLine()) {
            String[] strArr = in.nextLine().split("\\s+");
            for (String s : strArr) {
                input.add(Integer.parseInt(s));
            }

        }
        int step = input.size() / 2;//第一步的最大移动步长
        int size = input.size() - 1;
        int curindex;
        int curStep;
        int minCount = 100;//初始值设为数组最大长度
        //1.第一步

        for (int i = 1; i < step; i++) {
            curindex = i;
            curStep = 1;
            int second = input.get(i);
            //2.进行循环判断
            while (curindex < size) {
                curindex += input.get(curindex);
                curStep++;

                if (curindex == size) {
                    if (curStep < minCount) {
                        minCount = curStep;
                    }
                    break;
                }
            }
        }
        System.out.println(minCount == 100 ? -1 : minCount);


    }
}
