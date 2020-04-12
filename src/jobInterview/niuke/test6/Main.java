package jobInterview.niuke.test6;

/**
 * @author zhangjinglong
 * @date 2020-04-11-11:18 上午
 * <p>
 * 题目描述
 * 功能:输入一个正整数，按照从小到大的顺序输出它的所有质因子（如180的质因子为2 2 3 3 5 ）
 * <p>
 * 最后一个数后面也要有空格
 * <p>
 * 1.思路很重要，递归的方法比较简单
 */

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Long input = in.nextLong();
        System.out.println(getResult(input));


    }

    public static String getResult(long ulDataInput) {

        Math.sqrt(ulDataInput);
        //先计算出小于输入数字的质数列表
        if (ulDataInput < 2) {
            return null;
        }
        ArrayList<Long> zhishu = new ArrayList<>();
        for (long i = 0; i <= Math.sqrt(ulDataInput); i++) {
            //判断一个数字是否为质数
            if (isZhishu(i)) {
                //加入到质数列表中
                zhishu.add(i);
            }
        }
        StringBuffer sb = new StringBuffer();
        //然后从质数列表中依次选择，可以被输入整除的数字
        int length = zhishu.size();
        int count = 0;
        while (ulDataInput != 1) {
            if (ulDataInput % zhishu.get(count) == 0) {
                ulDataInput /= zhishu.get(count);
                sb.append(zhishu.get(count) + " ");
                // System.out.print(zhishu.get(count)+" ");
            } else {
                count++;
            }

        }


        return sb.toString();
    }

    //判断一个数字是否为质数
    public static boolean isZhishu(long input) {
        if (input < 2) return false;
        for (long i = 2; i <= Math.sqrt(input); i++) {
            if (input % i == 0) {
                return false;
            }
        }
        return true;
    }


    //别的解法：


    public static void main2(String[] args) {
        Scanner scan = new Scanner(System.in);
        long num = Long.parseLong(scan.next());
        getPrimer(num);
    }


    public static void getPrimer(long num) {
        for (int i = 2; i <= num; i++) {
            if (num % i == 0) {
                System.out.print(i + " ");
                getPrimer(num / i);
                break;
            }
            if (i == num) {
                System.out.print(i + "");
            }
        }
    }

}
