package jobInterview.niuke.test5;

import java.util.Scanner;

/**
 * @author zhangjinglong
 * @date 2020-04-11-10:57 上午
 * https://www.nowcoder.com/practice/8f3df50d2b9043208c5eed283d1d4da6?tpId=37&tqId=21228&rp=0&ru=/ta/huawei&qru=/ta/huawei/question-ranking
 * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。（多组同时输入 ）
 * <p>
 * 1.进制转化  Long.parseLong(str,16)
 * 2.记得删除 进制前的标示字符串， 0xA ，删除0x
 */

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            String soucce = in.nextLine();
            System.out.println(Long.parseLong(soucce.substring(2), 16));
        }


    }
}
