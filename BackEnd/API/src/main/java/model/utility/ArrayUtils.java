package model.utility;

import exceptions.NoAvailableDelimiterException;

public class ArrayUtils<T> {
  //#region methods
  public String toString(T[] items, String delimiter)
  {
    StringBuilder sb = new StringBuilder(items[0].toString());
    
    for (int i = 1; i < items.length; i++) {
      sb.append(delimiter + items[i].toString());
    }
    
    return sb.toString();
  }

  public String toString(T[] items) throws NoAvailableDelimiterException
  {
    final String delimiters = " ,|\t;*&";
    StringBuilder contents = new StringBuilder();

    for (T t : items) {
      contents.append(t.toString());
    }
    final String badChars = contents.toString();

    for (char c : delimiters.toCharArray()) {
      if (!badChars.contains(c + ""))
      {
        return toString(items, c + "");
      }
    }
    throw new NoAvailableDelimiterException();
  }
  //#endregion
}