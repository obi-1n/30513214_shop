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
    
    public void calculateOrderTotal()
    {
        orderTotal = 0;
        for(Map.Entry<Integer, OrderLine> olMapEntry : orderLines.entrySet())
        {
            OrderLine actualOrderLine = olMapEntry.getValue();
            orderTotal = orderTotal + actualOrderLine.getlineTotal();
        }
    }
    
    public void addOrderLine(OrderLine ol)
    {
        int orderLineId = 0;
        if(orderLines.containsKey(orderLineId))
        {
            orderLineId++;            
        }
        ol.setorderLineid(orderLineId);
        orderLines.put(ol.getOrderLineId(), ol);
        
        calculateOrderTotal();
    }
    
    public void removeOrderLine (int productId)
    {
        Iterator<Map.Entry<Integer, OrderLine>> iter = orderLines.entrySet().iterator();
        
       while(iter.hasNext())
       {
           Map.Entry<Integer, OrderLine> olEntry = iter.next();
           OrderLine actualOrderLine = olEntry.getValue();
           
           if(actualOrderLine.getproduct().getproductId() == productId)
           {
              iter.remove();
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
