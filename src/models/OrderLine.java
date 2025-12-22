/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author 30513214
 */
public class OrderLine {
    
    private int orderLineId;
    private Product product;
    private int quantity;
    private double lineTotal;
    
    public int getOrderLineId()
    {
        return orderLineId;
    }
    
    public void setOrderLineId(int orderLineIdIn)
    {
        orderLineId = orderLineIdIn;
    }
    
    //constructors
    //4 parameters(everything)
    public OrderLine(int orderLineIdIn, Product productIn, int quantityIn, double lineTotalIn)
    {
        orderLineId = orderLineIdIn;
        product = productIn;
        quantity = quantityIn;
            lineTotal = lineTotalIn;
    }
    
    //3 parameters(everything except lineTotal)
    public OrderLine(int orderLineIdIn, Product productIn, int quantityIn)
    {
        orderLineId = orderLineIdIn;
        product = productIn;
        quantity = quantityIn;
        lineTotal = productIn.getprice() * quantityIn;
    }
    
    public OrderLine(Product productIn, int quantityIn)
    {
        orderLineId = 0;
        product = productIn;
        quantity = quantityIn;
        lineTotal = quantity * product.getprice();
    }
    
    
    //getter methods
    public int getorderLineId()
    {
        return orderLineId;
    }
    
    public Product getproduct()
    {
        return product;
    }
    
    public int getquantity()
    {
        return quantity;
    }
    
    public double getlineTotal()
    {
        return lineTotal;
    }
    
    
    //setter methods
    public void setorderLineid(int orderLineIdIn)
    {
        orderLineId = orderLineIdIn;
    }
    
    public void setproduct(Product productIn)
    {
        product = productIn;
    }
    
    public void setquantity(int quantityIn)
    {
        quantity = quantityIn;
    }
    
    public void setlineTotal(double lineTotalIn)
    {
        lineTotal = lineTotalIn;
    }
}
