package src.main.java.users;

public class Employee extends User{
  //#region Variables
  Business manager;
  
   //#region Properties
  //#region getters
  public Business getManager()
  {
    return manager;
  }
//#endregion

//#region setters

//#endregion
//#endregion
  //#endregion 
  //#region Constructors
  Employee(final String email, final String password, final Business manager)
  {
    super(email, password);
    this.manager = manager;
  }
  
  Employee(final String name, final String email, final String password, final Business manager)
  {
    this(name, email, password, manager, false);
  }
  public Employee(String name, String email, String hashedPassword, Business manager, boolean isHashed) {
    super(name, email, hashedPassword, isHashed);
    this.manager = manager;
}

//#endregion 
  //#region Functions
  //#region Inheritance
  @Override
  public void updateBookings() {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void updateLogin(String email, String password, String name, String id) {
    // TODO Auto-generated method stub
    
  }
  //#endregion
  
  //#endregion
  
}