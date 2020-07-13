/**********************************************
Workshop 6
Course: JAC444 - Semester 4
Last Name: Olah 
First Name: Nathan  
ID: 124723198 
Section: NBB 
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature Date: 2020/07/10
**********************************************/

import javafx.application.Application;
import java.util.Scanner;

public class Tester {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		boolean restart = true;
		while (restart) {
			System.out.print("To launch Task1 Enter '1' or Task2 Enter '2' or '0' to exit: ");			
			int choice = input.nextInt();
			
			switch(choice) {
				case 1 : Application.launch(Task1.class, args);
					break;
				case 2 : Task2.start();
					break;
				case 0 :
					restart = false;
					break;
				default: System.out.println("Invalid choice");
					break;
			}
			
		}
		
	} 
}
