package coreJavaVolumeFundamentals.interfaces;

import java.util.Arrays;

/**
 * @author zhangjinglong
 * @date 2020-02-29-11:31 上午
 */

public class EmployeeSortTest {
    public static void main(String[] args) {
        Employee[] staff=new Employee[3];

        staff[0]=new Employee("Harry Hacker",300);
        staff[1]=new Employee("Carl Cracker",200);
        staff[2]=new Employee("Tony Tester",400);

        Arrays.sort(staff);

        //print out infromation about all Employee objects
        for(Employee e:staff){
            System.out.println("name="+e.getName()+",salary="+e.getSalary());
        }
    }


}
