package coreJavaVolumeFundamentals;

import java.util.Random;

/**
 * 对象的构造函数示例
 * @author zhangjinglong
 * @date 2020-02-26-5:45 下午
 */

public class ConstructorTest {
    public static void main(String[] args) {
        //fill the staff array with three Employee objects
        Employee[] staff=new Employee[3];

        staff[0]=new Employee("Harry",1000);
        staff[1]=new Employee(6000);
        staff[2]=new Employee();

        //print out information about all Employee objects
        for(Employee e:staff){
            System.out.println("name="+e.getName()+",id="+e.getId()+",salary="+e.getSalary());
        }

    }


}
class Employee{
    private static int nextId;

    private int id;
    private String name="";//instance field initialization
    private double salary;

    //static initialization block
    static{
        Random generator=new Random();
        //set nextId to a random number between 0 and 9999
        nextId=generator.nextInt(10000);
    }
    //object initialization block
    {
        id=nextId;
        nextId++;
    }
    //three overloaded constructors
    public Employee(String name,double salary){
        this.name=name;
        this.salary=salary;
    }

    public Employee(double salary){
        //calls the Employee(String,double) constructor
        this("Employee#"+nextId,salary);
    }
    //the default constructor
    public Employee(){
        //name initialized to "" --see above
        //salary not explicitly set --initialized to 0
        //id initialized in initialization block
    }

    public String getName(){
        return this.name;
    }
    public double getSalary(){
        return this.salary;
    }

    public int getId() {
        return id;
    }
}
