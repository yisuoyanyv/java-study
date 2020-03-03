package coreJavaVolumeFundamentals.abstractClasses;

/**
 * @author zhangjinglong
 * @date 2020-02-27-3:43 下午
 */

public abstract class Person {
    public abstract String getDescription();
    private String name;

    public Person(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
