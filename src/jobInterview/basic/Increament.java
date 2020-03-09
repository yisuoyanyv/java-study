package jobInterview.basic;

/**
 * 理解局部变量表和操作数栈
 * 理解运算优先级
 * @author zhangjinglong
 * @date 2020-03-03-10:13 下午
 * 小结
 * 赋值 = 最后计算
 * =右边的从左到右加载值一次压入操作数栈
 * 实际先算哪个，看运算符优先级
 * 自增、自减操作都是直接修改变量的值，不经过操作数栈
 * 最后的赋值之前，临时结果也是存储在操作数栈中
 *
 * 建议《JVM虚拟机规范》关于指令的部分
 */

public class Increament {
    public static void main(String[] args) {
        int i=1; //1
        i=i++;   //i++ 2    赋值操作后，i变1
        int j=i++;  //j=1   i++后 i为2
        int k=i+ ++i *i++; //1. i入操作数栈 栈内 2.++i i变3，栈内 2 3， 3，i++ i先入栈，栈内 2 3 3，i++后i为4
        //4.把操作数栈中前两个弹出来求乘积，结果再压入栈  栈内 2 9    5.把操作数栈中的值弹出来求和再赋值给k，此时，栈空，k=11


        System.out.println("i="+i);//4
        System.out.println("j="+j);//1
        System.out.println("k="+k);//11
    }
}
