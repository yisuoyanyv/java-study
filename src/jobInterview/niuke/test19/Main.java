package jobInterview.niuke.test19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author zhangjinglong
 * @date 2020-04-23-10:13 下午
 * https://www.nowcoder.com/practice/03ba8aeeef73400ca7a37a5f3370fe68?tpId=37&tqId=21250&rp=0&ru=/ta/huawei&qru=/ta/huawei/question-ranking
 * <p>
 * 查找兄弟单词
 */

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int num = in.nextInt();
        String[] dict = new String[num];
        for (int i = 0; i < num; i++) {
            dict[i] = in.next();
        }
        String targetStr = in.next();
        int target = in.nextInt();
        int result = 0;
        ArrayList<String> resultXiaodi = new ArrayList<>();
        for (int i = 0; i < num; i++) {

            if (compare(targetStr, dict[i])) {
                result++;
                resultXiaodi.add(dict[i]);
            }

        }

        System.out.println(result);
        Collections.sort(resultXiaodi);
        for (int i = 0; i < target; i++) {
            System.out.println(resultXiaodi.get(i).trim());
        }

    }

    private static boolean compare(String target, String s) {
        if (target.length() != s.length()) {
            return false;
        }
        if (target.equals(s)) {
            return false;
        }
        char[] targetChar = target.toCharArray();
        char[] sChar = s.toCharArray();
        Arrays.sort(targetChar);
        Arrays.sort(sChar);
        return Arrays.equals(sChar, targetChar);


    }
}
