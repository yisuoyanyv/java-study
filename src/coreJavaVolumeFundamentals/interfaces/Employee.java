package coreJavaVolumeFundamentals.interfaces;

/**
 * @author zhangjinglong
 * @date 2020-02-29-11:26 上午
 */

public class Employee implements Comparable<Employee> {
    private String name;
    private double salary;

    public Employee(String name,double salary){
        this.name=name;
        this.salary=salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void raiseSalary(double byPercent){
        double rise=salary*byPercent/100;
        salary+=rise;
    }

    /**
     * Compares employees by salary
     * @param other another Employee object
     * @return a negative value if this employee has a lower salary than
     * otherObject, 0 if the salaries are the same,a positive value otherwise
     */
    @Override
    public int compareTo(Employee other) {
        return Double.compare(salary,other.salary);
    }
}
