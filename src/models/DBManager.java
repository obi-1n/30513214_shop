/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 *
 * @author 30513214
 */
public class DBManager {
    
    public static void main(String[] args) {
        
        //access database
        //DBManager db = new DBManager();
        //HashMap<String, Customer> customer = db.loadCustomers();
        //HashMap<String, Staff> staff = db.loadStaff();
    }
    
    private final String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
    private final String connectionString = "jdbc:ucanaccess://Data\\AssessmentShopDB.accdb";
    
    public void editDetails(Customer c)
    {
        
        try 
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE Customers SET "
                    + "AddressLine1 = '" + c.getaddressLine1() + "',"
                    + "AddressLine2 = '" + c.getaddressLine2() + "',"
                    + "Town = '" + c.gettown() + "',"
                    + "Postcode = '" + c.getpostcode() + "'"
                    + " WHERE "
                    + "Username = '" + c.getUsername() + "'");
        } 
        catch (Exception ext) 
        {
            System.out.println("Error editing details: " + ext.getMessage());
        }
    }
    
    public void editProduct(Product p) {
        String additionalSQL = "";

        if (p.getClass().getName().equals("models.Clothing")) 
        {
            Clothing c = (Clothing) p;
            additionalSQL = "Measurement = '" + c.getmeasurement() + "' ";

        }
        
        else if (p.getClass().getName().equals("models.Footwear")) 
        {
            Footwear f = (Footwear) p;
            additionalSQL = "Size = '" + f.getsize() + "' ";
        }
        
        try 
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE Products SET "
                    + "ProductName = '" + p.getproductName() + "',"
                    + "Price = '" + p.getprice() + "',"
                    + "StockLevel = '" + p.getstockLevel() + "',"
                    + additionalSQL + " WHERE "
                    + "ProductId = '" + p.getproductId() + "'");
        } 
        catch (Exception ext) 
        {
            System.out.println("Error editing product: " + ext.getMessage());
        }
    }
    
    public void deleteProduct(int productId)
    {
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM Products WHERE ProductId = " + productId);
        }
        catch(Exception ext)
        {
            System.out.println("Error Removing Product:" + ext.getMessage());
        }
    }
    
    public void updateProductStockLevel(int productId, int quantity)
    {
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE Products SET StockLevel = (StockLevel - " + quantity + ") WHERE ProductId = " + productId);
            
        }
        catch(Exception ex)
        {
            System.out.println("Error: Updating StockLevel- " + ex.getMessage());
        }
    }
    
    public int writeOrder(Order o, String customerUsername)
    {
        int orderId = 0;
        
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Orders(OrderDate, Username, OrderTotal, Status)" +
                    "VALUES (" +
                    "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(o.getorderDate()) + "'," +
                    "'" + customerUsername + "'," +
                    "'" + o.getorderTotal() + "'," +
                    "'" + o.getstatus() + "'" +
                    ")");
            
            ResultSet rs = stmt.getGeneratedKeys();
            
            if(rs.next())
            {
                orderId =rs.getInt(1);
                o.setOrderId(orderId);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error writing orders123: " + ex.getMessage());
        }
        finally
        {
            return orderId;
        }
    }
    
    public void writeOrderLine(OrderLine ol, int orderId)
    {
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO OrderLines (OrderLineID, ProductId, Quantity, LineTotal, OrderId)" +
                    " VALUES (" +
                    "'" + ol.getOrderLineId() + "'," +
                    //"'1'," +
                    "'" + ol.getproduct().getproductId() + "'," + 
                    "'" + ol.getquantity() + "'," +        
                    "'" + ol.getlineTotal() + "'," +
                    "'" + orderId + "'" +
                    ")");
        }
        catch(Exception ex)
        {
            System.out.println("Error writing orderlines: " + ex.getMessage());
        }
    }
    
    public HashMap<String, Customer> loadCustomers()
    {
        //create hashmap
        HashMap<String, Customer> loadedCustomers = new HashMap();
        
        try
        {
            //acess database
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Customers");
            
            //populate table
            while(rs.next())
            {
                String username = rs.getString("Username");
                String password =rs.getString("Password");
                String firstName = rs.getString ("FirstName");
                String lastName = rs.getString("LastName");
                String addressLine1 = rs.getString("AddressLine1");
                String addressLine2 = rs.getString("AddressLine2");
                String town = rs.getString("Town");
                String postcode = rs.getString("PostCode");
                
                
                //public Customer(String usernameIn, String passwordIn, String firstNameIn, String lastNameIn, String addressLine1In, String addressLine2In, String townIn, String postcodeIn)
                Customer customer = new Customer(username, password, firstName, lastName, addressLine1, addressLine2, town, postcode);
                
                //.put(KEY, VALUE)
                loadedCustomers.put(username, customer);
            }
        }
        
        catch(Exception  ex)
        {
            
        }
        finally
        {
            //return data
            return loadedCustomers;
        }
        
    }
    
    public HashMap<String, Staff> loadStaff()
    {
        //create hashmap
        HashMap<String, Staff> loadedStaff = new HashMap();
        
        try
        {
            //access database
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Staff");
            
            //populate table
            while(rs.next())
            {
                String username = rs.getString("Username");
                String password =rs.getString("Password");
                String firstName = rs.getString ("FirstName");
                String lastName = rs.getString("LastName");
                String position = rs.getString("Position");
                double salary = rs.getDouble("Salary");
                
                
                //public Staff(String usernameIn, String passwordIn, String firstNameIn, String lastNameIn, String positionIn, double salaryIn)
                Staff staff = new Staff(username, password, firstName, lastName, position, salary);
                
                //.put(KEY, VALUE)
                loadedStaff.put(username, staff);
            }
        }
        
        catch(Exception  ex)
        {
            
        }
        finally
        {
            //return data
            return loadedStaff;
        }
    }
    
    public HashMap<Integer, Product> loadProducts()
    {
        HashMap<Integer, Product> allProducts = new HashMap();
        
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Products");
            
            while(rs.next())
            {
                int productId = rs.getInt("ProductId");
                String productName = rs.getString("ProductName");
                double price = rs.getDouble("Price");
                int stockLevel = rs.getInt("StockLevel");
                String measurement = rs.getString("Measurement");
                int size = rs.getInt("Size");
                
                
                if(size == 0)
                {
                  //Clothing(int productIdIn, String productNameIn, double priceIn, int stockLevelIn, String measurementIn)
                    Clothing c = new Clothing(productId, productName, price, stockLevel, measurement);
                    allProducts.put(productId, c);
                }
                
                else
                {
                    //Footwear(int productIdIn, String productNameIn, double priceIn, int stockLevelIn, int sizeIn)
                    Footwear f = new Footwear(productId, productName, price, stockLevel, size);
                    allProducts.put(productId, f);
                }
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error loading products : " + ex.getMessage());
        }
        finally
        {
            return allProducts;
        }
    }
    
    public Customer customer_login(String username, String password)
    {
        HashMap<String, Customer> allCustomers = loadCustomers();
        
        if(allCustomers.containsKey(username))
        {
            Customer customerWithCorrectUsername = allCustomers.get(username);
            
            if(customerWithCorrectUsername.getpassword().equals(password))
            {
                return customerWithCorrectUsername;
            }
            
            else
            {
                return null;
            }
        }
        
        else
        {
            return null;
        }
    }
    
    public Staff staff_login(String username, String password)
    {
        HashMap<String, Staff> allStaff = loadStaff();
        
        if(allStaff.containsKey(username))
        {
            Staff staffWithCorrectUsername = allStaff.get(username);
            
            if(staffWithCorrectUsername.getpassword().equals(password))
            {
                return staffWithCorrectUsername;
            }
            
            else
            {
                return null;
            }
        }
        
        else
        {
            return null;
        }
    }
    
}
