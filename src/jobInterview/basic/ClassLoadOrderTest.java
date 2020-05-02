package jobInterview.basic;

/**
 * @author zhangjinglong
 * @date 2020-04-19-12:52 下午
 * <p>
 * 类加载 顺序 类的实例化顺序
 * 父类，子类
 */

public class ClassLoadOrderTest {
    public static void main(String[] args) {
        Student s = new Student();
    }
}

class Person {
    static String name = "父类静态变量";
    String addr = "父类非静态变量";

    static {
        System.out.println("父类静态代码块");
    }

    {
        System.out.println("父类非静态代码块");
    }

    public Person() {
        System.out.println("父类构造方法");
    }

}

class Student extends Person {
    static String name1 = "子类静态变量";
    String addr1 = "子类非静态变量";

    static {
        System.out.println("子类静态代码块");

    }

    {
        System.out.println("子类非静态代码块");
    }

    public Student() {
        System.out.println("子类构造方法");
    }

}

