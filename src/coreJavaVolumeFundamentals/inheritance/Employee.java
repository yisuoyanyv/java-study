package coreJavaVolumeFundamentals.inheritance;

import java.time.LocalDate;

/**
 * @author zhangjinglong
 * @date 2020-02-27-11:12 上午
 */

public class Employee {
    private String name;
    private double salary;
    private LocalDate hireDay;



    public Employee(String name, double salary, int year, int month, int day){
        this.name=name;
        this.salary=salary;
        this.hireDay=LocalDate.of(year,month,day);
    }

    public void raiseSalary(double byPrecent){
        double raise=salary*byPrecent/100;
        salary+=raise;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }
}
