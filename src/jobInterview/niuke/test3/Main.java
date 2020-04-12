package jobInterview.niuke.test3;

/**
 * @author zhangjinglong
 * @date 2020-04-11-9:44 上午
 * https://www.nowcoder.com/practice/3245215fffb84b7b81285493eae92ff0?tpId=37&tqId=21226&rp=0&ru=/ta/huawei&qru=/ta/huawei/question-ranking
 * 题目描述
 * 明明想在学校中请一些同学一起做一项问卷调查，为了实验的客观性，他先用计算机生成了N个1到1000之间的随机整数（N≤1000），
 * 对于其中重复的数字，只保留一个，把其余相同的数去掉，不同的数对应着不同的学生的学号。然后再把这些数从小到大排序，
 * 按照排好的顺序去找同学做调查。请你协助明明完成“去重”与“排序”的工作(同一个测试用例里可能会有多组数据，希望大家能正确处理)。
 * <p>
 * 1.注意集合类的使用  Set用于去重，Collectins.sort()用于排序，ArrayList可替代数组使用
 * 2.不确定的输入使用 while循环， in.hasNextXXX()方法判断
 * 3.使用TreeSet数据结构实现排序和去重  考察集合类的特点
 */

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<ArrayList<Integer>> arrays = new ArrayList<>();
        while (in.hasNextInt()) {
            int arrlength = in.nextInt();
            ArrayList<Integer> arr = new ArrayList<>();
            for (int i = 0; i < arrlength; i++) {
                arr.add(in.nextInt());
            }
            arrays.add(arr);
        }

        for (List a : arrays) {
            //去重用set
            Set<Integer> s = new HashSet<>(a);
            a = new ArrayList(s);
            Collections.sort(a);
            for (Object i : a) {
                System.out.println((int) i);
            }
        }

    }

    //使用TreeSet数据结构实现排序和去重
    public static void main2(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            TreeSet<Integer> treeSet = new TreeSet<>();
            int nextInt = sc.nextInt();
            //输入
            for (int i = 0; i < nextInt; i++) {
                treeSet.add(sc.nextInt());
            }
            //输出
            for (Integer integer : treeSet) {
                System.out.println(integer);
            }
        }
    }


}
