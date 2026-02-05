/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author 30513214
 */
public class Order {
    private int orderId;
    private Date orderDate;
    private double orderTotal;
    private String status;
    
    //check item is already in order
    public boolean isInBasket(int productId)
    {
        //start by assuming item isn't in the basket
        boolean isAlreadyInBasket = false;
        
        //go through every (key, value) pair in the orderLines map
        for (Map.Entry<Integer, OrderLine> olMapEntry : orderLines.entrySet())
        {
            //Get the orderLine object from the map entry
            OrderLine actualOrderLine = olMapEntry.getValue();
            
            //Get Product stored inside the orderLine
            Product productFromOrderLine = actualOrderLine.getproduct();
            
            //Compare the ID we're searching for with this product's ID
            if(productId == productFromOrderLine.getproductId())
            {
                //if they match, mark that we've found it
                isAlreadyInBasket = true;
            }
        }
        
        //After checking everything, return whether we found a match
        return isAlreadyInBasket;
    }
    
    //calculates the order total
    public void calculateOrderTotal()
    {
        // Reset the order total to 0
        orderTotal = 0;
        // Iterate through each entry in the orderLines HashMap
        for(Map.Entry<Integer, OrderLine> olMapEntry : orderLines.entrySet())
        {
            // Get the OrderLine object from the current HashMap entry
            OrderLine actualOrderLine = olMapEntry.getValue();
            // Add the line total of the current OrderLine to the order total
            orderTotal = orderTotal + actualOrderLine.getlineTotal();
        }
    }
    
    //adds orderline to order
    public void addOrderLine(OrderLine ol)
    {
        // Initialize orderLineId to 0
        int orderLineId = 0;
        // Check if the orderLineId already exists as a key in the HashMap
        if(orderLines.containsKey(orderLineId))
        {
            // Increment orderLineId to find the next available id
            orderLineId++;            
        }
        // Set the orderLineId on the OrderLine object
        ol.setorderLineid(orderLineId);
        // Add the OrderLine to the HashMap with orderLineId as the key
        orderLines.put(ol.getOrderLineId(), ol);
        
        // Recalculate the order total to include the new OrderLine
        calculateOrderTotal();
    }
    
    //removes orderline from order
    public void removeOrderLine (int productId)
    {
        // Create an Iterator for the orderLines HashMap entries
        Iterator<Map.Entry<Integer, OrderLine>> iter = orderLines.entrySet().iterator();
        
        // Loop through each entry in the HashMap
        while(iter.hasNext())
        {
            // Get the current HashMap entry
            Map.Entry<Integer, OrderLine> olEntry = iter.next();
            // Get the OrderLine object from the current entry
            OrderLine actualOrderLine = olEntry.getValue();
            
            // Check if the product ID in the OrderLine matches the productId parameter
            if(actualOrderLine.getproduct().getproductId() == productId)
            {
            // Remove the current OrderLine from the HashMap
            iter.remove();
            // Subtract the line total of the removed OrderLine from the order total
            orderTotal = orderTotal - actualOrderLine.getlineTotal();
            }
        }
    }
    
    //HashMap<K,V>
    //K - Key, Integer because orderLineId is an int
    //V - Value, what it is a collection of
    private HashMap<Integer, OrderLine> orderLines;
    
    //Coonstructors
    //0 parameters
    public Order()
    {
        orderId = 0;
        orderDate = new Date();
        orderTotal = 0;
        status = "Ongoing";
        orderLines = new HashMap();
    }
    
    //4 parameters (all except orderLines)
    public Order(int orderIdIn, Date orderDateIn, double orderTotalIn, String statusIn)
    {
        orderId = orderIdIn;
        orderDate = orderDateIn;
        orderTotal = orderTotalIn;
        status = statusIn;
        orderLines = new HashMap();
    }
    
    //getter methods
    public int getorderId()
    {
        return orderId;
    }
    
    public Date getorderDate()
    {
        return orderDate;
    }
    
    public double getorderTotal()
    {
        return orderTotal;
    }
    
    public String getstatus()
    {
        return status;
    }
    
    public HashMap<Integer, OrderLine> getOrderLines()
    {
        return orderLines;
    }
    
    //setter methods
    public void setorderId(int orderIdIn)
    {
        orderId = orderIdIn;
    }
    
    public void setorderDate(Date orderDateIn)
    {
        orderDate = orderDateIn;
    }
    
    public void setorderTotal(double orderTotalIn)
    {
        orderTotal = orderTotalIn;
    }
    
    public void setstatus(String statusIn)
    {
        status = statusIn;
    }
    
    public void setOrderLines(HashMap<Integer, OrderLine> olIn)
    {
        orderLines = olIn;
    }
    
    public void setOrderId(int orderIdIn)
    {
        orderId = orderIdIn;
    }
    
    public void setStatus(String statusIn)
    {
        status = statusIn;
    }
    
    
}
