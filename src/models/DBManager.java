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
    /**
     * The DBManager class is responsible for managing the database connection
     * and operations related to the AssessmentShopDB database.
     */
    private final String connectionString = "jdbc:ucanaccess://Data\\AssessmentShopDB.accdb";
    
    /**
     * Edits the details of a customer in the database.
     * @param c Customer object containing updated customer details
     */
    public void editDetails(Customer c)
    {
        try 
        {
            // Load the database driver
            Class.forName(driver);
            // Establish connection to the database
            Connection conn = DriverManager.getConnection(connectionString);
            
            // Create statement to execute SQL query
            Statement stmt = conn.createStatement();
            // Execute UPDATE query to modify customer details
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
            // Handle any exceptions that occur during the update
            System.out.println("Error editing details: " + ext.getMessage());
        }
    }
    
    /**
     * Edits the details of a product in the database.
     * @param p Product object containing updated product details
     */
    public void editProduct(Product p) {
        String additionalSQL = "";

        // Check if the product is of type Clothing
        if (p.getClass().getName().equals("models.Clothing")) 
        {
            Clothing c = (Clothing) p;
            // Prepare additional SQL for Clothing specific fields
            additionalSQL = "Measurement = '" + c.getmeasurement() + "' ";

        }
        // Check if the product is of type Footwear
        else if (p.getClass().getName().equals("models.Footwear")) 
        {
            Footwear f = (Footwear) p;
            // Prepare additional SQL for Footwear specific fields
            additionalSQL = "Size = '" + f.getsize() + "' ";
        }
        
        try 
        {
            // Load the database driver
            Class.forName(driver);
            // Establish connection to the database
            Connection conn = DriverManager.getConnection(connectionString);
            
            // Create statement to execute SQL query
            Statement stmt = conn.createStatement();
            // Execute UPDATE query to modify product details
            stmt.executeUpdate("UPDATE Products SET "
                    + "ProductName = '" + p.getproductName() + "',"
                    + "Price = '" + p.getprice() + "',"
                    + "StockLevel = '" + p.getstockLevel() + "',"
                    + additionalSQL + " WHERE "
                    + "ProductId = '" + p.getproductId() + "'");
        } 
        catch (Exception ext) 
        {
            // Handle any exceptions that occur during the update
            System.out.println("Error editing product: " + ext.getMessage());
        }
    }
    
    /**
     * Deletes a product from the database based on the provided product ID.
     * @param productId the ID of the product to be deleted
     */
    public void deleteProduct(int productId)
    {
        try
        {
            // Load the database driver
            Class.forName(driver);
            // Establish connection to the database
            Connection conn = DriverManager.getConnection(connectionString);
            
            // Create statement to execute SQL query
            Statement stmt = conn.createStatement();
            // Execute DELETE query to remove the product with the specified ID
            stmt.executeUpdate("DELETE FROM Products WHERE ProductId = " + productId);
        }
        catch(Exception ext)
        {
            // Handle any exceptions that occur during the deletion process
            System.out.println("Error Removing Product:" + ext.getMessage());
        }
    }
    
    /**
     * Updates the stock level of a product in the database by reducing it by the specified quantity.
     * @param productId the ID of the product to update
     * @param quantity the quantity to reduce from the current stock level
     */
    public void updateProductStockLevel(int productId, int quantity)
    {
        try
        {
            // Load the database driver
            Class.forName(driver);
            // Establish connection to the database
            Connection conn = DriverManager.getConnection(connectionString);
            
            // Create statement to execute SQL query
            Statement stmt = conn.createStatement();
            // Update the stock level by reducing the specified quantity
            stmt.executeUpdate("UPDATE Products SET StockLevel = (StockLevel - " + quantity + ") WHERE ProductId = " + productId);
            
        }
        catch(Exception ex)
        {
            // Handle any exceptions that occur during the update
            System.out.println("Error: Updating StockLevel- " + ex.getMessage());
        }
    }
    
    /**
     * Writes an order record to the database and retrieves the generated order ID
     * @param o Order object containing order details
     * @param customerUsername the username of the customer placing the order
     * @return the generated OrderId from the database
     */
    public int writeOrder(Order o, String customerUsername)
    {
        int orderId = 0;
        
        try
        {
            // Load the database driver
            Class.forName(driver);
            // Establish connection to the database
            Connection conn = DriverManager.getConnection(connectionString);
            // Create statement to execute SQL query
            Statement stmt = conn.createStatement();
            // Insert new order record with date, customer, total, and status
            stmt.executeUpdate("INSERT INTO Orders(OrderDate, Username, OrderTotal, Status)" +
                    "VALUES (" +
                    "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(o.getorderDate()) + "'," +
                    "'" + customerUsername + "'," +
                    "'" + o.getorderTotal() + "'," +
                    "'" + o.getstatus() + "'" +
                    ")");
            
            // Retrieve the auto-generated order ID from the database
            ResultSet rs = stmt.getGeneratedKeys();
            
            // Check if an ID was generated and extract it
            if(rs.next())
            {
                orderId = rs.getInt(1);
                // Set the order ID on the Order object
                o.setOrderId(orderId);
            }
        }
        catch(Exception ex)
        {
            // Handle any database exceptions
            System.out.println("Error writing orders123: " + ex.getMessage());
        }
        finally
        {
            // Return the generated order ID
            return orderId;
        }
    }
    
    /**
     * Writes an order line record to the database
     * @param ol OrderLine object containing line details
     * @param orderId the ID of the parent order
     */
    public void writeOrderLine(OrderLine ol, int orderId)
    {
        try
        {
            // Load the database driver
            Class.forName(driver);
            // Establish connection to the database
            Connection conn = DriverManager.getConnection(connectionString);
            // Create statement to execute SQL query
            Statement stmt = conn.createStatement();
            // Insert new order line record with product and quantity details
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
            // Handle any database exceptions
            System.out.println("Error writing orderlines: " + ex.getMessage());
        }
    }
    
    /**
     * Loads all customers from the database into a HashMap
     * @return HashMap containing customers with username as key
     */
    public HashMap<String, Customer> loadCustomers()
    {
        // Create hashmap to store customer records
        HashMap<String, Customer> loadedCustomers = new HashMap();
        
        try
        {
            // Load the database driver
            Class.forName(driver);
            // Establish connection to the database
            Connection conn = DriverManager.getConnection(connectionString);
            
            // Create statement and execute query to retrieve all customers
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Customers");
            
            // Iterate through each customer record in the result set
            while(rs.next())
            {
                // Extract customer data from the result set
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String addressLine1 = rs.getString("AddressLine1");
                String addressLine2 = rs.getString("AddressLine2");
                String town = rs.getString("Town");
                String postcode = rs.getString("PostCode");
                
                // Create a new Customer object with the retrieved data
                Customer customer = new Customer(username, password, firstName, lastName, addressLine1, addressLine2, town, postcode);
                
                // Add customer to HashMap with username as the key
                loadedCustomers.put(username, customer);
            }
        }
        catch(Exception ex)
        {
            // Handle any database exceptions silently
        }
        finally
        {
            // Return the populated HashMap of customers
            return loadedCustomers;
        }
    }
    
    /**
     * Loads all staff members from the database into a HashMap
     * @return HashMap containing staff with username as key
     */
    public HashMap<String, Staff> loadStaff()
    {
        // Create hashmap to store staff records
        HashMap<String, Staff> loadedStaff = new HashMap();
        
        try
        {
            // Load the database driver
            Class.forName(driver);
            // Establish connection to the database
            Connection conn = DriverManager.getConnection(connectionString);
            
            // Create statement and execute query to retrieve all staff members
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Staff");
            
            // Iterate through each staff record in the result set
            while(rs.next())
            {
                // Extract staff data from the result set
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String position = rs.getString("Position");
                double salary = rs.getDouble("Salary");
                
                // Create a new Staff object with the retrieved data
                Staff staff = new Staff(username, password, firstName, lastName, position, salary);
                
                // Add staff member to HashMap with username as the key
                loadedStaff.put(username, staff);
            }
        }
        catch(Exception ex)
        {
            // Handle any database exceptions silently
        }
        finally
        {
            // Return the populated HashMap of staff members
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
        // Load all customers from the database
        HashMap<String, Customer> allCustomers = loadCustomers();
        
        // Check if the provided username exists in the customer records
        if(allCustomers.containsKey(username))
        {
            // Retrieve the customer with the matching username
            Customer customerWithCorrectUsername = allCustomers.get(username);
            
            // Verify that the provided password matches the stored password
            if(customerWithCorrectUsername.getpassword().equals(password))
            {
                // Return the authenticated customer
                return customerWithCorrectUsername;
            }
            
            else
            {
                // Return null if password is incorrect
                return null;
            }
        }
        
        else
        {
            // Return null if username does not exist
            return null;
        }
    }
    
    public Staff staff_login(String username, String password)
    {
        // Load all staff members from the database
        HashMap<String, Staff> allStaff = loadStaff();
        
        // Check if the provided username exists in the staff records
        if(allStaff.containsKey(username))
        {
            // Retrieve the staff member with the matching username
            Staff staffWithCorrectUsername = allStaff.get(username);
            
            // Verify that the provided password matches the stored password
            if(staffWithCorrectUsername.getpassword().equals(password))
            {
                // Return the authenticated staff member
                return staffWithCorrectUsername;
            }
            
            else
            {
                // Return null if password is incorrect
                return null;
            }
        }
        
        else
        {
            // Return null if username does not exist
            return null;
        }
    }
    
    public void DeleteCustomer(Customer c)
    {
        // Attempt to delete a customer record from the database
        try 
        {
            // Load the database driver
            Class.forName(driver);
            // Establish connection to the database
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();
            
            // Execute DELETE query with multiple conditions for security
            stmt.executeUpdate("DELETE FROM Customers WHERE "
                    + "Username = '" + c.getUsername() + "' AND"
                    + "Password = '" + c.getpassword() + "' AND"
                    + "LastName = '" + c.getlastName() + "'");
        } 
        catch (Exception ext) 
        {
            // Handle any exceptions that occur during deletion
            System.out.println("Error deleting account: " + ext.getMessage());
        }
    }
    
}
