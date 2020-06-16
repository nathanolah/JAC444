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

public class Invoice implements Payable {
    private String m_partNumber;
    private String m_partDescription;
    private int m_quantity;
    private double m_pricePerItem;
    
    // Constructor
    public Invoice(String partNumber, String partDesc, int count, double price) {
        setPartNumber(partNumber);
        setPartDescription(partDesc);
        setQuantity(count);
        setPricePerItem(price);
    }

    // Setter Methods
    public void setPartNumber(String partNumber) {
        this.m_partNumber = partNumber;
    }

    public void setPartDescription(String partDesc) {
        this.m_partDescription = partDesc;
    }

    public void setQuantity(int quantity) {
        this.m_quantity = quantity;
    }

    public void setPricePerItem(double pricePerItem) {
        this.m_pricePerItem = pricePerItem;
    }

    // Getter Methods
    public String getPartNumber() {
        return m_partNumber;
    }

    public String getPartDescription() {
        return m_partDescription;
    }

    public int getQuantity() {
        return m_quantity;
    }

    public double getPricePerItem() {
        return m_pricePerItem;
    }

    // toString Method
    public String toString() {
        return "Part Number: " + getPartNumber() + " Description: " + getPartDescription() + " Amount: " + getPaymentAmount() + "\n";
    }

    public double getPaymentAmount() {
        return (getPricePerItem() * getQuantity());
    }
}
