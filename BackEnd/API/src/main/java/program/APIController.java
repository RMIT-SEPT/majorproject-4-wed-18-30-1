package program;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
*  Listens for requests made to the application port, then creates a thread to handle the request
* @author Luke Magnusson
* @since 2020-08-08
* @version 1.0
*/
@RestController
public class APIController {
  
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
  @RequestMapping("/")
	public String index() {
		return "Spring Boot says \"Hello world!\"";
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