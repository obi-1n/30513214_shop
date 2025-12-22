/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author 30513214
 */
public class Footwear extends Product{
    
    private int size;
    
    //constructors
    //0-input parameter
    public Footwear()
    {
        super();
        //call Product()
        size = 00;
    }
    
    //4-input parameter (everything EXCEPT productId - 4 parameters)
    public Footwear(String productNameIn, double priceIn, int stockLevelIn, int sizeIn)
    {
        super(productNameIn, priceIn, stockLevelIn);
        //call Product(int productIdIn, String productNameIn, double priceIn, int stockLevelIn)
        size = sizeIn;
    }
    
    //5-input parameter (everything)
    public Footwear(int productIdIn, String productNameIn, double priceIn, int stockLevelIn, int sizeIn)
    {
        super(productIdIn, productNameIn, priceIn, stockLevelIn);
        //call Product(int productIdIn, String productNameIn, double priceIn, int stockLevelIn)
        size = sizeIn;
    }
    
    //getter method
    public int getsize()
    {
        return size;
    }
    
    //setter method
    public void setsize(int sizeIn)
    {
       size = sizeIn;
    }
    
}
