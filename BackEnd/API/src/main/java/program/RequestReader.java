package program;

/**
*  Listens for requests made to the application port, then creates a thread to handle the request
* @author Luke Magnusson
* @since 2020-08-08
* @version 1.0
*/
public class RequestReader implements Runnable {
  
  //#region fields
  //#region Private Fields
  /**
  * shouldRun, (boolean): object that is checked each loop of the main program, if false the main program will exit gracefully, if true, it will continue to listen for requests.
  * @author Luke Magnusson
  * @since 2020-08-07
  * @version 1.0
  */
  private boolean shouldRun = true;
  //#endregion
  //#endregion
  
  //#region Controls
  
  /**
  * Listens for requests made to the application port, then creates a thread to handle the request
  * @author Luke Magnusson
  * @since 2020-08-08
  * @version 1.0
  */
  @Override
  public void run() {
    shouldRun = true;
    while (shouldRun) {
      //Listen for the next request
      String json = getRequest();
      
      //Create thread to handle the request
      APIModel model = new APIModel(json);
      model.run();
    }
  }
  
  /**
  * Gracefully exits the main thread at the end of its current iteration.
  * @author Luke Magnusson
  * @since 2020-08-08
  * @version 1.0
  */
  public void stop(){
    this.shouldRun = false;
  }
  //#endregion
  //#region Private methods
  
  /**
  * Gets the next request made by the frontend pages
  * @return Json string of request made by frontend
  * @author Luke Magnusson
  * @since 2020-08-07
  * @version 1.0
  */
  private String getRequest()
  {
    //TODO method stub
    return "";
  }
  //#endregion
}