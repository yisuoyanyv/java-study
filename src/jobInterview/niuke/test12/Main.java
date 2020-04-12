package jobInterview.niuke.test12;

/**
 * @author zhangjinglong
 * @date 2020-04-12-11:51 上午
 * https://www.nowcoder.com/practice/119bcca3befb405fbe58abe9c532eb29?tpId=37&tqId=21240&rp=0&ru=/ta/huawei&qru=/ta/huawei/question-ranking
 * <p>
 * 题目描述
 * 开发一个坐标计算工具， A表示向左移动，D表示向右移动，W表示向上移动，S表示向下移动。从（0,0）点开始移动，从输入字符串里面读取一些坐标，并将最终输入结果输出到输出文件里面。
 * <p>
 * 输入：
 * <p>
 * 合法坐标为A(或者D或者W或者S) + 数字（两位以内）
 * <p>
 * 坐标之间以;分隔。
 * <p>
 * 非法坐标点需要进行丢弃。如AA10;  A1A;  $%$;  YAD; 等。
 * <p>
 * 下面是一个简单的例子 如：
 * <p>
 * A10;S20;W10;D30;X;A1A;B10A11;;A10;
 * <p>
 * 处理过程：
 * <p>
 * 起点（0,0）
 * <p>
 * +   A10   =  （-10,0）
 * <p>
 * +   S20   =  (-10,-20)
 * <p>
 * +   W10  =  (-10,-10)
 * <p>
 * +   D30  =  (20,-10)
 * <p>
 * +   x    =  无效
 * <p>
 * +   A1A   =  无效
 * <p>
 * +   B10A11   =  无效
 * <p>
 * +  一个空 不影响
 * <p>
 * +   A10  =  (10,-10)
 * <p>
 * 结果 （10， -10）
 * <p>
 * 注意请处理多组输入输出
 * <p>
 * 输入描述:
 * 一行字符串
 * <p>
 * 输出描述:
 * 最终坐标，以,分隔
 * <p>
 * 示例1
 * 输入
 * 复制
 * A10;S20;W10;D30;X;A1A;B10A11;;A10;
 * 输出
 * 复制
 * 10,-10
 * <p>
 * <p>
 * 1.正则表达式的灵活使用
 * 2.switch case的用法
 */

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static int[] result = new int[2];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);


        while (input.hasNextLine()) {
            String str = input.nextLine();
            String[] list = str.split(";");
            for (int i = 0; i < list.length; i++) {
                move(list[i]);
            }

            System.out.printf("%d,%d", result[0], result[1]);
            System.out.println();

            result[0] = 0;
            result[1] = 0;


        }

    }

    public static void move(String input) {
        String patter = "^([ASWD])(\\d{1,2})$";
        Pattern r = Pattern.compile(patter);
        Matcher m = r.matcher(input);
        if (m.find()) {
//            System.out.println(m.group(0));
            int value = Integer.parseInt(m.group(2));
            switch (m.group(1)) {
                case "A":
                    result[0] -= value;
                    break;
                case "D":
                    result[0] += value;
                    break;
                case "W":
                    result[1] += value;
                    break;
                case "S":
                    result[1] -= value;
                    break;
                default:
                    break;

            }

        }

    }
}