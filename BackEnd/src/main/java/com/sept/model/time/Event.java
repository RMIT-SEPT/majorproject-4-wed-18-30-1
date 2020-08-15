package com.sept.model.time;

public class Event {
  //Epoch is 00:00:00 January first 2020
  private long secondsSinceEpoch = 0;
  private Occurrence frequency = Occurrence.Once;
  
  //#region constants
  public static final int SECONDS_PER_MINUTE = 60;
  public static final int SECONDS_PER_HOUR = 60 * SECONDS_PER_MINUTE;
  public static final int SECONDS_PER_DAY = 24 * SECONDS_PER_HOUR;
  public static final int SECONDS_PER_WEEK = 7 * SECONDS_PER_DAY;
  public static enum Occurrence {Once, Daily, Weekly};
  //#endregion
  
  //#region Constructors
  public Event(int week, int day, int hour, int minute, int second, Occurrence frequency)
  {
    this(day, hour, minute, second, frequency);
    secondsSinceEpoch += SECONDS_PER_WEEK * week;
    secondsSinceEpoch %= getDivisor(frequency);
  }
  
  public Event(int day, int hour, int minute, int second, Occurrence frequency)
  {
    this(hour, minute, second, frequency);
    secondsSinceEpoch += SECONDS_PER_DAY * day;
    secondsSinceEpoch %= getDivisor(frequency);
  }
  public Event(int hour, int minute, int second, Occurrence frequency)
  {
    secondsSinceEpoch = SECONDS_PER_HOUR * hour 
    + SECONDS_PER_MINUTE * minute 
    + second;
    
    this.frequency = frequency;
    secondsSinceEpoch %= getDivisor(frequency);
  }
  
  public Event(int week, int day, int hour, int minute, int second)
  {
    this(day, hour, minute, second);
    secondsSinceEpoch += SECONDS_PER_WEEK * week;
  }
  
  public Event(int day, int hour, int minute, int second)
  {
    this(hour, minute, second);
    secondsSinceEpoch += SECONDS_PER_DAY * day;
  }
  public Event(int hour, int minute, int second)
  {
    secondsSinceEpoch = SECONDS_PER_HOUR * hour 
    + SECONDS_PER_MINUTE * minute 
    + second;
  }
  
  public Event(String event)
  {
    String[] params = event.split(" ");
    
    //Get frequency of the event
    int freq = Integer.parseInt(params[0]);
    frequency = Occurrence.values()[freq];
    
    //Get the time of the event
    secondsSinceEpoch = Integer.parseInt(params[1]);
  }
  //#endregion
  
  
  /**
  * @author Luke Magnusson
  * @version 1.0
  * @since 2020-08-08
  * @param e An event that may have a frequency
  * @return true if e occurred after this event
  * @see Event#compare(Event)
  */
  public boolean isBefore(Event e)
  {
    return compare(e) < 0;
  }
  
  /**
  * @author Luke Magnusson
  * @version 1.0
  * @since 2020-08-08
  * @param e An event that may have a frequency
  * @return true if e occurred before this event
  * @see Event#compare(Event)
  */
  public boolean isAfter(Event e)
  {
    return compare(e) > 0;
  }
  
  /**
  * Gets the number of seconds between two events
  * @author Luke Magnusson
  * @version 1.0
  * @since 2020-08-08
  * @param e An event that may have a frequency
  * @return number of seconds between e and this event, n>0 if e occurred closer to the epoch
  */
  public long compare(Event e)
  {
    //Converts e to a time in the domain of [0, max value for this frequency]
    //Converts this to a time in the domain of [0, mac value for e]
    return (secondsSinceEpoch % getDivisor(e.frequency)) - (e.secondsSinceEpoch % getDivisor(frequency));
  }
  
  @Override
  public String toString() {
    return frequency.ordinal() + " " + secondsSinceEpoch;
  }
  
  /**
  * 
  * @param o the frequency of the domain event, e.g. weekly for an event that occurs once per week when converting a daily
  * @return a number (x) such that for event e mod x, e can be compared as if the domain event occurred in the same frame of reference as e.
  */
  private static long getDivisor(Occurrence o)
  {
    long freq = Long.MAX_VALUE;
    
    switch (o) {
      case Weekly:
      freq = SECONDS_PER_WEEK;
      break;
      
      case Daily:
      freq = SECONDS_PER_DAY;
      break;
      
      default:
      break;
    }
    
    return freq;
  }
}