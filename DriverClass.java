// Isabel Paynter & Zachary Boggs
import java.util.ArrayList;

public class DriverClass {
    public static void main(String[] args) {
        String fullName = "Erika T. Jones";
        String employeeNumber = "ej789";
        double payRate = 100.0, hoursWorked = 1.0;
        
        // TA will change the payrate and the hours worked to test your code
        Employee e;
        e = new Employee(fullName, employeeNumber, payRate, hoursWorked);
        
        System.out.println(e); // To Test your toString method
        e.printCheck(); // This prints the check of Erika T. Jones
        
        Company company = new Company();
        company.hire(new Employee("Saeed Happy", "sh895", 2, 200));
        company.hire(e);
        company.printCompanyInfo();
        
        company.hire(new Employee("Enrico Torres", "et897", 3, 150));
        
        company.printCheck("ab784");
        company.deleteEmployeesBySalary(256.36);
        company.reverseEmployees();
        
        System.out.println(company.SearchByName("WaLiD WiLLiAms"));
        company.printEmployees();
        
        System.out.println("Bye!");
    }
}

// Employee class
class Employee {
    private String fullName;
    private String employeeNumber;
    private double payRate;
    private double hoursWorked;

    public Employee(String fName, String empNumber, double rate, double hours) {
        fullName = fName;
        employeeNumber = empNumber;
        payRate = rate;
        hoursWorked = hours;
    }

    // Setters and Getters
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fName) {
        fullName = fName;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String empNumber) {
        employeeNumber = empNumber;
    }

    public double getPayRate() {
        return payRate;
    }

    public void setPayRate(double rate) {
        payRate = rate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hours) {
        hoursWorked = hours;
    }

    // toString method without @Override
    public String toString() {
        return "[" + employeeNumber + "/" + fullName + ", " + hoursWorked + " Hours @ " + payRate + " per hour]";
    }

    // Calculate net pay (private method)
    private double netPay() {
        double grossPay = hoursWorked * payRate;
        return grossPay - (grossPay * 0.06);
    }

    // Print paycheck
    public void printCheck() {
        double grossPay = hoursWorked * payRate;
        double tax = grossPay * 0.06;
        double netPay = netPay();
        
        System.out.println("--------------------------------------------------");
        System.out.println("Employee's name: " + fullName);
        System.out.println("Employee's number: " + employeeNumber);
        System.out.printf("Hourly rate of pay: %.2f\n", payRate);
        System.out.printf("Hours worked: %.2f\n\n", hoursWorked);
        System.out.printf("Total Gross Pay: $%.2f\n\n", grossPay);
        System.out.println("Deductions:");
        System.out.printf("Tax (6%%): $%.2f\n", tax);
        System.out.printf("Net Pay: %.2f Dollars\n", netPay);
        System.out.println("--------------------------------------------------");
    }
}

// Company class
class Company {
    private ArrayList<Employee> employeeList;
    private String companyName;
    private static String companyTaxId;

    // Static Setters and Getters for companyTaxId
    public static void setCompanyTaxId(String taxId) {
        companyTaxId = taxId;
    }

    public static String getCompanyTaxId() {
        return companyTaxId;
    }

    // Setters and Getters for companyName
    public void setCompanyName(String name) {
        companyName = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    // Constructor
    public Company() {
        employeeList = new ArrayList<>();
        companyName = "People's Place";
        companyTaxId = "v1rtua7C0mpan1";
    }

    // Hire an employee
    public boolean hire(Employee employee) {
        for (Employee e : employeeList) {
            if (e.getEmployeeNumber().equals(employee.getEmployeeNumber())) {
                return false; // Employee number must be unique
            }
        }
        employeeList.add(employee);
        return true;
    }

    // Print company info
    public void printCompanyInfo() {
        System.out.println("Company Name: " + companyName);
        System.out.println("Company Tax ID: " + companyTaxId);
        System.out.println("Number of Employees: " + employeeList.size());
    }

    // Print all employees
    public void printEmployees() {
        for (Employee e : employeeList) {
            System.out.println(e);
        }
    }

    // Count employees earning less than maxSalary
    public int countEmployees(double maxSalary) {
        int count = 0;
        for (Employee e : employeeList) {
            double grossPay = e.getHoursWorked() * e.getPayRate();
            if (grossPay < maxSalary) {
                count++;
            }
        }
        return count;
    }

    // Search employee by name (case insensitive)
    public boolean SearchByName(String fullName) {
        for (Employee e : employeeList) {
            if (e.getFullName().equalsIgnoreCase(fullName)) {
                return true;
            }
        }
        return false;
    }

    // Reverse employee list
    public void reverseEmployees() {
        int size = employeeList.size();
        for (int i = 0; i < size / 2; i++) {
            Employee temp = employeeList.get(i);
            employeeList.set(i, employeeList.get(size - i - 1));
            employeeList.set(size - i - 1, temp);
        }
    }

    // Delete employees by target salary
    public void deleteEmployeesBySalary(double targetSalary) {
        employeeList.removeIf(e -> e.getHoursWorked() * e.getPayRate() == targetSalary);
    }

    // Print check for employee by employeeNumber
    public void printCheck(String employeeNumber) {
        for (Employee e : employeeList) {
            if (e.getEmployeeNumber().equals(employeeNumber)) {
                e.printCheck();
                return;
            }
        }
        System.out.println("NO SUCH EMPLOYEE EXISTS");
    }
}
