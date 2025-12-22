/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author 30513214
 */
public class Staff extends User{
    
    private String position;
    private double salary;
    
    //constructors
    //0-input parameter
    public Staff()
    {
        super();
        //call User()
        position = "empty";
        salary = 00;
    }
    
    //6-input parameter (everything)
    public Staff(String usernameIn, String passwordIn, String firstNameIn, String lastNameIn, String positionIn, double salaryIn)
    {
        super(usernameIn, passwordIn, firstNameIn, lastNameIn);
        //call User(String usernameIn, String passwordIn, String firstNameIn, String lastNameIn)
        position = positionIn;
        salary = salaryIn;
    }
    
    //getter methods
    public String getposition()
    {
        return position;
    }
    
    public double getsalary()
    {
        return salary;
    }
    
    
    
    //setter methods
    public void setposition(String positionIn)
    {
       position = positionIn;
    }
    
    public void setaddressLine2(double salaryIn)
    {
       salary = salaryIn;
    }
    
    //Display Greeting
    
    public String greeting()
    {
        String greeting ="<html>Welcome" + " " + this.getfirstName() + " " + this.getlastName() + "<br>You are logged in as Staff</html>";
        return greeting;
    }
    
    
}
