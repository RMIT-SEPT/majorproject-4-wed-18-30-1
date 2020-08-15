package com.sept.model;

import com.sept.exceptions.UnknownRequest;

public class APIModel implements Runnable {
  //#region variables
  //#region Public
  
  //#endregion
  //#region Protected
  
  //#endregion
  //#region Package
  
  //#endregion
  //#region Private
  
  /**
  * run, (boolean): object that is checked each loop of the main program, if false the main program will exit gracefully, if true, it will continue to listen for requests.
  *  @author Luke Magnusson
  * @since 2020-08-07
  * @version 1.0
  */
  private String jsonRequest = "";
  private enum requestReason {unknownReason, createUser, modifyUser, reloadUsers, reloadBookings}
  //#endregion
  
  //#region Constructors
  
  
  
  public APIModel(String requestJson)
  {
    jsonRequest = requestJson;   
  }
  //#endregion
  
  
  //#region Tasks
  @Override
  public void run() {
    switch (getReason()) {
      case createUser:
      createUser(jsonRequest);
      break;
      
      case modifyUser:
      modifyUser(jsonRequest);
      break;
      
      case reloadUsers:
      reloadUsers(jsonRequest);
      break;
      
      case reloadBookings:
      reloadBookings(jsonRequest);
      break;
      
      default:
      throw new UnknownRequest(jsonRequest);
    }
  }
  
  //#region private tasks
  private requestReason getReason()
  {
    //TODO method stub
    return requestReason.unknownReason;
  }
  
  private boolean createUser(String request)
  {
    //TODO method stub
    return false;
  }
  
  private boolean modifyUser(String request)
  {
    //TODO method stub
    return false;
  }
  
  private boolean reloadUsers(String request)
  {
    //TODO method stub
    return false; 
  }
  
  private boolean reloadBookings(String request)
  {
    //TODO method stub
    return false;
  }
  //#endregion
  //#endregion  
}