package coreJavaVolumeFundamentals.equals;

/**
 * @author zhangjinglong
 * @date 2020-02-27-5:42 下午
 */

public class EqualsTest {
    public static void main(String[] args) {
        Employee alice1=new Employee("Alice Adams",70000,1982,12,12);
        Employee alice2=alice1;
        Employee alice3=new Employee("Alice Adams",70000,1982,12,12);
        Employee bob=new Employee("Bob Brandson",50000,1983,12,12);

        System.out.println("alice1==alice2:"+(alice1==alice2));
        System.out.println("alice1==alice3:"+(alice1==alice3));

        System.out.println("alice1.equals(alice3):"+alice1.equals(alice3));
        System.out.println("alice1.equals(bob):"+alice1.equals(bob));

        System.out.println("bob.toString():"+bob);

        Manager carl=new Manager("Carl Cracker",80000,1999,12,12);
        Manager boss=new Manager("Carl Cracker",80000,1999,12,12);
        boss.setBonus(5000);
        System.out.println("boss.toString():"+boss);
        System.out.println("carl.equals(boss):"+carl.equals(boss));
        System.out.println("alice1.hashCode():"+alice1.hashCode());
        System.out.println("alice3.hashCode():"+alice3.hashCode());
        System.out.println("bob.hashCode():"+bob.hashCode());
        System.out.println("carl.hashCode():"+carl.hashCode());



    }
}
