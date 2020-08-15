package com.sept.model.time;

import java.util.Date;

public class Interval {  
  //#region Variables
  /**
  * Represents the start of the interval
  * @author Luke Magnusson
  * @version 1.1
  * @since 2020-08-06
  * @hidden Switched to {@link Event} as of 2020-08-08
  */
  final Event startDate;
  
  /**
  * Represents the end of the interval
  * @author Luke Magnusson
  * @version 1.1
  * @since 2020-08-06
  * @hidden Switched to {@link Event} as of 2020-08-08
  */
  final Event endDate;
  
  //#endregion
  
  //#region Constructors
  
  /**
  * Creates a Interval that represents this timespan
  * @param startDate
  * @param endDate
  */
  public Interval(Event startDate, Event endDate) {
    this.startDate = startDate;
    this.endDate = endDate;
  }
  //#endregion
  
  //#region Functions
  
  /**
  * Soon to be deprecated as it does not play well with bookings
  * @author Luke Magnusson
  * @version 1.0
  * @since 2020-08-07
  * @param startTime string representation of an event
  * @param endTime string representation of an event
  * @see Event#toString()
  */
  public Interval(String startTime, String endTime) {
    startDate = new Event(startTime);
    endDate = new Event(endTime);
  }
  
  //#region Overrides
  @Override
  public String toString() {
    return '\'' + startDate.toString() + "\', \'" + endDate.toString() + '\'';
  }
  //#endregion
  
  //#region Comparisons
  /**
  * @param i interval to be checked against this one
  * @return Returns true if the interval starts or ends inside this interval
  * @author Luke Magnusson
  * @version 1.0
  * @since 2020-08-06
  * @see java.util.Date#before(Date)
  * @see java.util.Date#after(Date)
  */
  public boolean intersects(Interval i)
  {
    return intersects(i.startDate, i.endDate);
  }
  
  /**
  * The params start and end are arbitrary. i.e. the parameters can be reversed without raising an issue
  * @param start date that represents one extreme of the timespan that you are checking against
  * @param end the date that represents the other extreme of the timespan you are checking against
  * @return Returns true if the interval starts or ends inside this interval
  * @author Luke Magnusson
  * @version 1.1
  * @since 2020-08-06
  * @see java.util.Date#before(Date)
  * @see java.util.Date#after(Date)
  */
  public boolean intersects(Event start, Event end)
  {
    return intersects(start) || intersects(end);
  }
  
  /**
  * @param time Date to be checked against this one
  * @return Returns true if the date exists inside this interval
  * @author Luke Magnusson
  * @version 1.0
  * @since 2020-08-06
  * @see java.util.Date#before(Date)
  * @see java.util.Date#after(Date)
  */
  public boolean intersects(Event time)
  {
    return time.isAfter(startDate) && time.isBefore(endDate);
  }
  
  /**
  * @return readonly variable startDate
  * @author Luke Magnusson
  * @version 1.0
  * @since 2020-08-06
  */
  public Event getStart()
  {
    return startDate;
  }
  
  /**
  * @return readonly variable endDate
  * @author Luke Magnusson
  * @version 1.0
  * @since 2020-08-06
  */
  public Event getEnd()
  {
    return endDate;
  }
  //#endregion
  //#endregion
}