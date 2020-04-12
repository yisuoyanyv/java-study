package jobInterview.niuke.test4;

/**
 * @author zhangjinglong
 * @date 2020-04-11-10:13 上午
 * https://www.nowcoder.com/practice/d9162298cb5a437aad722fccccaae8a7?tpId=37&tqId=21227&rp=0&ru=/ta/huawei&qru=/ta/huawei/question-ranking
 * <p>
 * 题目描述
 * •连续输入字符串，请按长度为8拆分每个字符串后输出到新的字符串数组；
 * •长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
 * <p>
 * 1.使用递归的思路，截取字符串进行分段输出。分小于8和大于等于8
 */

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String str = in.nextLine();
            printStr(str);

        }
    }


    public static void printStr(String str) {
        if (str == "") return;
        if (str.length() < 8) {
            System.out.print(str);
            int l = str.length();
            if (l == 0) return;
            while (l < 8) {
                System.out.print("0");
                l++;
            }
            System.out.println();

        } else {
            System.out.println(str.substring(0, 8));
            printStr(str.substring(8));
        }

    }
}