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

import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Scanner;

public class IncomeTax {

    // Filing statuses
    public static final int SINGLE_FILER = 0;
    public static final int MARRIED_JOINTLY_OR_QUALIFYING_WIDOW = 1;
    public static final int MARRIED_SEPARATELY = 2;
    public static final int HEAD_OF_HOUSEHOLD = 3; 

    // Intervals for 2001
    private static int[][] singleInterval2001 = { {0, 27050}, {27051, 65550}, {65551, 136750}, {136751, 297350}, {297351, Integer.MAX_VALUE} };
    private static int[][] marriedJointlyInterval2001 = { {0, 45200}, {45201, 109250}, {109251, 166500}, {166501, 297350}, {297351, Integer.MAX_VALUE} };
    private static int[][] marriedSeparatelyInterval2001 = { {0, 22600}, {22601, 54625}, {54626, 83250}, {83251, 148675}, {148676, Integer.MAX_VALUE} };
    private static int[][] headOfHouseholdInterval2001 = { {0, 36250}, {36251, 93650}, {93651, 151650}, {151651, 297350}, {297351, Integer.MAX_VALUE} };

    // Intervals for 2009
    private static int[][] singleInterval2009 = { {0, 8350}, {8351, 33950}, {33951, 82250}, {82251, 171550}, {171551, 372950}, {372951, Integer.MAX_VALUE} };
    private static int[][] marriedJointlyInterval2009 = { {0, 16700}, {16701, 67900}, {67901, 137050}, {137051, 208850}, {208851, 372950}, {372951, Integer.MAX_VALUE} };
    private static int[][] marriedSeparatelyInterval2009 = { {0, 8350}, {8351, 33950}, {33951, 68525}, {68526, 104425}, {104426, 186475}, {186476, Integer.MAX_VALUE} };
    private static int[][] headOfHouseholdInterval2009 = { {0, 11950}, {11951, 45500}, {45501, 117450}, {117451, 190200}, {190201, 372950}, {372951, Integer.MAX_VALUE} };
    
    // Rates from 2009
    static double rates2009[] = { 10, 15, 25, 28, 33, 35 };

    // Rates from 2001
    static double rates2001[] = { 15, 27.5, 30.5, 35.5, 39.1 };

    private int filingStatus;
    private double taxableIncome;
    private int[][] m_intervals;
    private double[] m_rates;

    // Default Constructor
    public IncomeTax() {
        this.filingStatus = 0;
        this.taxableIncome = 0;
        this.m_intervals = new int[0][0];
        this.m_rates = new double[0];
    }

    // 4-Argument Constructor
    public IncomeTax(int status, int[][] intervals, double[] rates, int taxableIncome) {
        setFilingStatus(status);
        setTaxableIncome(taxableIncome);
        setRates(rates);
        setIntervals(intervals);
    } 

    // Setter Methods
    public void setRates(double[] rates) {
        this.m_rates = rates;
    }

    public void setIntervals(int[][] intervals) {
        this.m_intervals = intervals.clone();
    }

    public void setFilingStatus(int status) {
        this.filingStatus = status;
    }

    public void setTaxableIncome(double taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    // Getter Methods
    public int getFilingStatus() {
        return this.filingStatus;
    }

    public double getTaxableIncome() {
        return this.taxableIncome;
    }

    public double[] getRates() {
        return this.m_rates;
    }

    // Get the rate of the income
    public int countIntervals() {
        int count = 0;
        int leftValue, rightValue; // to store the start and end of the interval

        for (int i = 0; i < this.m_intervals.length; i++) {
            leftValue = this.m_intervals[i][0]; // lower value
            rightValue = this.m_intervals[i][1]; // higher value

            if (getTaxableIncome() >= leftValue && getTaxableIncome() <= rightValue)
                break;
            count++;
        }

        return count; // returns the index of interval to match the corresponding rate
    }

    public double getIncomeTax(int ratesIdx) {
        double totalTax = 0.0;
        double calcTax = 0; 

        for (int i = 0; i <= ratesIdx; i++) {
            if (getTaxableIncome() > this.m_intervals[i][1])
                calcTax = this.m_intervals[i][1];
            else
                calcTax = getTaxableIncome();
            totalTax += ((double) calcTax - (double) this.m_intervals[i][0]) * (this.m_rates[i] / 100); 
        }
        return totalTax; 
    }
    
    // Validates the status input
    public static int validateStatus() {
        Scanner input = new Scanner(System.in);
        boolean ok = false;
        String errMessage = "Please enter a number status number between 0 and 3";
        int status = 0;

        do {
            try {
                status = input.nextInt();
                if (status > 3 || status < 0)
                    System.out.println(errMessage);
                else 
                    ok = true;
            }
            catch (InputMismatchException e) {
                input.nextLine(); // Clears buffer
                System.out.println(errMessage);
            }
        } while (!ok);

        return status;
    }

    // User can only input year 2001 or 2009
    public static int validateYear() {
        int year = 0;
        Scanner input = new Scanner(System.in);
        boolean ok = false;
        String errMessage = "Please enter either '2001' or '2009'";

        do {
            try {
                year = input.nextInt();
                if (year == 2001 || year == 2009)
                    ok = true;
                else 
                    System.out.println(errMessage);
            }
            catch (InputMismatchException e) {
                input.nextLine(); // Clears buffer
                System.out.println(errMessage);
            }
        } while (!ok);

        return year;
    }

    // Selects the correct intervals
    public static int[][] findInterval(int year, int status) {
        int interval[][] =  {{}};

        if (year == 2009) {
            if (status == SINGLE_FILER)
                interval = singleInterval2009.clone(); // Copy over the 2d array
            else if (status == MARRIED_JOINTLY_OR_QUALIFYING_WIDOW)
                interval = marriedJointlyInterval2009.clone();
            else if (status == MARRIED_SEPARATELY)
                interval = marriedSeparatelyInterval2009.clone();
            else if (status == HEAD_OF_HOUSEHOLD)
                interval = headOfHouseholdInterval2009.clone();  
        }

        if (year == 2001) {
            if (status == SINGLE_FILER)
                interval = singleInterval2001.clone(); // Copy over the 2d array
            else if (status == MARRIED_JOINTLY_OR_QUALIFYING_WIDOW)
                interval = marriedJointlyInterval2001.clone();
            else if (status == MARRIED_SEPARATELY)
                interval = marriedSeparatelyInterval2001.clone();
            else if (status == HEAD_OF_HOUSEHOLD)
                interval = headOfHouseholdInterval2001.clone();  
        }
        
        return interval;
    }

    // Validate value from user input
    public static int validateValue() {
        Scanner input = new Scanner(System.in);
        int value = 0;
        boolean ok = false;
        String errMessage = "Please enter a valid value ";

        do {
            try {
                value = input.nextInt();
                if (value < 0)
                    System.out.println(errMessage);
                else 
                    ok = true;
            }
            catch (InputMismatchException e) {
                input.nextLine(); // Clears buffer
                System.out.println(errMessage);
            }
        } while (!ok);

        return value;
    }

    // Selects the correct rates depending on the year
    public static double[] getRates(int year) {
        double rates[] = {};
        if (year == 2001)
            rates = rates2001.clone(); // Copies the array
        else if (year == 2009)
            rates = rates2009.clone();

        return rates;
    }
    
    /** CHOICE 1 **/
    static void startChoice1() {
        int status;
        int year;

        System.out.print("Please input either '2001' or '2009' to select tax rates: ");
        year = validateYear();
        System.out.println();

        // Select Rates
        double[] rates = getRates(year);

        System.out.println("0 - single filer");
        System.out.println("1 - married jointly or qualifying widow(er)");
        System.out.println("2 - married separately");
        System.out.println("3 - head of household");
        System.out.println();
        System.out.print("Enter the filing status: ");
        status = validateStatus();

        // Finds intervals based on year and status
        int[][] intervals = findInterval(year, status); 

        System.out.println();
        System.out.print("Enter the Taxable Income: $");
        int incomeBeforeTax = validateValue();

        // Creates new instance 
        IncomeTax user = new IncomeTax(status, intervals, rates, incomeBeforeTax); 

        // index of the rate needed based in the taxableIncome
        int ratesIndex = user.countIntervals();
        double taxedIncome = user.getIncomeTax(ratesIndex);

        System.out.println();
        System.out.println("Tax is: $" + String.format("%.2f", taxedIncome));
        System.out.println();

    }

    // Prints the taxable incomes from start to end value
    public static void printIncomes(int year, int startValue, int endValue, ArrayList<Double> single,
    		ArrayList<Double> marriedJointly, ArrayList<Double> marriedSeparately, ArrayList<Double> headOfHousehold) {

        int tIncome = startValue;
        System.out.println(year + " tax tables for taxable income from $" + startValue + " to $" + endValue);
        System.out.println();
        System.out.println("----------------------------------------------------------\n");
        System.out.println("Taxable   Single   Married Joint   Married    Head of\n");
        System.out.println("Income             or Qualifying   Separate   a House\n");
        System.out.println("                      Widow(er)\n");
        System.out.println("----------------------------------------------------------\n");

        for (int i = 0, j = startValue; j <= endValue; j += 1000, i++) {
            System.out.println(tIncome + "    " +
                String.format("%.2f", single.get(i)) + "    " +
                String.format("%.2f", marriedJointly.get(i)) + "    " + 
                String.format("%.2f", marriedSeparately.get(i)) + "    " + 
                String.format("%.2f", headOfHousehold.get(i)));
            
            tIncome += 1000;
            System.out.println();
        }
    }
    
    /** CHOICE 2 **/
    static void startChoice2() {
        // Stores taxed incomes
        ArrayList<Double> single = new ArrayList<Double>();
        ArrayList<Double> marriedJointly = new ArrayList<Double>();
        ArrayList<Double> marriedSeparately = new ArrayList<Double>();
        ArrayList<Double> headOfHousehold = new ArrayList<Double>();

        System.out.print("Enter the amount From: $");
        int startValue = validateValue();
        System.out.println();

        System.out.print("Enter the amount To: $");
        int endValue = validateValue();
        
        while(endValue <= startValue) {
            System.out.println("Please enter an amount greater than " + startValue);
            endValue = validateValue();
        }
        System.out.println();

        double rates[] = getRates(2001);
        
        // Create user instance
        IncomeTax user = new IncomeTax();
        user.setTaxableIncome(startValue);
        user.setRates(rates);

        // Rates from 2001
        for (int i = 0; i < 4; i++) {
            // Sets new intervals for each iteration
            int[][] intervals = findInterval(2001, i);
            user.setIntervals(intervals);
            
            // Populate taxed incomes for all statuses
            for (int value = startValue; value <= endValue; value += 1000) {
                user.setTaxableIncome(value); // Adds 1000 and sets the new value
                int rateIndex = user.countIntervals();

                if (i == SINGLE_FILER)
                    single.add(user.getIncomeTax(rateIndex));
                else if (i == MARRIED_JOINTLY_OR_QUALIFYING_WIDOW) 
                    marriedJointly.add(user.getIncomeTax(rateIndex));
                else if (i == MARRIED_SEPARATELY)
                    marriedSeparately.add(user.getIncomeTax(rateIndex));
                else if (i == HEAD_OF_HOUSEHOLD)
                    headOfHousehold.add(user.getIncomeTax(rateIndex));
            }
        }

        // Prints incomes from 2001
        printIncomes(2001, startValue, endValue, single, marriedJointly, marriedSeparately, headOfHousehold);

        // Remove elements from arrays
        single.clear();
        marriedJointly.clear();
        marriedSeparately.clear();
        headOfHousehold.clear();
        System.out.println();

        // Sets rates from 2009
        user.setRates(getRates(2009));

        for (int i = 0; i < 4; i++) {
            // Sets new intervals for each iteration
            int[][] intervals = findInterval(2009, i);
            user.setIntervals(intervals);

            for (int value = startValue; value <= endValue; value += 1000) {

                user.setTaxableIncome(value); // Adds 1000 and sets the new value
                int rateIndex = user.countIntervals();

                if (i == SINGLE_FILER)
                    single.add(user.getIncomeTax(rateIndex));
                else if (i == MARRIED_JOINTLY_OR_QUALIFYING_WIDOW) 
                    marriedJointly.add(user.getIncomeTax(rateIndex));
                else if (i == MARRIED_SEPARATELY)
                    marriedSeparately.add(user.getIncomeTax(rateIndex));
                else if (i == HEAD_OF_HOUSEHOLD)
                    headOfHousehold.add(user.getIncomeTax(rateIndex));
            }
        }

        System.out.println();
        
        // Prints incomes from 2009
        printIncomes(2009, startValue, endValue, single, marriedJointly, marriedSeparately, headOfHousehold);

    }
	
}
