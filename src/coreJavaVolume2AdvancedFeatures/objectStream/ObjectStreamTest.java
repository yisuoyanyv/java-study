package coreJavaVolume2AdvancedFeatures.objectStream;

import java.io.*;

/**
 * @author zhangjinglong
 * @date 2020-03-17-11:45 下午
 */

public class ObjectStreamTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Employee harry = new Employee("Hary Hacker", 40000, 1989, 12, 2);
        Manager carl = new Manager("Carl Cracker", 80000, 1999, 2, 2);
        carl.setSecretary(harry);

        Manager tony = new Manager("Tony Tester", 88888, 2011, 4, 4);
        tony.setSecretary(harry);

        Employee[] staff = new Employee[3];
        staff[0] = carl;
        staff[1] = harry;
        staff[2] = tony;

        //save all employee records to the file employee.dat
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("resources/text/employee.dat"))) {
            out.writeObject(staff);
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("resources/text/employee.dat"))) {
            //retrieve all records into a new array
            Employee[] newstaff = (Employee[]) in.readObject();

            //raise secretary's salary
            newstaff[1].raiseSalary(10);

            //print the newly read employee records
            for (Employee e : newstaff) {
                System.out.println(e);
            }
        }

    }
}
