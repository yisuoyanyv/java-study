package jobInterview.niuke.test18;

import java.util.Scanner;

/**
 * @author zhangjinglong
 * @date 2020-04-12-5:38 下午
 * <p>
 * https://www.nowcoder.com/practice/5190a1db6f4f4ddb92fd9c365c944584?tpId=37&tqId=21249&rp=0&ru=/ta/huawei&qru=/ta/huawei/question-ranking
 * 题目描述
 * 编写一个程序，将输入字符串中的字符按如下规则排序。
 * <p>
 * 规则 1 ：英文字母从 A 到 Z 排列，不区分大小写。
 * <p>
 * 如，输入： Type 输出： epTy
 * <p>
 * 规则 2 ：同一个英文字母的大小写同时存在时，按照输入顺序排列。
 * <p>
 * 如，输入： BabA 输出： aABb
 * <p>
 * 规则 3 ：非英文字母的其它字符保持原来的位置。
 * <p>
 * <p>
 * 如，输入： By?e 输出： Be?y
 * <p>
 * <p>
 * 注意有多组测试数据，即输入有多行，每一行单独处理（换行符隔开的表示不同行）
 * <p>
 * <p>
 * 输入描述:
 * 输入字符串
 * 输出描述:
 * 输出字符串
 * 示例1
 * 输入
 * 复制
 * A Famous Saying: Much Ado About Nothing (2012/8).
 * 输出
 * 复制
 * A aaAAbc dFgghh: iimM nNn oooos Sttuuuy (2012/8).
 * <p>
 * <p>
 * 1.考察点是排序 TODO 稳定排序方法
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < 26; i++) {

                char c = (char) ('A' + i); //从 A开始，不分大小写，将字母部分先写进去
                for (int j = 0; j < line.length(); j++) {
                    char sc = line.charAt(j);
                    if (sc == c || c == sc - 32) {
                        sb.append(sc);
                    }
                }


            }

            //处理非字母部分
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                if (!(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z')) {
                    sb.insert(j, c);
                }
            }

            System.out.println(sb.toString());
        }

    }


}
