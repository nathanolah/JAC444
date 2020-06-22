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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Task2 {
	
	// Counts the characters of the String parameter
    public static void countCharacters(String str) {
       int len = str.length();
       int count[] = new int[len]; 
       int size = 0;

       for (int i = 0; i < len; i++)
            if (Character.isLetter(str.charAt(i))) {
                count[str.charAt(i)]++;
                size++;
            }
 
        // size of characters found sets the size of this array 
        char characters[] = new char[size];

        for (int i = 0; i < size; i++) {
            if (Character.isLetter(str.charAt(i)))
                characters[i] = str.charAt(i);
                
                int found = 0;
                for (int j = 0; j <= i; j++) {
                    // If any matches found 
                    if (characters[i] == characters[j])  
                    found++; 
                }

                if (found == 1)
                    if (count[str.charAt(i)] != 0) {
                        System.out.println("Number of " + 
                        characters[i] + "'s: " + count[str.charAt(i)]); 
                    }
        }
        
   }

    public static void task2(String FILENAME) {
        String sCurrentLine = "";
        String str = "";
        
        // Reads the file stores each line into str
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            while((sCurrentLine = reader.readLine()) != null) {
                str += (sCurrentLine); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        countCharacters(str); 

    }


}