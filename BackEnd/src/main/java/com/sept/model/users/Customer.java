package com.sept.model.users;

public class Customer extends User{

	
  //#region Variables
     //Section left intentionally blank
  //#endregion 
  //#region Constructors
    Customer(String email, String password)
    {
      super(email, password);
    }

    Customer(String name, String email, String password)
    {
      super(name, email, password);
    }

    Customer(String name, String email, String hashedPassword, boolean isHashed)
    {
      super(name, email, hashedPassword, isHashed);
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