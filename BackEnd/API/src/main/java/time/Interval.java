package src.main.java.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Interval {
  //#region Static methods
  public static String toString(Interval[] intervals)
  {
    StringBuilder sb = new StringBuilder(intervals[0].toString());
    
    for (int i = 1; i < intervals.length; i++) {
      sb.append(", " + intervals[i].toString());
    }
    
    return sb.toString();
  }
  //#endregion
  
  //#region Variables
  /**
  * Represents the start of the interval
  * @author Luke Magnusson
  * @version 1.0
  * @since 2020-08-06
  */
  final Instant startDate;
  
  /**
  * Represents the end of the interval
  * @author Luke Magnusson
  * @version 1.0
  * @since 2020-08-06
  */
  final Instant endDate;
  
  //#endregion
  
  //#region Constructors
  
  /**
  * Creates a Interval that represents this timespan
  * @param startDate
  * @param endDate
  */
  public Interval(Instant startDate, Instant endDate) {
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
  * @param startTime string representation of a date in the format [day-of-week (numeric)] [hour]:[minute]
  * @param endTime string representation of a date in the format [day-of-week (numeric)] [hour]:[minute]
  */
  public Interval(String startTime, String endTime) {
    DateTimeFormatter f = 
    DateTimeFormatter.ofPattern( "e hh:mm" , Locale.UK )
                     .withZone(ZoneId.systemDefault());
    
    LocalDateTime ldt = LocalDateTime.parse( startTime , f );
    this.startDate = ldt.atZone(ZoneId.systemDefault()).toInstant();
    
    
    ldt = LocalDateTime.parse(endTime , f);
    this.endDate = ldt.atZone(ZoneId.systemDefault()).toInstant();
    
  }
  
  //#region Overrides
  @Override
  public String toString() {
    DateTimeFormatter f =
    DateTimeFormatter.ofPattern("e hh:mm" , Locale.UK)
                     .withZone(ZoneId.systemDefault());
    return f.format(startDate) + ", " + f.format(endDate);
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
  * @version 1.0
  * @since 2020-08-06
  * @see java.util.Date#before(Date)
  * @see java.util.Date#after(Date)
  */
  public boolean intersects(Instant start, Instant end)
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
  public boolean intersects(Instant time)
  {
    return time.isAfter(startDate) && time.isBefore(endDate);
  }
  
  /**
  * @return readonly variable startDate
  * @author Luke Magnusson
  * @version 1.0
  * @since 2020-08-06
  */
  public Instant getStart()
  {
    return startDate;
  }
  
  /**
  * @return readonly variable endDate
  * @author Luke Magnusson
  * @version 1.0
  * @since 2020-08-06
  */
  public Instant getEnd()
  {
    return endDate;
  }
  
  private String toString(Instant i)
  {
    return String.valueOf(i.atZone(ZoneOffset.UTC).getHour()) 
    + ':' + i.atZone(ZoneOffset.UTC).getMinute();
  }
  //#endregion
  //#endregion
}