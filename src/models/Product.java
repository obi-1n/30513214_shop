/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author 30513214
 */
public class Product {
    
    public String toString()
    {
        String animalText = productName + " : " + price;
        return animalText;
    }
    
    //Attributes 
    private int productId;
    private String productName;
    private double price;
    private int stockLevel;
    
    //Constructor
    public Product()
    {
        productId = 000;
        productName = "none";
        price = 000;
        stockLevel = 000;        
    }
    
    //Overloaded Constructor
    public Product(String productNameIn, double priceIn, int stockLevelIn)
    {
        productId = 000;
        productName = productNameIn; 
        price = priceIn;
        stockLevel = stockLevelIn;        
    }
    
    //2nd Overloaded Constructor
    public Product(int productIdIn, String productNameIn, double priceIn, int stockLevelIn)
    {
        productId = productIdIn;
        productName = productNameIn;
        price = priceIn;
        stockLevel = stockLevelIn;        
    }
    
    //getter method for productId
    public int getproductId()
    {
       return productId; 
    }
    
    //setter method for productId
    public void setproductId(int productIdIn)
    {
       productId = productIdIn;
    }
    
    //getter method for productName
    public String getproductName()
    {
       return productName; 
    }
    
    //setter method for productName
    public void setproductName(String productNameIn)
    {
       productName = productNameIn;
    }
    
    //getter method for price
    public double getprice()
    {
       return price; 
    }
    
    //setter method for price
    public void setprice(double priceIn)
    {
       price = priceIn;
    }
    
    //getter method for stockLevel
    public int getstockLevel()
    {
       return stockLevel; 
    }
    
    //setter method for stockLevel
    public void setstockLevel(int stockLevelIn)
    {
       stockLevel = stockLevelIn;
    }
    
    
    
}
