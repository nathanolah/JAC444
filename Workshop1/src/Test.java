/**********************************************
Workshop #1
Course: JAC444 - Semester 4
Last Name: Olah
First Name: Nathan 
ID: 124723198
Section: NBB
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature
Date: 2020-05-29
**********************************************/

import java.util.Scanner;

public class Test {
	
	// Main Tester
	public static void main(String[] args) {
		int taskNumber;	
		Scanner input = new Scanner(System.in);
       
    	System.out.print("Please enter the task number you would like to run '1' or '2': ");
        taskNumber = input.nextInt();
        System.out.println();
        	
    	if (taskNumber == 1 || taskNumber == 2) {
    		
    		// Menu
    		switch (taskNumber) {
    		case 1:
    			MaxLocation.startTask1();
    			break;
    		case 2:
    			CrapsGame.startTask2();
    			break;
    		}
    		
    	} 
        	
        // Close input
        input.close();
		
	}

}
