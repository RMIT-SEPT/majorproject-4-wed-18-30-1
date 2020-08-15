package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
*  Listens for requests made to the application port, then creates a thread to handle the request
* @author Luke Magnusson
* @since 2020-08-08
* @version 1.0
*/
@RestController
public class AddBookingController {
  
  //#region fields
  //#region Private Fields
  //#endregion
  //#endregion
  
  //#region Controls
  /**
  * Testing request that provides a sign of life from the server
  * @return "Spring Boot says \"Hello world!\"\n"
  * @author Luke Magnusson
  * @since 2020-08-08
  * @version 1.0
  */
  @RequestMapping("/AddBooking")
  public String index() {
    //TODO auto-generated stub
    return "Functionality has not been provided for this use case yet.\n";
  }
  //#endregion
  //#region Private methods
  
  //#endregion
}