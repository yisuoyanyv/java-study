package jobInterview.niuke.test1;


/**
 * @author zhangjinglong
 * @date 2020-04-11-8:45 上午
 * <p>
 * 1.记得导包，记得输入和输出函数自己写
 * 2.类名需要用Main，记得写main方法
 * <p>
 * 字符串最后一个单词的长度
 */

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        System.out.println(getLastWordLenght(input));
    }

    public static int getLastWordLenght(String input) {
        if (input == null || input.length() == 0) return 0;
        String[] result = input.split("\\s+");
        return result[result.length - 1].length();

    }
}