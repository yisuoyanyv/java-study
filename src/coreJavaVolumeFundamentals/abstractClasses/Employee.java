package coreJavaVolumeFundamentals.abstractClasses;

import java.time.LocalDate;

/**
 * @author zhangjinglong
 * @date 2020-02-27-3:45 下午
 */

public class Employee extends Person {
    private double salary;
    private LocalDate hireDay;

    public Employee(String name,double salary,int year,int month,int day){
        super(name);
        this.salary=salary;
        this.hireDay=LocalDate.of(year,month,day);

    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    @Override
    public String getDescription() {
        return String.format("an employee with a salary of $%.2f",salary);
    }

    public void raiseSalary(double byPercent){
        double raise=salary*byPercent/100.0;
        salary+=raise;
    }
}
