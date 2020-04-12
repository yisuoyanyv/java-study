package jobInterview.niuke.test7;

import java.util.Scanner;

/**
 * @author zhangjinglong
 * @date 2020-04-11-1:10 下午
 * https://www.nowcoder.com/practice/3ab09737afb645cc82c35d56a5ce802a?tpId=37&tqId=21230&rp=0&ru=/ta/huawei&qru=/ta/huawei/question-ranking
 * 题目描述
 * 写出一个程序，接受一个正浮点数值，输出该数值的近似整数值。如果小数点后数值大于等于5,向上取整；小于5，则向下取整。
 *
 * 1.考察Math.round 的四舍五入方式
 */
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        float f = scan.nextFloat();

        System.out.print(Math.round(f));


    }
}