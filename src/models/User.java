/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author 30513214
 */
public class User {
    //Attributes 
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    
    //Constructor
    public User()
    {
        username = "none";
        password = "none";
        firstName = "none";
        lastName = "none";
        
    }
    
    //Overloaded method (constructor)
    public User(String usernameIn, String passwordIn, String firstNameIn, String lastNameIn)
    {
        username = usernameIn;
        password = passwordIn;
        firstName = firstNameIn;
        lastName = lastNameIn;
    }
    
    //getter method for username
    public String getusername()
    {
       return username; 
    }
    
    //setter method for username
    public void setusername(String usernameIn)
    {
       username = usernameIn;
    }
    
    //getter method for password
    public String getpassword()
    {
       return password; 
    }
    
    //setter method for password
    public void setpassword(String passwordIn)
    {
       password = passwordIn;
    }
    
    //getter method for firstName
    public String getfirstName()
    {
       return firstName; 
    }
    
    //setter method for firstName
    public void setfirstName(String firstNameIn)
    {
       firstName = firstNameIn;
    }
    
    //getter method for lastName
    public String getlastName()
    {
       return lastName; 
    }
    
    //setter method for lastName
    public void setlastName(String lastNameIn)
    {
       lastName = lastNameIn;
    }
    
    public String getUsername()
    {
        return username;
    }


}

//Constructor
    
