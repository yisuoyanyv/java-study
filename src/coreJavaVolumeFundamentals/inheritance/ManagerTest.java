package coreJavaVolumeFundamentals.inheritance;

/**
 * @author zhangjinglong
 * @date 2020-02-27-11:19 上午
 */

public class ManagerTest {

    public static void main(String[] args) {
        //consturct a manager object
        Manager boss=new Manager("Carl Craer",80000,1999,12,12);
        boss.setBonus(5000);

        Employee[]  staff=new Employee[3];
        //fill the staff array with Manager and Employee objects
        staff[0]=boss;
        staff[1]=new Employee("Harry Hacker",50000,1989,10,1);
        staff[2]=new Employee("Tommy Tester",40000,1990,12,12);

        //print out information abount all Employee objects
        for(Employee e:staff){
            System.out.println("name="+e.getName()+",salary="+e.getSalary());
        }
    }
}
