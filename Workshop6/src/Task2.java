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

import java.util.Scanner;

public class Task2 implements ArrayProcessor {
	public static Scanner input = new Scanner(System.in);
	
	public static int size = 0;
	public static double arr[] = {};
	
	public static void populateArray() {
		System.out.print("How many values would you like to enter: ");
		size = input.nextInt();
		
		double newArray[] = new double[size];
		
		for (int i = 0; i < size; i++) {
			System.out.print("Enter the value of element " + (i + 1) + ": ");
			newArray[i] = input.nextDouble();
		}
		
		arr = newArray;
	}
	
	public static void start() {
		populateArray();			
		System.out.println("Sum: " + sum.apply(arr));
		System.out.println("Maximum value: " + maximum.apply(arr));
		System.out.println("Minimum value: " + minimum.apply(arr));
		System.out.println("Average: " + average.apply(arr));
		
		System.out.println();
		System.out.print("Enter a value to check how many times it occurs in the array: ");
		double value = input.nextDouble();
		System.out.println(value + " occurs " + (int)counter(value).apply(arr) + " time" + (((int)counter(value).apply(arr) > 1) ? "s" : " "));
		System.out.println();
	}

	// Get the average
	public static final ArrayProcessor average = (double x[]) -> {
		double sum = 0;
		for (double i : x) sum += i;
		return sum / (double)x.length;
	};
	
	// Find minimum value
	public static final ArrayProcessor minimum = (double x[]) -> {
		double min = Double.MAX_VALUE;
		for (double i : x) min = (i < min) ? i : min;
		return min;
	};
	
	// Find maximum value
	public static final ArrayProcessor maximum = (double x[]) -> {
		double max = Double.MIN_VALUE;
		for (double i : x) max = (i > max) ? i : max;
		return max;
	};
	
	// Get the Sum
	public static final ArrayProcessor sum = (double x[]) -> {
		double total = 0;
		for (double i : x) total += i;
		return total;
	};
	
	// counts the amount of times value occurs
	public static ArrayProcessor counter(double value) {
		return (arr) -> {
			double count = 0;
			for (double i : arr) 
				if (i == value)
					count++;
			
			return count;
		};
		
	}

	@Override
	public double apply(double[] array) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
