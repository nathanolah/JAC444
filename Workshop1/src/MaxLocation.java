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

public class MaxLocation {

    // Private data members, only an instance of MaxLocation can access these members
    private int m_row;
    private int m_column;

    private double m_maxValue = 0;
    private int m_maxValueRow;
    private int m_maxValueColumn;

    // Getter methods
    int getMaxValueRow() {
        return m_maxValueRow;
    }

    int getMaxValueCol() {
        return m_maxValueColumn;
    }

    // Get max value and location
    double getMaxLocation(double[][] matrix) {
        for (int i = 0; i < m_row; i++) {
            for (int j = 0; j < m_column; j++) {
                if (m_maxValue < matrix[i][j]) {
                    m_maxValue = matrix[i][j];
                    m_maxValueRow = i;
                    m_maxValueColumn = j;
                }
            }
        }

        return m_maxValue;
    }

    // Set the values of the matrix
    void setMatrix(double[][] matrix) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the array:");
        for (int i = 0; i < m_row; i++) {
            for (int j = 0; j < m_column; j++) {
                matrix[i][j] = input.nextDouble();
            }
        }

        input.close();
    }

    static void startTask1() {
        Scanner input = new Scanner(System.in);
        MaxLocation obj = new MaxLocation();

        System.out.print("Enter the number of rows and columns in the array: ");

        // Get the rows and columns
        obj.m_row = input.nextInt();
        obj.m_column = input.nextInt();
        System.out.println();

        // Initialize the size and set the values of the matrix
        double matrix[][] = new double[obj.m_row][obj.m_column];
        obj.setMatrix(matrix);

        // Get max value and location of value
        System.out.println("The location of the largest element is " + obj.getMaxLocation(matrix) + " at ("
                + obj.getMaxValueRow() + ", " + obj.getMaxValueCol() + ")");

        input.close();
    }
    
}
