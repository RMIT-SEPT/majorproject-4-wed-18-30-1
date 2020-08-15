package model.users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.database.DatabaseConnection;
import exceptions.PasswordTooWeakException;
import model.security.PasswordTools;
import model.time.Interval;

public class UserFactory {
  
  //#region Saving to database
  /**
  * Creates a customer and adds them to the database before returning the new customer
  * @param name Name provided by the user, e.g. John Doe
  * @param email Email of the user, will cause SQLException if it is not unique
  * @param password Password of the user, will be validated for strength before creation
  * @return New {@link users.Customer} that has been added to the database
  * @author Luke Magnusson
  * @since 2020-08-07
  * @version 1.0
  * @throws ClassNotFoundException When {@link database.DatabaseConnection#DatabaseConnection()} throws ClassNotFoundException
  * @throws SQLException When the stored procedure is errant or email is not unique
  * @throws PasswordTooWeakException if the password does not meet requirements listed in {@link security.PasswordTools#passwordMeetsRequirements(String)}
  * @see security.PasswordTools#passwordMeetsRequirements(String)
  * @see database.DatabaseConnection#createCustomer(Customer)
  * @see database.DatabaseConnection#DatabaseConnection()
  */
  public Customer generateCustomer(String name, String email, String password) throws ClassNotFoundException, SQLException, PasswordTooWeakException
  {
    if (PasswordTools.passwordMeetsRequirements(password)) {
      Customer c = new Customer(name, email, password);
      DatabaseConnection.createCustomer(c);
      
      return c;  
    } else
    {
      throw new PasswordTooWeakException();
    }
    
  }
  
  
  /**
  * Creates a employee and adds them to the database before returning the new employee
  * @param name Name provided by the user, e.g. John Doe
  * @param email Email of the user, will cause SQLException if it is not unique
  * @param password Password of the user, will be validated for strength before creation
  * @return New {@link users.Employee} that has been added to the database
  * @author Luke Magnusson
  * @since 2020-08-07
  * @version 1.0
  * @throws ClassNotFoundException When {@link database.DatabaseConnection#DatabaseConnection()} throws ClassNotFoundException
  * @throws SQLException When the stored procedure is errant or email is not unique
  * @throws PasswordTooWeakException if the password does not meet requirements listed in {@link security.PasswordTools#passwordMeetsRequirements(String)}
  * @see security.PasswordTools#passwordMeetsRequirements(String)
  * @see database.DatabaseConnection#createEmployee(Employee)
  * @see database.DatabaseConnection#DatabaseConnection()
  */
  public Employee generateEmployee(String name, String email, String password, Business manager) throws ClassNotFoundException, SQLException, PasswordTooWeakException
  {
    if (PasswordTools.passwordMeetsRequirements(password)) {
      Employee e = new Employee(name, email, password, manager);
      DatabaseConnection.createEmployee(e);
      
      return e;
    } else
    {
      throw new PasswordTooWeakException();
    }
  }
  
  
  /**
  * Creates a business and adds them to the database before returning the new business
  * @param name Name provided by the user, e.g. John Doe
  * @param email Email of the user, will cause SQLException if it is not unique
  * @param password Password of the user, will be validated for strength before creation
  * @return New {@link users.business} that has been added to the database
  * @author Luke Magnusson
  * @since 2020-08-07
  * @version 1.0
  * @throws ClassNotFoundException When {@link database.DatabaseConnection#DatabaseConnection()} throws ClassNotFoundException
  * @throws SQLException When the stored procedure is errant or email is not unique
  * @throws PasswordTooWeakException if the password does not meet requirements listed in {@link security.PasswordTools#passwordMeetsRequirements(String)}
  * @see security.PasswordTools#passwordMeetsRequirements(String)
  * @see database.DatabaseConnection#createBusiness(Business)
  * @see database.DatabaseConnection#DatabaseConnection()
  */
  public Business generateBusiness(String name, String email, String password, String location, Interval[] openingHours) throws ClassNotFoundException, SQLException, PasswordTooWeakException
  {
    if (PasswordTools.passwordMeetsRequirements(password)) {
      Business b = new Business(name, email, password, location, openingHours);
      DatabaseConnection.createBusiness(b);
      
      return b;
    } else
    {
      throw new PasswordTooWeakException();
    }
  }
  //#endregion
  
  //#region Building From database
  /**
  * Loads the businesses from the database
  * @author Luke Magnusson
  * @since 2020-08-06
  * @version 1.0
  * @return Array of the businesses that were stored on the database
  * @see database.DatabaseConnection#selectBusinesses()
  */
  public Business[] loadBusinesses()
  {
    ResultSet rs;
	try {
    rs = DatabaseConnection.selectBusinesses();
    
    
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    List<Business> result = new ArrayList<>();
    
    //Column names
    //u.name | u.email | u.passwdHash | b.streetAddress | b.postCode | a.dayOfWeek | a.startTime | a.endTime
    List<String[]> table = getTable(rs);
    
    for (String[] row : table) {
      Business b = new Business(row[0], row[1], row[2], row[3] + " " + row[4]);
      
      //Search for already existent entry
      int index = result.indexOf(b);
      
      //If it doesn't exist, add it
      if (index == -1)
      {
        index = result.size();
        result.add(b);
      }
      Interval openingHours = new Interval(row[6], row[7]);
      b.setOpeningHours(openingHours, Integer.parseInt(row[5]));
    }
    
    return result.toArray(new Business[result.size()]);
  }

  /**
  * Loads the employees from the database
  * @author Luke Magnusson
  * @since 2020-08-06
  * @version 1.0
  * @return Array of the employees that were stored on the database
  * @see database.DatabaseConnection#selectEmployees()
  */
  public Employee[] loadEmployees()
  {
    ResultSet rs = DatabaseConnection.selectEmployees();
    List<Employee> result = new ArrayList<>();
    
    //Column names
    //u.name | u.email | u.passwdHash | b.streetAddress | b.postCode | a.dayOfWeek | a.startTime | a.endTime
    List<String[]> table = getTable(rs);
    Business[] businesses = loadBusinesses();
    
    for (String[] row : table) {
      Business b = (Business) findMatch(businesses, row[3]);
      Employee e = new Employee(row[0], row[1], row[2], b, true);
      
      if (result.indexOf(e) == -1)
      result.add(e);
      else
      throw new RuntimeException("Employee " + e.toString() + "is stored twice in the database");
    }
    
    return result.toArray(new Employee[result.size()]);
  }
  
  /**
  * Loads the customers from the database
  * @author Luke Magnusson
  * @since 2020-08-06
  * @version 1.0
  * @return Array of the customers that were stored on the database
  * @see database.DatabaseConnection#selectCustomers()
  */
  public Customer[] loadCustomers()
  {
    ResultSet rs = DatabaseConnection.selectCustomers();
    
    //Column names
    //u.name | u.email | u.passwdHash | b.streetAddress | b.postCode | a.dayOfWeek | a.startTime | a.endTime
    List<String[]> table = getTable(rs);
    List<Customer> result = new ArrayList<>();
    
    for (String[] row : table) {
      Customer c = new Customer(row[0], row[1], row[2], true);
      
      if (result.indexOf(c) == -1)
      result.add(c);
      else
      throw new RuntimeException("Employee " + c.toString() + "is stored twice in the database");
    }
    
    return result.toArray(new Customer[result.size()]);
  }
  //#endregion
  
  //#region Private utilities
  /**
  * 
  * @param rs ResultSet from a SQL query or stored procedure that returned a table of results
  * @return list of records from the ResultSet
  * @author Luke Magnusson
  * @since 2020-08-06
  * @version 1.0
  * @see database.DatabaseConnection#selectBusinesses()
  * @see database.DatabaseConnection#selectCustomers()
  * @see database.DatabaseConnection#selectEmployees()
  */
  private List<String[]> getTable(ResultSet rs)
  {
    List<String[]> table = new ArrayList<>();
    try {
      int columnCount = rs.getMetaData().getColumnCount();
      
      
      while(rs.next())
      {
        String[] row = new String[columnCount];
        for (int i=0; i <columnCount ; i++)
        {
          row[i] = rs.getString(i + 1);
        }
        table.add(row);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println(
      "SQL error while converting ResultSet to List<String[]> [users.UserFactory.getTable]"
      );
    }
    
    return table;
  }
  
  /**
  * 
  * @param users array of users where this ID is suspected to exist
  * @param ID identifier of the user in question
  * @return Null if user is not found, otherwise will return the user
  * @author Luke Magnusson
  * @since 2020-08-06
  * @version 1.0
  */
  private User findMatch(User[] users, String ID)
  {
    for (User user : users) {
      if (user.id == ID)
      return user;
    }
    return null;
  }
  //#endregion
}