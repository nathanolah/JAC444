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

import java.util.concurrent.ThreadLocalRandom; // For random number generation

public class CrapsGame {

    static int m_dieOne;
    static int m_dieTwo;
    static int m_point;

    // Generate random number between 1 - 6
    static int rollDice() {
        return (int)ThreadLocalRandom.current().nextInt(1, 7); // Returns number 1 - 6
    }

    // Starts Craps game
    static void startTask2() {
       
        // Roll dice
        m_dieOne = rollDice();
        m_dieTwo = rollDice();

        int sum = m_dieOne + m_dieTwo;

        System.out.println("You rolled " + m_dieOne + " + " + m_dieTwo + " = " + sum);

        if (sum == 2 || sum == 3 || sum == 12) { // Rolled Craps
            System.out.println("Craps, Better Luck Next Time, You Lose");
        } 
        else if (sum == 7 || sum == 11) { // Rolled Naturals
            System.out.println("Congratulations, You win");
        } 
        else {
            boolean done = false;

            // Establish the point
            m_point = sum;
            System.out.println("Point is (established) set to " + m_point);

            do {
                // Roll dice 
                m_dieOne = rollDice();
                m_dieTwo = rollDice();
                sum = m_dieOne + m_dieTwo;
                System.out.println("You rolled " + m_dieOne + " + " + m_dieTwo + " = " + sum);

                if (sum == 7) {
                    System.out.println("Craps, Better Luck Next Time, You lose");
                    done = true;
                }

                if (sum == m_point) {
                    System.out.println("Congratulations, You Win");
                    done = true;
                }

            } while (!done);

        } 
    }
	    
}
