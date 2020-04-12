package jobInterview.niuke.test8;

/**
 * @author zhangjinglong
 * @date 2020-04-11-4:44 下午
 * <p>
 * https://www.nowcoder.com/practice/de044e89123f4a7482bd2b214a685201?tpId=37&tqId=21231&rp=0&ru=/ta/huawei&qru=/ta/huawei/question-ranking
 * <p>
 * 合并表记录
 * <p>
 * 题目描述
 * 数据表记录包含表索引和数值（int范围的整数），请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照key值升序进行输出。
 * <p>
 * 1.考察treeMap的特性，按key排序
 * 2.Map的一个方法getOrDefault 十分实用
 */

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Map<Integer, Integer> result = new TreeMap<>();

        int count = Integer.parseInt(scan.nextLine());

        for (int i = 0; i < count; i++) {
            String[] line = scan.nextLine().split("\\s+");
            result.getOrDefault(Integer.parseInt(line[0]), 0);
            result.put(Integer.parseInt(line[0]), Integer.sum(result.getOrDefault(Integer.parseInt(line[0]), 0), Integer.parseInt(line[1])));
        }
        result.forEach((k, v) -> System.out.println(k + " " + v));

    }


}
