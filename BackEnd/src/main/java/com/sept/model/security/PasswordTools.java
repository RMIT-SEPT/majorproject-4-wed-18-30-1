package com.sept.model.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

/**
 * Houses static functions for testing and modifying passwords.
 */
public class PasswordTools {
/**
 * @param password The plain text password that is to be hashed before adding to the database.
 * This function will return the 0 padded hexadecimal hash of password
 * @return <b>String:</b> SHA-512 digest of the password string 
 * @author Luke Magnusson
 * @version 1.0
 * @since 2020-08-06
 *
 * @see MessageDigest
 */
  public static String hashPassword(String password)
  {
    try {
      //Create digesting object
      MessageDigest consumer = MessageDigest.getInstance("SHA-512");

      //Digest the password
      byte[] digest = consumer.digest(password.getBytes());
      
      //convert digest to integer
      BigInteger bigInt = new BigInteger(digest);
      
      //Get hexadecimal of the digest
      String hash = bigInt.toString(16);
      
      //Pad hexadecimal to fit within spec
      hash=String.format("%032x", bigInt);
      
      return hash;
      
    } catch (NoSuchAlgorithmException e) {
      // should not be reached as "SHA-512" is hard coded in.
      throw new RuntimeException(e);
    }
  }
  
/**
 * Checks if password fits ALL the following criteria:
 * <p>  At least one (1) lower case letter
 *    {@code (?=.*[a-z])}
 * <p>  At least one (1) upper case letter
 *    {@code (?=.*[A-Z])}
 * <p>  At least one (1) number
 *    {@code (?=.*[0-9])}
 * <p>  At least one (1) special character case letter
 *    {@code (?=.*[!@#$%^&*])}
 * <p>  At least eight (8) total characters
 *    <code>(?=.{8,})}</code>
 * <p><b>Note:</b> Special characters are characters in the set "!@#$%^&*" (shift + 1..8), and does not currently check for punctuation characters or brackets
 * 
 * @param password string to be matched against the criteria for a strong password
 * @return <b>boolean:</b> True if the password fits the criteria.
 * @author Luke Magnusson
 * @since 2020-08-06
 * @version 1.0
 */
  public static boolean passwordMeetsRequirements(String password)
  {
    // Match groups
    //   (1)  .*[a-z] at least 1 lower case letter
    //   (2)  .*[A-Z] at least 1 upper case letter
    //   (3)  .*[0-9] at least 1 number
    //   (4)  .*[!@#\\$%\\^&\\*] at least 1 character in the set "!@#$%^&*"
    //   (5)  .{8,} at least 8 characters total
    String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\$%\\^&\\*])(?=.{8,})";
    
    // Check if the regex matches the password.
    return Pattern.matches(pattern, password);
  }
}