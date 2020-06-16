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

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class PayrollSystemTest {
    static Scanner input = new Scanner(System.in);

    // First loop
    static public void firstLoop(ArrayList<Employee> empArray) {
        for (int i = 0; i < empArray.size(); i++) {
        	// Checks if employee is an instance of BasePlusCommissionEmployee
            if (empArray.get(i) instanceof BasePlusCommissionEmployee) {  
                System.out.printf("%s\n%s: $%,.2f\n\n", ((BasePlusCommissionEmployee)empArray.get(i)), "Earned", empArray.get(i).getPaymentAmount());
            } else {
                System.out.printf("%s\n%s: $%,.2f\n\n", empArray.get(i), "Earned", empArray.get(i).getPaymentAmount());
            }
        }
    }
    
    // Second loop
    static public void secondLoop(ArrayList<Employee> empArray) {
        for (int i = 0; i < empArray.size(); i++)
            System.out.println(empArray.get(i).getClass()); // Outputs the name of the class 
    }

    // Validate double value, and prints a custom message
    public static double setValidDouble(String message) {
        boolean ok = false;
        double value = 0;

        System.out.print(message); 
        do {
            try {
                value = input.nextDouble();
                ok = true;
            } catch (Exception e) {
                System.out.print("Please a valid value: ");
                input.nextLine(); // clear buffer
            }
        } while (!ok);

        return value;
    }

    // Validate choice value
    static public int validateChoice() {
        boolean ok = false;
        int choice = 0;
        System.out.println("Enter a value between 1 - 4");
        do {
            try {
                choice = input.nextInt();
                if (choice < 1 || choice > 4) {
                    System.out.println("Please enter a value between 1 - 4");
                    input.nextLine();
                } else {
                	ok = true;
                }
            } catch (InputMismatchException e) {
                System.out.println(e + "\n");
                input.nextLine();
            }
        } while(!ok);

        return choice;
    }

    // Prints custom message and returns inputed string
    public static String getString(String message) {
        System.out.print(message);
        String str = input.next();
        return str;
    }
    
    // Sets values for the specified type of employee
    public static Employee setEmployees() {
        boolean ok = false;
        String firstName = "";
        String lastName = "";
        String ssn = "";
        double sales = 0;
        double rate = 0;
        double salary = 0; 
        double hoursWorked = 0;
        double hourlyWage = 0;
        Employee emp = null;
        
        // Choices 
        System.out.println("1 - Commission Employee");
        System.out.println("2 - Hourly Employee");
        System.out.println("3 - Salaried Employee");
        System.out.println("4 - Base Plus Commission Employee\n");
        int choice = validateChoice();

        do {
            try {
                if (choice == 1) {
                    firstName = getString("First name: ");
                    lastName = getString("Last name: ");
                    ssn = getString("Social Security Number: ");
                    sales = setValidDouble("Please enter the total in sales: $"); // set sales
                    rate = setValidDouble("Please enter a rate between 0.0 and 1.0: "); // sets rates
                    emp = new CommissionEmployee(firstName, lastName, ssn, sales, rate);
                }
                else if (choice == 2) {
                    firstName = getString("First name: ");
                    lastName = getString("Last name: ");
                    ssn = getString("Social Security Number: ");
                    hourlyWage = setValidDouble("Please enter the hourly wage: $"); // set hourly wage
                    hoursWorked = setValidDouble("Please enter the amount of hours worked: "); // set hours worked
                    emp = new HourlyEmployee(firstName, lastName, ssn, hourlyWage, hoursWorked);
                }
                else if (choice == 3) {
                    firstName = getString("First name: ");
                    lastName = getString("Last name: ");
                    ssn = getString("Social Security Number: ");
                    salary = setValidDouble("Please enter the amount of the salary: $"); // set the salary
                    emp = new SalariedEmployee(firstName, lastName, ssn, salary);
                }
                else if (choice == 4) {
                    firstName = getString("First name: ");
                    lastName = getString("Last name: ");
                    ssn = getString("Social Security Number: ");
                    sales = setValidDouble("Please enter the total in sales: $"); // set sales
                    rate = setValidDouble("Please enter a rate between 0.0 and 1.0: "); // sets rates
                    salary = setValidDouble("Please enter the amount of the salary: $"); // set the salary
                    emp = new BasePlusCommissionEmployee(firstName, lastName, ssn, sales, rate, salary);
                }
        
                ok = true;

            } catch (ArithmeticException e) {
                System.out.println(e + "\n");
                input.nextLine();
            }

        } while (!ok);

        return emp;
    }   
    
    // Validate amount value
    public static int getAmount() {
        boolean ok = true;
        int amount = 0;
        do {
            try {
                amount = input.nextInt();
                if (amount < 1) {
                    ok = false;
                    input.nextLine();
                    System.out.println("Please enter a value greater than 0");
                } else {
                	ok = true;
                }
            } catch (InputMismatchException e) {
                System.out.println(e + "\n" + "Please enter a valid integer");
                input.nextLine();
                ok = false;
            }
        } while (!ok);

        return amount;
    }

    public static void main(String[] args) {
        int amount = 0;
        ArrayList<Employee> employees = new ArrayList<Employee>();
        
        System.out.print("How many employees would you like to create: ");
        amount = getAmount();
        System.out.println();

        for (int i = 0; i < amount; i++) {
            System.out.println();
            employees.add(setEmployees()); // Adds the new employees
        }

        System.out.println();
        System.out.println("Employees processed individually: \n");
        firstLoop(employees);
        System.out.println();
        secondLoop(employees);

    }

}
