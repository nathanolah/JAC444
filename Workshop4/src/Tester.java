/**********************************************
Workshop #4
Course:JAC444 - Semester 4
Last Name: Olah
First Name: Nathan
ID: 124723198
Section: NBB
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature
Date: 2020/06/21
**********************************************/

import java.util.InputMismatchException;
import java.util.Scanner;

public class Tester {
    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        boolean restart = true;
        boolean valid = false;
        int choice = 0;

        while(restart) {
            System.out.println("1 - To play Hangman");
            System.out.println("2 - To start Task 2");
            System.out.println("0 - To exit");
            while (!valid) {
                System.out.print("> ");
                try {
                    choice = input.nextInt();
                    if (choice == 1 || choice == 2 || choice == 0) {
                        valid = true;
                    } else {
                        valid = false;
                    }
                } catch (InputMismatchException e) {
                    System.out.println(e);
                    input.nextLine();
                }
            }
            valid = false;
            
            if (choice == 1) {
                Hangman.startHangman("./hangman.txt");
            } else if (choice == 2) {
                Task2.task2("./Coronavirus_file.txt");
            } else if (choice == 0) {
                restart = false;
            }

        }

    }
}