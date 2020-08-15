package com.sept.exceptions;

public class UnknownRequest extends RuntimeException {
  /**
	 *
	 */
	private static final long serialVersionUID = 7679028037036895806L;

public UnknownRequest(String request)
  {
    super(String.format("The request: %s\nis malformed or not handled by this version of the API", request));
  }
  
}