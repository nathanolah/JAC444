/***********************************************
Workshop #3 Course:JAC444 - Semester 4
Last Name: Olah
First Name: Nathan
ID: 124723198
Section: NBB
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature
Date: 2020-06-15
**********************************************/

public class SalariedEmployee extends Employee {
    private double m_weeklySalary;

    // Constructor
    public SalariedEmployee(String firstName, String lastName, String ssn, double salary) {
        super(firstName, lastName, ssn);
        setWeeklySalary(salary);
    }

    // Getter Method
    public double getWeeklySalary() {
        return m_weeklySalary;
    }

    // Setter Method
    public void setWeeklySalary(double salary) {
        this.m_weeklySalary = validateWeeklySalary(salary);
    }

    // Validate weekly salary
    public double validateWeeklySalary(double salary) {
        if (salary <= 0.0)
            throw new ArithmeticException("Invalid weekly salary, must be greater than 0.0");
        else
            return salary;
    }

    public String toString() {
        return super.toString() + "Employee Weekly Salary: $" + getWeeklySalary();
    }

    public double getPaymentAmount() { 
        return getWeeklySalary(); 
    }
}