package model.users;

import model.security.PasswordTools;
import model.time.Booking;

public abstract class User {
  //#region Variables
  String hashedPassword;
  String email;
  String name;
  String id;
  Booking[] bookings; 
  
  //#region Properties
  //#region getters
  public String getPasswordHash()
  {
    return hashedPassword;
  }
  
  
  public String getEmail()
  {
    return email;
  }
  
  public String getName()
  {
    return name;
  }
  
  
  public String getID()
  {
    return id;
  }
  
  
  public Booking[] getBookings()
  {
    return bookings;
  }
  //#endregion
  
  //#region setters
  
  //#endregion
  //#endregion
  
  //#endregion 
  
  //#region Constructors
  public User(String email, String password)
  {
    this.hashedPassword = PasswordTools.hashPassword(password);
    this.email = email;
  }
  
  public User(String name, String email, String password)
  {
    this(email, password);
    this.name = name;  
  }
  
  public User(String name, String email, String hashedPassword, boolean b) {
    this(name, email, hashedPassword);
 
    if(b)
      this.hashedPassword = hashedPassword;
  }
  
  
  //#endregion 
  //#region Functions
  //#region abstract
  public abstract void updateBookings();
  
  /**
  * Candidate for moving to a non-abstract implementation
  */
  public abstract void updateLogin(String email, String password, String name, String id);
  //#endregion
  //#region Concrete
  
  //#endregion
  //#endregion
  
}

/*
# password:string
# email:string
+ name:string
+ID:string
-Bookings:Booking[]

--------------------------------------------

+ updateBookings():void
+updateLogin(String password, String email, String name):bool
+checkPasswordStrength(String password):bool
*/