/***********************************************
Workshop #3 Course:JAC444 - Semester 4
Last Name: Olah
First Name: Nathan
ID: 124723198
Section: NBB
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature
Date: 2020-06-15
**********************************************/

public class CommissionEmployee extends Employee {
    private double m_grossSales;
    private double m_commissionRate;

    // Constructor
    public CommissionEmployee (String firstName, String lastName, String ssn, double sales, double rate) {
        super(firstName, lastName, ssn);
        setGrossSales(sales);
        setCommissionRate(rate);
    }

    // Getter Methods
    public double getGrossSales() {
        return m_grossSales;
    }

    public double getCommissionRate() {
        return m_commissionRate;
    }

    // Setter Methods
    public void setGrossSales(double sales) {
        this.m_grossSales = validateSales(sales);
    }

    public void setCommissionRate(double rate) {
        this.m_commissionRate = validateRate(rate);
    }

    // Validate Sales
    public double validateSales(double sales) {
        if (sales <= 0.0)
            throw new ArithmeticException("Sales must be greater than 0.0");
        else
            return sales;
    }

    // Validate Rate
    public double validateRate(double rate) {
        if (rate < 0.0 || rate > 1.0) 
            throw new ArithmeticException("Rate out of range, rate must be between 0.0 - 1.0"); 
        else
            return rate;
    }

    @Override
    public String toString() {
        return super.toString() + "Gross Sales: $" + getGrossSales() + "\n" + "Commission Rate: " + getCommissionRate();
    }
    
    public double getPaymentAmount() {
        return (getGrossSales() * getCommissionRate());
    }

}