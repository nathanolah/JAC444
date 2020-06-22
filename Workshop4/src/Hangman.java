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
 
import java.util.Random;
import java.util.Arrays;
import java.util.Scanner; 
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Hangman {
    static Scanner input = new Scanner(System.in);
    public static int m_numberOfMisses;
    private static char[] m_asteriskWord;
    private static ArrayList<Character> m_triedLetters = new ArrayList<Character>();


    // Returns random word from the text file
    public static String getRandomWord(String filename) {
        String randomWord = "";

        try {
        	// FileReader reads character based data from the file
        	// BufferReader reads the text from the character based input stream
            BufferedReader reader = new BufferedReader(new FileReader(filename)); 
            String line = reader.readLine();
            List<String> words = new ArrayList<String>();
            
            while (line != null) {
                String[] wordsLine = line.split(" ");
                for (String word : wordsLine)
                    words.add(word);
                line = reader.readLine(); // reads data line by line
            }
            
            // Returns random word
            Random rand = new Random(System.currentTimeMillis());
            randomWord = words.get(rand.nextInt(words.size()));
    
        } catch (IOException e) {
            e.printStackTrace();
        }

        return randomWord;
    }

    // User saves new word to text file
    public static void addToMemory(String filename) {
        System.out.print("Enter a new word to be added in the memory> ");
        String newWord = input.next();

        // Append the word to the file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true)); // true for append mode
            writer.write(newWord + " "); // writes new word to the file 
            writer.close(); // closes file
            System.out.println(newWord + " is saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Resets values to empty
    public static void setEmpty() {
        m_triedLetters.clear();
        m_numberOfMisses = 0;
    }

    // Converts the chosen word to an asterisk version
    public static void convertToAsterisks(String word) {
        m_asteriskWord = word.toCharArray();
        Arrays.fill(m_asteriskWord, '*');
    }

    // Checks for used or not valid letter choice
    public static boolean validateChoice(String letter) {
        boolean isValid = false;
        boolean isUsed = false;
        
        // checks for 1 character, and is a valid character
        if (letter.length() == 1 && Character.isLetter(letter.charAt(0)))
            isValid = true;

        // checks if there is letters that were tried, and if any of the tried letters matches the given letter 
        if (m_triedLetters.size() > 0 && m_triedLetters.indexOf(letter.charAt(0)) != -1)
            isUsed = true;

        if (!isValid)
            System.out.println("The letter you've entered contains more than 1 letter or is not a letter");
        else if (isUsed) 
            System.out.println("You've already tried " + letter.charAt(0) + ", try a new letter");

        return !isUsed && isValid;
    }

    // Validates letter guess then adds to tried letters and returns the letter
    public static char getLetterGuess() {
        boolean valid = false;
        String letter = "";
        do {
            System.out.print("(Guess) Enter a letter in word " + new String(m_asteriskWord) + " > ");
            letter = input.next();

            // Checks for used or not valid letter choice
            valid = validateChoice(letter);
        } while (!valid);

        // add letter to triedLetters
        m_triedLetters.add(letter.charAt(0));

        return letter.charAt(0);
    }

    // Checks for word if any characters matches the letter
    public static boolean checkForMatch(char letter, String word) {
        boolean found = false;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                m_asteriskWord[i] = letter; // change from asterisk to the matched letter
                found = true;
            }
        }

        return found;
    
    }

    public static boolean checkWord() {
        boolean done = false;
        if (new String(m_asteriskWord).indexOf("*") == -1) {
            System.out.println("The word is " + new String(m_asteriskWord) + ". You missed " + 
                m_numberOfMisses + ((m_numberOfMisses > 1 || m_numberOfMisses == 0) ? " times" : " time"));
            done = true;
        }

        return done;
    }
    
    public static void play(String word) {
        boolean done = false;
        m_numberOfMisses = 0;

        convertToAsterisks(word);

        do {
            // Get letter guess
            char letter = getLetterGuess();
            boolean match = checkForMatch(letter, word);

            if (!match) {
                System.out.println(letter + " is not in the word");
                m_numberOfMisses++;
            }

            done = checkWord();

        } while (!done);

    }

    public static boolean validYesNo() {
        boolean restart = true;
        boolean valid = false;
        String letter = "";

        do {
            System.out.print("Do you want to guess another word? Enter Y or N> ");
            letter = input.next();

            // check letter
            boolean isLetter = letter.length() == 1 && Character.isLetter(letter.charAt(0));

            // check for either y or n
            boolean isYesOrNo = Character.toLowerCase(letter.charAt(0)) == 'y' || Character.toLowerCase(letter.charAt(0)) == 'n';

            if (!isLetter || !isYesOrNo) {
                System.out.println("Please enter Y or N to continue");
            } else {
                valid = true;
            }

        } while (!valid);

        if (Character.toLowerCase(letter.charAt(0)) == 'n')
            restart = false;

        return restart;
    }

    public static void startHangman(String filename) {
        boolean restart = true;
    
        while(restart) {
            setEmpty();
            String word = getRandomWord(filename);
            System.out.println("***** " + word.length() + " letter word *****");

            play(word);
            addToMemory(filename);

            restart = validYesNo();                
        }
        System.out.println("Thank you for playing Hangman.");
    }

}
