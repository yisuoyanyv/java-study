package coreJavaVolumeFundamentals.inheritance;

/**
 * @author zhangjinglong
 * @date 2020-02-27-11:17 上午
 */

public class Manager extends Employee {
    private double bonus;

    public Manager(String name,double salary,int year,int month,int day){
        super(name,salary,year,month,day);
        this.bonus=0;
    }

    @Override
    public double getSalary() {
        double baseSalary=super.getSalary();
        return baseSalary+bonus;
    }

    public void setBonus(double b){
        bonus=b;
    }

    public double getBonus() {
        return bonus;
    }
}
