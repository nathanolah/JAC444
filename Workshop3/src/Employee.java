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

public abstract class Employee implements Payable { // this is an abstract class because it doesn't implement getPayableAmount()
    private String m_firstName;
    private String m_lastName;
    private String m_socialSecurityNumber;

    // Constructor
    public Employee(String firstName, String lastName, String ssn) {
        setFirstName(firstName);
        setLastName(lastName);
        setSocialSecurityNumber(ssn);
    }

    // Getter Methods
    public String getFirstName() {
        return m_firstName;
    }

    public String getLastName() {
        return m_lastName;
    }

    public String getSocialSecurityNumber() {
        return m_socialSecurityNumber;
    }

    // Setter Methods
    public void setFirstName(String firstName) {
        this.m_firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.m_lastName = lastName;
    }

    public void setSocialSecurityNumber(String ssn) {
        this.m_socialSecurityNumber = ssn;
    }

    public String toString() {
        return getFirstName() + " " +  getLastName() + "\nSocial Security Number: " + getSocialSecurityNumber() + "\n";
    }

}