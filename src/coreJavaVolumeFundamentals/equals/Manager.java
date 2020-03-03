package coreJavaVolumeFundamentals.equals;

import java.util.Objects;

/**
 * @author zhangjinglong
 * @date 2020-02-27-5:34 下午
 */

public class Manager extends Employee {
    private double bonus;

    public Manager(String name,double salary,int year,int month,int day){
        super(name,salary,year,month,day);
        this.bonus=0;
    }

    public double getSalary(){
        double baseSalary=super.getSalary();
        return baseSalary+this.bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public boolean equals(Object otherObject) {
        if(!super.equals(otherObject)) return false;
        Manager ohter=(Manager) otherObject;
        //super.equals checked that this and other belong to the same class
        return bonus==ohter.bonus;
    }

    @Override
    public int hashCode() {
        return super.hashCode()+17*Double.hashCode(bonus);
    }

    public String toString(){
        return super.toString()+"[bonus="+bonus+"]";
    }
}
