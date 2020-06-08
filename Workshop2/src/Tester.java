/********************************************** 
Workshop #2
Course: JAC444 - Semester 4 
Last Name: Olah
First Name: Nathan
ID: 124723198 
Section: NBB 
This assignment represents my own work in accordance with Seneca Academic Policy. 
Signature Date: 2020/06/07
**********************************************/
import java.util.Scanner;
import java.util.InputMismatchException;

public class Tester {
	public static void main(String[] args) {
		boolean ok = false;
		Scanner input = new Scanner(System.in);
		
		do {
			System.out.println("1 - Compute personal income Tax");
			System.out.println("2 - Print the tax tables for taxable incomes (with range)");
			System.out.println("0 - Exit\n");
			System.out.print("Please select one of the choices: ");

			try {
				int choice = input.nextInt();
				
				if (choice == 1)
					IncomeTax.startChoice1();
				else if (choice == 2)	
					IncomeTax.startChoice2();
				else if (choice == 0)
					ok = true;
				else 
					System.out.println("Please enter a number between 0 - 2");

			} catch (InputMismatchException e) { // Catches an error if values are not integers
				input.nextLine(); // Clear buffer
				System.out.println("Please enter a number between 0 - 2");
			}
	
		} while (!ok);
		
	}
	
}
