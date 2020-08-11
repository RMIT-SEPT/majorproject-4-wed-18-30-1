package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.time.Interval;
import model.users.Business;
import model.users.Customer;
import model.users.Employee;
import model.utility.ArrayUtils;

/**
* Houses static procedures for executing stored procedures on the soft-eng-sql server
* @author Luke Magnusson
* @version 1.0
* @since 2020-08-06
*/
public class DatabaseConnection {
  
  /**
  * Creates a connection to the SQL server with which to send SQL statements
  * <pre>{@code
    *   Connection con = getConnection();
    *   stmt = con.getStatement();
    *   return stmt.executeQuery("SELECT * FROM users")
    *}
    * </pre>
    * @return open connection to soft-eng-sql server
    * @throws ClassNotFoundException
    * @throws SQLException
    * @author Luke Magnusson
    * @version 1.0
    * @since 2020-08-06
    * @see java.sql.Connection
    * @throws SQLException When the SQL code is errant, or the username and password provided are incorrect.
    * @throws ClassNotFoundException when calling {@code Class.forName("com.mysql.jdbc.Driver");} raises an exception
    */
    private static Connection getConnection() throws ClassNotFoundException, SQLException
    {
      String connectionURL = "jdbc:mysql://IronAcres.duckdns.org:3306/soft-eng";
      Class.forName("com.mysql.jdbc.Driver");
      
      //TODO change sign in to one with less permissions.
      Connection con = DriverManager.getConnection(connectionURL, "root", "d2f57a8a974b218fb1e6cfa9457636d4");
      return con;
    }
    
    /**
    * @param email Email address of the user, must be unique
    * @param passwordHash SHA-512 Hash of their password
    * @param name Name of the user e.g. Jane Doe, may be empty
    * @return {@code publicID} of the user generated with this method
    * @author Luke Magnusson
    * @version 1.0
    * @throws SQLException When the SQL code is errant, or the entry already exists with that private key
    * @throws ClassNotFoundException when calling {@code Class.forName("com.mysql.jdbc.Driver");} raises an exception
    * @since 2020-08-06
    * @see java.sql.Connection
    * @see security.PasswordTools#hashPassword(String)
    */
    public static ResultSet createCustomer(String email, String passwordHash, String name) throws ClassNotFoundException, SQLException
    {
      return executeStoredProcedure("CreateCustomer", email, passwordHash, name);
    }
    
    /**
    * Wrapper for stored procedure <b>CreateEmployee</b>
    * @param email email address of employee, must be unique
    * @param passwordHash Hash of their password
    * @param name Name of the employee, may be empty
    * @param businessID identifier of the business they work for
    * 
    * @return {@code publicID} of the user generated with this method
    * @author Luke Magnusson
    * @since 2020-08-06
    * @version 1.0
    * @throws SQLException when the stored procedure doesn't exist or is errant
    * @throws ClassNotFoundException when calling {@code Class.forName("com.mysql.jdbc.Driver");} raises an exception
    * @see security.PasswordTools#hashPassword(String)
    */
    public static ResultSet createEmployee(String email, String passwordHash, String name, String businessID) throws ClassNotFoundException, SQLException
    {
      return executeStoredProcedure("CreateEmployee", email, passwordHash, name, businessID);
    }
    
    /**
    * Wrapper for stored procedure <b>CreateBusiness</b>
    * @param email email address of the manager, must be unique
    * @param passwordHash SHA-512 Hash of provided password
    * @param name Name of business owner/manager, e.g. Jane Smith, may be empty
    * @param businessName Name provided for the business, e.g. ACME inc.
    * @param businessStreetAddress Street address, e.g. 123 Main Rd
    * @param businessPostcode Postcode for the proposed entry, e.g. 3000
    * @return {@code publicID} of the user generated with this method
    * @author Luke Magnusson
    * @since 2020-08-06
    * @version 1.0
    * @throws SQLException when the stored procedure doesn't exist or is errant
    * @throws ClassNotFoundException when calling {@code Class.forName("com.mysql.jdbc.Driver");} raises an exception
    * @see security.PasswordTools#hashPassword(String)
    */
    public static ResultSet createManager(String email, String passwordHash, String name, String businessName, String businessStreetAddress, int businessPostcode) throws ClassNotFoundException, SQLException
    {
      return executeStoredProcedure("CreateBusiness", email, passwordHash, name, businessName, businessStreetAddress, String.valueOf( businessPostcode));
    }
    
    /**
    * Wrapper for stored procedure <b>LoginUser</b>
    * @param email
    * @param passwordHash 
    * @return {@code publicID} of user who matches the provided details
    * @author Luke Magnusson
    * @since 2020-08-06
    * @version 1.0
    * @throws SQLException when the stored procedure doesn't exist or is errant
    * @throws ClassNotFoundException when calling {@code Class.forName("com.mysql.jdbc.Driver");} raises an exception
    * @see security.PasswordTools#hashPassword(String)
    */
    public static ResultSet loginUser(String email, String passwordHash) throws ClassNotFoundException, SQLException
    {
      return executeStoredProcedure("LoginUser",  passwordHash,email);
    }
    
    /**
    * Wrapper for stored procedure <b>SelectEmployee</b>
    * @param employeeID
    * @return
    * @author Luke Magnusson
    * @since 2020-08-06
    * @version 1.0
    * @throws SQLException when the stored procedure doesn't exist or is errant
    * @throws ClassNotFoundException when calling {@code Class.forName("com.mysql.jdbc.Driver");} raises an exception
    */
    public static ResultSet selectEmployee(int employeeID) throws ClassNotFoundException, SQLException
    {
      return executeStoredProcedure("SelectEmployee", String.valueOf(employeeID));
    }
    
    /**
    * 
    * @param procedure stored procedure to execute
    * @param args Arguments for the stored procedure
    * @return ResultSet that is returned by the stored procedure
    * @throws ClassNotFoundException
    * @throws SQLException
    * 
    * @author Luke Magnusson
    * @since 2020-08-06
    * @version 1.0
    * @throws SQLException when the stored procedure doesn't exist or is errant
    * @throws ClassNotFoundException when calling {@code Class.forName("com.mysql.jdbc.Driver");} raises an exception
    */
    private static ResultSet executeStoredProcedure(String procedure, String... args) throws SQLException, ClassNotFoundException   
    {
      //Initialize disposables
      Connection con = null;
      Statement stmt = null;
      ResultSet rs = null;
      SQLException sqlErr = null;
      ClassNotFoundException classErr = null; 
      try {
        con = getConnection();
        
        
        //Call the stored procedure
        procedure = String.format("EXECUTE %s",  procedure);
        
        //Append arguments
        procedure += " \'" + args[0] + "\'";
        for (int i = 1; i < args.length; i++) {
          procedure += ", \'" + args[i] + '\'';
        }
        
        //Execute procedure
        stmt = con.createStatement();
        rs = stmt.executeQuery(procedure);
        
      } catch (SQLException e) {
        e.printStackTrace();
        sqlErr = e;
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
        classErr = e;

      } finally{
        
        //Free result resources
        if (rs != null)
        {
          try {
            rs.close();
          } catch (SQLException e) {
            //This space is intentionally left blank
          }
        }
        
        //Free statement resources
        if (stmt != null)
        {
          try {
            stmt.close();
          } catch (SQLException e) {
            //This space is intentionally left blank
          }
        }
        
        //Free connection resources
        if (con != null)
        {
          try {
            con.close();
          } catch (SQLException e) {
            //This space is intentionally left blank
          }
        }
      }
      //If the results are tainted rethrow the exception
      if(sqlErr != null)
      throw sqlErr;
      if(classErr != null)
      throw classErr;
      
      return rs;
    }
    
    public static ResultSet createCustomer(Customer c) throws ClassNotFoundException, SQLException
    {
      return createCustomer(c.getEmail(), c.getPasswordHash(), c.getName());
    }
    
    public static ResultSet createEmployee(Employee e) throws ClassNotFoundException, SQLException {
      return createEmployee(e.getEmail(), e.getPasswordHash(), e.getName(), e.getManager().getID());
    }
    
    public static ResultSet createBusiness(Business b) throws ClassNotFoundException, SQLException {
      ArrayUtils<Interval> au = new ArrayUtils<>();
      
      String openingHours = '\'' 
                          + au.toString(b.getOpeningHours(), "\', \'") 
                          + '\'';
      return createEmployee(b.getEmail(), b.getPasswordHash(), b.getName(), openingHours);
    }

	public static ResultSet selectBusinesses() {
		return null;
	}

	public static ResultSet selectEmployees() {
		return null;
	}

	public static ResultSet selectCustomers() {
		return null;
	}
  }