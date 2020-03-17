package coreJavaVolume2AdvancedFeatures.textFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * @author zhangjinglong
 * @date 2020-03-17-9:53 上午
 */

public class TextFileTest {
    public static void main(String[] args) throws IOException {
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("juliear", 70000, 1982, 12, 2);
        staff[1] = new Employee("jinglong zh", 1000000, 1999, 9, 9);
        staff[2] = new Employee("juliear", 89999, 2020, 2, 2);

        //save all employee records to the file employee.dat
        try (PrintWriter out = new PrintWriter("resources/text/employee.dat", "UTF-8")) {
            writeData(staff, out);
        }

        //retrieve all records into a new array
        try (Scanner in = new Scanner(new FileInputStream("resources/text/employee.dat"), "UTF-8")) {
            Employee[] newStaff = readData(in);

            //print the newly read employee records
            for (Employee e : newStaff) {
                System.out.println(e);
            }
        }
    }

    /**
     * Write all employees in an array to a print writer
     *
     * @param employees employees an array of employees
     * @param out       a print writer
     * @throws IOException
     */
    public static void writeData(Employee[] employees, PrintWriter out) throws IOException {
        //write number of employees
        out.println(employees.length);

        for (Employee e : employees) {
            writeEmployee(out, e);
        }
    }

    /**
     * Reads an array of employee from a scanner
     *
     * @param in the scanner
     * @return the array of emplooyees
     */
    public static Employee[] readData(Scanner in) {
        //retrieve the array size
        int n = in.nextInt();
        in.nextLine();//consume newline

        Employee[] employees = new Employee[n];
        for (int i = 0; i < n; i++) {
            employees[i] = readEmployee(in);
        }
        return employees;
    }

    /**
     * Write employee data to a print writer
     *
     * @param out the print writer
     * @param e
     */
    public static void writeEmployee(PrintWriter out, Employee e) {
        out.println(e.getName() + "|" + e.getSalary() + "|" + e.getHireDay());
    }

    /**
     * Reads employee data from a buffered reader
     *
     * @param in
     * @return
     */
    public static Employee readEmployee(Scanner in) {
        String line = in.nextLine();
        String[] tokens = line.split("\\|");
        String name = tokens[0];
        double salary = Double.parseDouble(tokens[1]);
        LocalDate hireDate = LocalDate.parse(tokens[2]);

        int year = hireDate.getYear();
        int month = hireDate.getMonthValue();
        int day = hireDate.getDayOfMonth();
        return new Employee(name, salary, year, month, day);
    }
}

class Employee {
    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee(String n, double s, int year, int month, int day) {
        name = n;
        salary = s;
        hireDay = LocalDate.of(year, month, day);
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

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    public String toString() {
        return getClass().getName() + "[name=" + name + ",salary=" + salary + ",hireDay=" + hireDay + "]";
    }
}
