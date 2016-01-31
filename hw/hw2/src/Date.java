/* Date.java */

import java.io.*;

class Date {

  /* Put your private data fields here. */
	private static final int JAN = 1, FEB = 2, MAR = 3, APR = 4, MAY = 5,
			JUN = 6, JUL = 7, AUG = 8, SEP = 9, OCT = 10, NOV = 11, DEC = 12;
	private int month, day, year;

  /** Constructs a date with the given month, day and year.   If the date is
   *  not valid, the entire program will halt with an error message.
   *  @param month is a month, numbered in the range 1...12.
   *  @param day is between 1 and the number of days in the given month.
   *  @param year is the year in question, with no digits omitted.
   */
  public Date(int month, int day, int year) {
	  this(Integer.toString(month)+"/"+Integer.toString(day)+"/"+Integer.toString(year));
  }

  /** Constructs a Date object corresponding to the given string.
   *  @param s should be a string of the form "month/day/year" where month must
   *  be one or two digits, day must be one or two digits, and year must be
   *  between 1 and 4 digits.  If s does not match these requirements or is not
   *  a valid date, the program halts with an error message.
   */
  public Date(String s) {
	  int slashOne = s.indexOf("/");
	  int slashTwo = s.lastIndexOf("/");
	  int month = Integer.parseInt(s.substring(0,slashOne));
	  int day = Integer.parseInt(s.substring(slashOne+1, slashTwo));
	  int year = Integer.parseInt(s.substring(slashTwo+1));
	  if(Date.isValidDate(month, day, year))
	  {
		  this.month = month;
		  this.day = day;
		  this.year = year;
	  }
	  else
	  {
		  System.out.println("invalid date!");
		  System.exit(0);
	  }
  }

  /** Checks whether the given year is a leap year.
   *  @return true if and only if the input year is a leap year.
   */
  public static boolean isLeapYear(int year) {
    if((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
    {
    	return true;
    }
    return false;
  }

  /** Returns the number of days in a given month.
   *  @param month is a month, numbered in the range 1...12.
   *  @param year is the year in question, with no digits omitted.
   *  @return the number of days in the given month.
   */
  public static int daysInMonth(int month, int year) {
    switch(month){
    case FEB:
    	if(Date.isLeapYear(year))
    		return 29;
    	else
    		return 28;
    case APR: case JUN: case SEP: case NOV:
    	return 30;
    case JAN: case MAR: case MAY: case JUL: case AUG: case OCT: case DEC:
    	return 31;
    default:
        //System.out.println("invalid month!");
    	return 0;
    }
  }

  /** Checks whether the given date is valid.
   *  @return true if and only if month/day/year constitute a valid date.
   *
   *  Years prior to A.D. 1 are NOT valid.
   */
  public static boolean isValidDate(int month, int day, int year) {
	  if(year < 1)
	  {
		  //System.out.println("invalid year!");
		  return false;
	  }
	  if((day < 1) || (day > Date.daysInMonth(month, year)))
	  {
		  //System.out.println("invalid date!");
		  return false;
	  }
	  return true;
  }

  /** Returns a string representation of this date in the form month/day/year.
   *  The month, day, and year are expressed in full as integers; for example,
   *  12/7/2006 or 3/21/407.
   *  @return a String representation of this date.
   */
  public String toString() {
    String date = new String(String.valueOf(this.month) + "/"
                             + String.valueOf(this.day) + "/"
                             + String.valueOf(this.year));
    return date;
  }

  /** Determines whether this Date is before the Date d.
   *  @return true if and only if this Date is before d. 
   */
  public boolean isBefore(Date d) {  // unnecessary use this. , non-static method know field!!!
	  if(this.year < d.year)
		  return true;
	  else if(this.year > d.year)
		  return false;
	  else
	  {
		  if(this.month < d.month)
			  return true;
		  else if(this.month > d.month)
			  return false;
		  else
		  {
			  if(this.day < d.day)
				  return true;
			  else
				  return false;
		  }
	  }
  }

  /** Determines whether this Date is after the Date d.
   *  @return true if and only if this Date is after d. 
   */
  public boolean isAfter(Date d) {
    if(!this.isBefore(d) && !this.equals(d))
    	return true;
    else
    	return false;
  }

  /** Returns the number of this Date in the year.
   *  @return a number n in the range 1...366, inclusive, such that this Date
   *  is the nth day of its year.  (366 is used only for December 31 in a leap
   *  year.)
   */
    public int dayInYear() {  // unnecessary use this. , non-static method know field!!!
    int numofday = 0;
    for(int i=JAN; i<=this.month; i++)
    {
    	if(i==this.month)
    	{
    		numofday = numofday + this.day;
    	}
    	else
    	{
    		numofday = numofday + Date.daysInMonth(i, this.year);
    	}
    }
    return numofday;
  }

  /** Determines the difference in days between d and this Date.  For example,
   *  if this Date is 12/15/2012 and d is 12/14/2012, the difference is 1.
   *  If this Date occurs before d, the result is negative.
   *  @return the difference in days between d and this date.
   */
  public int difference(Date d) {
    int diff = 0;
    int before = 1;
    Date max, min;
    if(this.isBefore(d))
    {
    	before = -1;
    	max = d;
    	min = this;
    }
    else
    {
    	max = this;
    	min = d;
    }
    if(max.year == min.year)
    {
    	diff = (max.dayInYear() - min.dayInYear()) * before;
    }
    else
    {
    	int diffYear = min.year + 1;
    	while(diffYear < max.year)
    	{
    		if(Date.isLeapYear(diffYear))
    		{
    			diff = diff + 366;
    		}
    		else
    			diff = diff + 365;
    		diffYear++;
    	}
    	if(Date.isLeapYear(min.year))
    	{
    		diff = diff + (366 - min.dayInYear());
    	}
    	else
    		diff = diff + (365 - min.dayInYear());
    	diff = (diff + max.dayInYear()) * before;
    }
	  return diff;
  }

  public static void main(String[] argv) {
    System.out.println("\nTesting constructors.");
    Date d1 = new Date(1, 1, 1);
    System.out.println("Date should be 1/1/1: " + d1);
    d1 = new Date("2/4/2");
    System.out.println("Date should be 2/4/2: " + d1);
    d1 = new Date("2/29/2000");
    System.out.println("Date should be 2/29/2000: " + d1);
    d1 = new Date("2/29/1904");
    System.out.println("Date should be 2/29/1904: " + d1);

    d1 = new Date(12, 31, 1975);
    System.out.println("Date should be 12/31/1975: " + d1);
    Date d2 = new Date("1/1/1976");
    System.out.println("Date should be 1/1/1976: " + d2);
    Date d3 = new Date("1/2/1976");
    System.out.println("Date should be 1/2/1976: " + d3);

    Date d4 = new Date("2/27/1977");
    Date d5 = new Date("8/31/2110");

    /* I recommend you write code to test the isLeapYear function! */
    System.out.println("\nTesting leap year.");
    System.out.println("1900 is leap year: " + Date.isLeapYear(2110));
    System.out.println("1904 is leap year: " + Date.isLeapYear(1904));
    System.out.println("1976 is leap year: " + Date.isLeapYear(1976));
    System.out.println("2000 is leap year: " + Date.isLeapYear(2000));
    

    System.out.println("\nTesting before and after.");
    System.out.println(d2 + " after " + d1 + " should be true: " + 
                       d2.isAfter(d1));
    System.out.println(d3 + " after " + d2 + " should be true: " + 
                       d3.isAfter(d2));
    System.out.println(d1 + " after " + d1 + " should be false: " + 
                       d1.isAfter(d1));
    System.out.println(d1 + " after " + d2 + " should be false: " + 
                       d1.isAfter(d2));
    System.out.println(d2 + " after " + d3 + " should be false: " + 
                       d2.isAfter(d3));

    System.out.println(d1 + " before " + d2 + " should be true: " + 
                       d1.isBefore(d2));
    System.out.println(d2 + " before " + d3 + " should be true: " + 
                       d2.isBefore(d3));
    System.out.println(d1 + " before " + d1 + " should be false: " + 
                       d1.isBefore(d1));
    System.out.println(d2 + " before " + d1 + " should be false: " + 
                       d2.isBefore(d1));
    System.out.println(d3 + " before " + d2 + " should be false: " + 
                       d3.isBefore(d2));
    
    System.out.println("\nTesting the number of Date in the year.");
    System.out.println(d1 + " is " + d1.dayInYear() + " days in the year.");
//    System.out.println(Date.daysInMonth(JAN, d1.year));
//    System.out.println(Date.daysInMonth(FEB, d1.year));
//    System.out.println(Date.daysInMonth(MAR, d1.year));
//    System.out.println(Date.daysInMonth(APR, d1.year));
//    System.out.println(Date.daysInMonth(MAY, d1.year));
//    System.out.println(Date.daysInMonth(JUN, d1.year));
//    System.out.println(Date.daysInMonth(JUL, d1.year));
//    System.out.println(Date.daysInMonth(AUG, d1.year));
//    System.out.println(Date.daysInMonth(SEP, d1.year));
//    System.out.println(Date.daysInMonth(OCT, d1.year));
//    System.out.println(Date.daysInMonth(NOV, d1.year));
//    System.out.println(Date.daysInMonth(DEC, d1.year));
    System.out.println(d2 + " is " + d2.dayInYear() + " days in the year.");
    System.out.println(d3 + " is " + d3.dayInYear() + " days in the year.");
    System.out.println(d4 + " is " + d4.dayInYear() + " days in the year.");
    System.out.println(d5 + " is " + d5.dayInYear() + " days in the year.");

    System.out.println("\nTesting difference.");
    System.out.println(d1 + " - " + d1  + " should be 0: " + 
                       d1.difference(d1));
    System.out.println(d2 + " - " + d1  + " should be 1: " + 
                       d2.difference(d1));
    System.out.println(d3 + " - " + d1  + " should be 2: " + 
                       d3.difference(d1));
    System.out.println(d3 + " - " + d4  + " should be -422: " + 
                       d3.difference(d4));
    System.out.println(d5 + " - " + d4  + " should be 48762: " + 
                       d5.difference(d4));
  }
}