package coreJavaVolumeFundamentals.abstractClasses;

/**
 * @author zhangjinglong
 * @date 2020-02-27-3:49 下午
 */

public class Student extends Person {
    private String major;

    public Student(String name,String major){
        //pass n to superclass constructor
        super(name);
        this.major=major;
    }

    @Override
    public String getDescription() {
        return "a student majoring in "+major;
    }
}
