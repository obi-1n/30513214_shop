/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author 30513214
 */
public class Clothing extends Product{
    
    private String measurement;
    
    //constructors
    //0-input parameter
    public Clothing()
    {
        super();
        //call Product()
        measurement = "empty";
    }
    
    //4-input parameter (everything EXCEPT productId - 4 parameters)
    public Clothing(String productNameIn, double priceIn, int stockLevelIn, String measurementIn)
    {
        super(productNameIn, priceIn, stockLevelIn);
        //call Product(String productNameIn, double priceIn, int stockLevelIn)
        measurement = measurementIn;
    }
    
    //5-input parameter (everything)
    public Clothing(int productIdIn, String productNameIn, double priceIn, int stockLevelIn, String measurementIn)
    {
        super(productIdIn, productNameIn, priceIn, stockLevelIn);
        //call Product(int productIdIn, String productNameIn, double priceIn, int stockLevelIn)
        measurement = measurementIn;
    }
    
    //getter method
    public String getmeasurement()
    {
        return measurement;
    }
    
    //setter method
    public void setmeasurement(String measurementIn)
    {
       measurement = measurementIn;
    }
    
}
