package jobInterview.niuke.test9;

import java.util.TreeSet;

/**
 * @author zhangjinglong
 * @date 2020-04-11-5:13 下午
 *
 * https://www.nowcoder.com/practice/253986e66d114d378ae8de2e6c4577c1?tpId=37&tqId=21232&rp=0&ru=/ta/huawei&qru=/ta/huawei/question-ranking
 * 题目描述
 * 输入一个int型整数，按照从右向左的阅读顺序，返回一个不含重复数字的新的整数
 *
 *1.LinkedHashSet是按输入的顺序去重保存的,字符串的话是从左到右
 *2.考察LinkedHashSet的特性，去重和维持输入顺序。
 */

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<Character> list = new ArrayList<>();
        String input = scan.nextLine();

        for (char i : input.toCharArray()) {
            list.add(i);
        }

        Collections.reverse(list);
        LinkedHashSet<Character> set = new LinkedHashSet<>(list);


        set.forEach(l -> System.out.print(l));


    }
}
