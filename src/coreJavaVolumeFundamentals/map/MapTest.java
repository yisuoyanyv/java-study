package coreJavaVolumeFundamentals.map;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangjinglong
 * @date 2020-03-08-10:25 下午
 */

public class MapTest {

    public static void main(String[] args) {
        Map<String,Employee>  staff=new HashMap<>();
        staff.put("1-1-1",new Employee("zjl"));
        staff.put("2-2-2",new Employee("ctt"));
        staff.put("3-3-3",new Employee("wzy"));
        staff.put("3-4-3",new Employee("kdy"));
        staff.put("3-5-3",new Employee("hjj"));

        //print all entries
        System.out.println(staff);

        //remove an entry
        staff.remove("3-4-3");
        //replace an entry
        staff.put("3-3-3",new Employee("zzz"));
        //look up a vale
        System.out.println(staff.get("3-3-3"));
        //iterate through all entries
        staff.forEach((k,v)-> System.out.println("key="+k+",value="+v));
    }
}

class  Employee{
    private String name;

    public Employee(String name){
        this.name=name;
    }
}
