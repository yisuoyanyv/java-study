package jobInterview.basic;

/**
 * @author zhangjinglong
 * @date 2020-03-04-9:12 下午
 */

public class MemberAndLocalVariables {
    static int s;//成员变量  类变量
    int i;//成员变量  实例变量
    int j;//成员变量  实例变量
    {
        int i=1;//非静态代码块中的局部变量
        i++;
        j++;
        s++;
    }
    public void test(int j){//形参，局部变量
        j++;
        i++;
        s++;
    }

    public static void main(String[] args) {//形参，局部变量
        MemberAndLocalVariables obj1=new MemberAndLocalVariables();// i=0,j=1,s=1;  局部变量
        MemberAndLocalVariables obj2=new MemberAndLocalVariables();//i=0,j=1,s=2;  局部变量
        obj1.test(10);//i=1;j=1;s=3;
        obj1.test(20);//i=2;j=1,s=4;
        obj2.test(30);//i=1,j=1,s=5
        System.out.println(obj1.i+","+obj1.j+","+obj1.s);//2,1,5
        System.out.println(obj2.i+","+obj2.j+","+obj2.s);//1,1,5

    }
}
