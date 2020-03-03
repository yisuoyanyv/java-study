package coreJavaVolumeFundamentals.abstractClasses;

/**
 * @author zhangjinglong
 * @date 2020-02-27-3:51 下午
 */

public class PersonTest {
    public static void main(String[] args) {

        Person[] people=new Person[2];

        //fill the people array with Student and Employee objects
        people[0] = new Employee("Harry Hacker",4000,2020,1,1);
        people[1]=new Student("zjl","Chinese");

        //print out names and descriptions of all Person objects

        for(Person p:people){
            System.out.println(p.getName()+","+p.getDescription());
        }
    }

}
