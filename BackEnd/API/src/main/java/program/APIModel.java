package program;

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
  private boolean run = true;
  
  //#endregion
  
  //#region Constructors
  
  public APIModel()
  {
    
  }
  //#endregion
  
  
  //#region Tasks
  @Override
  public void run() {
    while (run)
    {
      String request = getRequest();
    }
  }
  
  //#region private tasks
  private String getRequest()
  {
    //TODO method stub
    return "";
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
  
  //#region Controls
  public void stop()
  {
    run = false;
    
  }
  
  //#endregion
  
  
}