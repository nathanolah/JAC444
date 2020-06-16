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

public class HourlyEmployee extends Employee {
    private double m_wage;
    private double m_hours;

    // Constructor
    public HourlyEmployee(String firstName, String lastName, String ssn, double hourlyWage, double hoursWorked) {
        super(firstName, lastName, ssn);
        setHourlyWage(hourlyWage);
        setHoursWorked(hoursWorked);
    }

    // Getter Methods
    public double getHourlyWage() {
        return m_wage;
    }

    public double getHoursWorked() {
        return m_hours;
    }

    // Setter Methods
    public void setHourlyWage(double hourlyWage) {
        this.m_wage = validateHourlyWage(hourlyWage);
    }

    public void setHoursWorked(double hoursWorked) {
        this.m_hours = validateHoursWorked(hoursWorked);
    }

    // Validate hourly wage
    public double validateHourlyWage(double hourlyWage) {
        if (hourlyWage <= 0.0)
            throw new ArithmeticException("Invalid hourly wage, must be greater than 0.0");
        else
            return hourlyWage; 
    }

    // Validate hours worked
    public double validateHoursWorked(double hoursWorked) {
        if (hoursWorked < 0.0 || hoursWorked > 168.0)
            throw new ArithmeticException("Invalid hours, must be between 0.0 - 168.0");
        else
            return hoursWorked;
    }

    @Override
    public String toString() {
        return super.toString() + "Hourly wage: $" + getHourlyWage() + " Total hours: " + getHoursWorked();
    }
    
    // Total amount of hours worked
    public double getPaymentAmount() {
        double totalAmount = 0;
        double wage = getHourlyWage();

        if (getHoursWorked() > 40) {
            totalAmount = wage * 40 + (wage * 1.5 * (getHoursWorked() - 40));
        } else {
            totalAmount = wage * getHoursWorked();
        }

        return totalAmount;
    }
    
} 