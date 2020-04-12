package jobInterview.niuke.test17;

import java.util.*;

/**
 * @author zhangjinglong
 * @date 2020-04-12-4:54 下午
 * https://www.nowcoder.com/practice/05182d328eb848dda7fdd5e029a56da9?tpId=37&tqId=21246&rp=0&ru=/ta/huawei&qru=/ta/huawei/question-ranking
 * <p>
 * 题目描述
 * 实现删除字符串中出现次数最少的字符，若多个字符出现次数一样，则都删除。输出删除这些单词后的字符串，字符串中其它字符保持原来的顺序。
 * 注意每个输入文件有多组输入，即多个字符串用回车隔开
 * 输入描述:
 * 字符串只包含小写英文字母, 不考虑非法输入，输入的字符串长度小于等于20个字节。
 * <p>
 * 输出描述:
 * 删除字符串中出现次数最少的字符后的字符串。
 * <p>
 * 示例1
 * 输入
 * 复制
 * abcdd
 * 输出
 * 复制
 * dd
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            char[] input = scanner.next().toCharArray();

            Map<Character, Integer> map = new HashMap<>();

            for (int i = 0; i < input.length; i++) {
                map.put(input[i], map.getOrDefault(input[i], 0) + 1);
            }
            int mix = Collections.min(map.values());
            for (int i = 0; i < input.length; i++) {
                if (map.get(input[i]) != mix) {
                    System.out.print(input[i]);
                }

            }
            System.out.println();
        }
    }
}
