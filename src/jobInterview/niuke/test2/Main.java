package jobInterview.niuke.test2;

/**
 * @author zhangjinglong
 * @date 2020-04-11-9:06 上午
 * <p>
 * 写出一个程序，接受一个由字母和数字组成的字符串，和一个字符，然后输出输入字符串中含有该字符的个数。不区分大小写。
 * <p>
 * <p>
 * 1.需要熟练掌握字符串处理的各种方法
 * 2。熟练掌握 Character和String的相互转化
 */

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        String seachKey = in.nextLine();

        System.out.println(getKeyCount(input, seachKey));
    }

    public static int getKeyCount(String input, String search) {
        if (input == null || input.length() == 0) return 0;
        int length = input.length();
        int count = 0;
        for (int i = 0; i < length; i++) {
            if ((Character.toString(input.charAt(i)).equalsIgnoreCase(search))) {
                count++;
            }
        }

        return count;

    }

    public static int getKeyCount2(String input, String search) {
        if (input == null || input.length() == 0) return 0;
        Character s = search.toCharArray()[0];
        int length = input.length();
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (s == input.charAt(i)) {
                count++;
            }
        }

        return count;

    }

    public static int getKeyCount3(String input, String search) {
        if (input == null || input.length() == 0) return 0;


        return (input.length() - input.replaceAll(search, "").length());

    }
}
