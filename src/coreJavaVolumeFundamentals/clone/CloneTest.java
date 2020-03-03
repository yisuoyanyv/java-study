package coreJavaVolumeFundamentals.clone;

/**
 * @author zhangjinglong
 * @date 2020-02-29-12:39 下午
 */

public class CloneTest {
    public static void main(String[] args) {
        try{
            Employee  original=new Employee("John Q. Public",50000);
            original.setHireDay(2020,1,1);
            Employee copy=original.clone();
            copy.raiseSalary(10);
            copy.setHireDay(2020,2,2);
            System.out.println("orinal:"+original);

            System.out.println("copy:"+copy);
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
    }
}
