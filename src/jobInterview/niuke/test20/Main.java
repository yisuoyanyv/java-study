package jobInterview.niuke.test20;

/**
 * @author zhangjinglong
 * @date 2020-04-25-5:32 下午
 */

// 本题为考试单行多行输入输出规范示例，无需提交，不计分。

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Map<String, Integer> map = new HashMap<>();
        Scanner in = new Scanner(System.in);

        String input = in.nextLine();

        String[] inputArr = input.split("@");
        String[] fullString = inputArr[0].split(",");//全量
        String[] zhanyongString = inputArr[1].split(",");//已占用
        for (String s : fullString) {//将全量字符放入map
            String[] sf = s.split(":");
            map.put(sf[0], Integer.valueOf(sf[1]));
        }
        for (String s : zhanyongString) {//将map中的已占用字符处理掉

            String[] sz = s.split(":");
            if (map.keySet().contains(sz[0])) {
                int cha = map.get(sz[0]) - Integer.parseInt(sz[1]);
                if (cha == 0) {
                    map.remove(sz[0]);
                } else {
                    map.put(sz[0], cha);
                }

            } else {
                throw new RuntimeException("已占用字符集中出现全量字符串集中没有的字符");
            }
        }
        StringBuilder sb = new StringBuilder();
        map.forEach((k, v) -> {
            sb.append(String.format("%s:%s,", k, v));

        });
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());
    }
}