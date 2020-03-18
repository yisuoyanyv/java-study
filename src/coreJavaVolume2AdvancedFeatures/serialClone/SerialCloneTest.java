package coreJavaVolume2AdvancedFeatures.serialClone;

import java.io.*;
import java.time.LocalDate;

/**
 * @author zhangjinglong
 * @date 2020-03-18-9:13 上午
 */

public class SerialCloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Employee harry = new Employee("Harry Hacker", 50000.0, 1999, 1, 1);
        //clone harrry
        Employee harry2 = (Employee) harry.clone();

        //mutate harry
        harry.raiseSalary(10);

        //now harry and the clone are different
        System.out.println(harry);
        System.out.println(harry2);
    }
}


/**
 * A class whose clone method uses serialization
 */
class SerialCloneable implements Cloneable, Serializable {
    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            //save the objects to a byte array
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            try (ObjectOutputStream out = new ObjectOutputStream(bout)) {
                out.writeObject(this);
            }

            //read a clone of the object from the byte array
            try (InputStream bin = new ByteArrayInputStream(bout.toByteArray())) {
                ObjectInputStream in = new ObjectInputStream(bin);
                return in.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            CloneNotSupportedException e2 = new CloneNotSupportedException();
            e2.initCause(e);
            throw e2;
        }
    }
}

/**
 * The familiar Employee class,redefiend to extend the SerialCloneable class
 */
class Employee extends SerialCloneable {
    private String name;
    private Double salary;
    private LocalDate hireDay;

    public Employee(String n, Double s, int year, int month, int day) {
        name = n;
        salary = s;
        hireDay = LocalDate.of(year, month, day);
    }

    public String getName() {
        return name;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public Double getSalary() {
        return salary;
    }

    /**
     * Raises the salary of this employee
     *
     * @param byPercent the percentage of the raise
     */
    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDay=" + hireDay +
                '}';
    }
}