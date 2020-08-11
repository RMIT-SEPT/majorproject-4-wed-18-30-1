package model.users;

import model.time.Interval;

public class Business extends User{
  //#region Variables
  protected Interval[] openingHours =  new Interval[7];
  protected Employee[] employees;
  protected String location;  
  
  //#region Properties
  //#region getters
  public Interval[] getOpeningHours()
  {
    return openingHours;
  }
  //#endregion
  //#endregion 
  //#region Constructors
  
  Business(String name, String email, String hashedPassword, String location)
  {
    super(name, email, hashedPassword, true);
  }
  
  Business(String name, String email, String password, String location, Interval[] openingHours)
  {
    super(name, email, password);
    this.location = location;
    
    if (openingHours.length == 7) 
    this.openingHours = openingHours;  
    else
    throw new IndexOutOfBoundsException("You can only have 7 days in your opening hours, no more, no less.");
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

  @Override
  public boolean equals(Object o)
  {
    //null check  && type check && reflexive check / equality check
    return o!=null 
    && getClass() == o.getClass() 
    && id.equals(((Business) o).id);
  }
  //#endregion
  
  //#region setters
  public void setOpeningHours(Interval openingHours, int day)
  {
    this.openingHours[day] = openingHours;
  }
  //#endregion
  
  //#endregion
}