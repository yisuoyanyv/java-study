package jobInterview.niuke.test14;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhangjinglong
 * @date 2020-04-12-2:38 下午
 * https://www.nowcoder.com/practice/184edec193864f0985ad2684fbc86841?tpId=37&tqId=21243&rp=0&ru=/ta/huawei&qru=/ta/huawei/question-ranking
 * <p>
 * 题目描述
 * 密码要求:
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 1.长度超过8位
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 2.包括大小写字母.数字.其它符号,以上四种至少三种
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 3.不能有相同长度超2的子串重复
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 说明:长度超过2的子串
 * <p>
 * <p>
 * 输入描述:
 * 一组或多组长度超过2的子符串。每组占一行
 * <p>
 * 输出描述:
 * 如果符合要求输出：OK，否则输出NG
 * <p>
 * 示例1
 * 输入
 * 复制
 * 021Abc9000
 * 021Abc9Abc1
 * 021ABC9000
 * 021$bc9000
 * 输出
 * 复制
 * OK
 * NG
 * NG
 * OK
 * <p>
 * 1.正则表达式
 * 2.字串匹配
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String input = scanner.next();
            System.out.println(check(input));
        }

    }

    public static String check(String input) {
        //            1.长度超过8位
        if (input.length() < 9) {
            return "NG";
        }

        //            2.包括大小写字母.数字.其它符号,以上四种至少三种

        int count = 0;
        String[] patters = {"[a-z]", "[A-Z]", "[0-9]", "[^a-zA-Z0-9]"};
        for (int i = 0; i < patters.length; i++) {
            Pattern p = Pattern.compile(patters[i]);
            Matcher matcher = p.matcher(input);
            if (matcher.find()) count++;

        }
        if (count < 3) return "NG";

        //            3.不能有相同长度超2的子串重复
//        for(int i=0;i<input.length()-3;i++){
//            if(input.substring(i+3).contains(input.substring(i,i+3))){
//                return "NG";
//            }
//        }


        //TODO
        if (input.replaceAll("(.{3,})(?=.{3,}\\1)", "").length() < input.length())
            return "NG";
        return "OK";
    }
}
