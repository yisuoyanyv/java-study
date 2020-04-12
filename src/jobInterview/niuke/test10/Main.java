package jobInterview.niuke.test10;

import java.util.*;

/**
 * @author zhangjinglong
 * @date 2020-04-11-5:39 下午
 * <p>
 * https://www.nowcoder.com/practice/eb94f6a5b2ba49c6ac72d40b5ce95f50?tpId=37&tqId=21233&rp=0&ru=/ta/huawei&qru=/ta/huawei/question-ranking
 * 题目描述
 * 编写一个函数，计算字符串中含有的不同字符的个数。字符在ACSII码范围内(0~127)，换行表示结束符，不算在字符里。不在范围内的不作统计。
 * <p>
 * 1.用set去重，返回set的size大小即可。
 */

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        Set<Character> set = new HashSet<>();
        for (Character c : str.toCharArray()) {
            set.add(c);
        }

        System.out.println(set.size());


    }
}

