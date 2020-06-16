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

public class BasePlusCommissionEmployee extends CommissionEmployee {
    private double m_baseSalary;

    // Constructor
    public BasePlusCommissionEmployee(String firstName, String lastName, String ssn, double sales, double rate, double salary) {
        super(firstName, lastName, ssn, sales, rate);
        setBaseSalary(salary);
    }

    // Getter Method
    public double getBaseSalary() {
        return this.m_baseSalary;
    }

    // Setter Method
    public void setBaseSalary(double baseSalary) {
        this.m_baseSalary = validateBaseSalary(baseSalary);
    }

    // Validate base salary
    public double validateBaseSalary(double baseSalary) {
        if (baseSalary <= 0.0)
            throw new ArithmeticException("Invalid value, base salary must be greater than 0.0");
        else
            return baseSalary;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + "Base Salary: $" + getBaseSalary();
    }
    
    public double getPaymentAmount() {
        return (getBaseSalary() + (getBaseSalary() * 0.10)) + super.getPaymentAmount(); 
    }
}