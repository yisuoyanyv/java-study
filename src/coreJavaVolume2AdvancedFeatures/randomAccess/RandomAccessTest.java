package coreJavaVolume2AdvancedFeatures.randomAccess;

import java.io.*;
import java.time.LocalDate;

/**
 * @author zhangjinglong
 * @date 2020-03-17-10:53 下午
 */

public class RandomAccessTest {
    public static void main(String[] args) throws IOException {
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("juliear", 70000, 1982, 12, 2);
        staff[1] = new Employee("jinglong zh", 1000000, 1999, 9, 9);
        staff[2] = new Employee("juliear", 89999, 2020, 2, 2);

        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("resources/text/employee.dat"))) {
            //save all employee records to the file employee.dat
            for (Employee e : staff) {
                writeData(out, e);
            }
        }

        try (RandomAccessFile in = new RandomAccessFile("resources/text/employee.dat", "r")) {
            //retrieve all records into a array
            //compute the array size
            int n = (int) (in.length() / Employee.RECORD_SIZE);
            Employee[] newstaff = new Employee[n];

            //read employees in reverse order
            for (int i = n - 1; i >= 0; i--) {
                newstaff[i] = new Employee();
                in.seek(i * Employee.RECORD_SIZE);
                newstaff[i] = readData(in);
            }

            //print the newly read employee records
            for (Employee e : newstaff) {
                System.out.println(e);
            }
        }


    }

    /**
     * Writes employee data to a data output
     *
     * @param out the data output
     * @param e   the employee
     * @throws IOException
     */
    public static void writeData(DataOutputStream out, Employee e) throws IOException {
        DataIO.writeFixedString(e.getName(), Employee.NAME_SIZE, out);
        out.writeDouble(e.getSalary());
        LocalDate hireDay = e.getHireDay();
        out.writeInt(hireDay.getYear());
        out.writeInt(hireDay.getMonthValue());
        out.writeInt(hireDay.getDayOfMonth());
    }

    /**
     * Reads employee data from a data input
     *
     * @param in the data input
     * @return the employee
     * @throws IOException
     */
    public static Employee readData(DataInput in) throws IOException {
        String name = DataIO.readFixedString(Employee.NAME_SIZE, in);
        double salary = in.readDouble();
        int y = in.readInt();
        int m = in.readInt();
        int d = in.readInt();
        return new Employee(name, salary, y, m, d);
    }
}
