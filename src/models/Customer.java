/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.HashMap;

/**
 *
 * @author 30513214
 */
public class Customer extends User{
    
    //Attributes
    private String addressLine1;
    private String addressLine2;
    private String town;
    private String postcode;
    private boolean isRegistered;
    
    //HashMap<K,V>
    //K - Key, Integer because orderLineId is an int
    //V - Value, what it is a collection of
    private HashMap<Integer, Order> Orders;
    
    //constructors
    //0-input parameter
    public Customer()
    {
        super();
        //call User()
        addressLine1 = "empty";
        addressLine2 = "empty";
        town = "empty";
        postcode ="empty";
        isRegistered = false;
        Orders= new HashMap();   
    }
    
    //8-input parameter (everything EXCEPT isRegistered)
    public Customer(String usernameIn, String passwordIn, String firstNameIn, String lastNameIn, String addressLine1In, String addressLine2In, String townIn, String postcodeIn)
    {
        super(usernameIn, passwordIn, firstNameIn, lastNameIn);
        //call User(String usernameIn, String passwordIn, String firstNameIn, String lastNameIn)
        addressLine1 = addressLine1In;
        addressLine2 = addressLine2In;
        town = townIn;
        postcode = postcodeIn;  
        isRegistered = false;
        Orders= new HashMap(); 
    }
    
    //getter methods
    public String getaddressLine1()
    {
        return addressLine1;
    }
    
    public String getaddressLine2()
    {
        return addressLine2;
    }
    
    public String gettown()
    {
        return town;
    }
    
    public String getpostcode()
    {
        return postcode;
    }
 
    
    public boolean getisRegistered()
    {
        return isRegistered;
    }
    
    public HashMap<Integer, Order> getOrders()
    {
        return Orders;
    }
    
    //setter methods
    public void setaddressLine1(String addressLine1In)
    {
       addressLine1 = addressLine1In;
    }
    
    public void setaddressLine2(String addressLine2In)
    {
       addressLine2 = addressLine2In;
    }
    
    public void settown(String townIn)
    {
       town = townIn;
    }
    
    public void setpostcode(String postcodeIn)
    {
       postcode = postcodeIn;
    }
    
    public void setisRegistered(boolean isRegisteredIn)
    {
       isRegistered = isRegisteredIn;
    }
    
    public void setOrders(HashMap<Integer, Order> OrdersIn)
    {
        Orders = OrdersIn;
    }
    
    public String greeting()
    {
        String greeting ="<html>Welcome" + " " + this.getfirstName() + " " + this.getlastName() + "<br>Enjoy Shopping!</html>";
        return greeting;
    }
    
}
