package comp3111.covid.util;

import java.text.SimpleDateFormat;
import java.util.*;
import java.text.SimpleDateFormat;
/**
 * Parse and validate the period of input, and convert to standard format
 */
public class PeriodParser {
	/**
	 * Parse the input of the user and convert to a {@link comp3111.covid.model.Period} object
	 * @param s		User input of the starting and ending date
	 * @return		A {@link comp3111.covid.model.Period} object on the input date
	 * Notice: this method is to be modified
	 */
	public static Date[] transformToPeriod(String s) {
		int fromIndex=0,toIndex=0;
		for(int i=3;i<s.length();i++) {
			if(s.charAt(i)=='m' && s.charAt(i-1)=='o' && s.charAt(i-2)=='r' && s.charAt(i-3)=='f')
				fromIndex=i;
			if(s.charAt(i)=='o' && s.charAt(i-1)=='t')
				toIndex=i;
		}
		if(fromIndex==0 || toIndex==0)
			return null;
		String s1=s.substring(fromIndex+2,toIndex-2);
		String s2=s.substring(toIndex+2);
		Date res[]=new Date[2];
		res[0]=DateParser.transformToDate(s1);
		res[1]=DateParser.transformToDate(s2);
		return res;
	}

	/**
	 * Parse the input of the user and convert starting and ending dates to the standard form of the dataset
	 * @param s		User input of the date
	 * @return		Array of String consisting the starting and ending date in standard form of the dataset
	 */
	public static String[] transformToPeriodString(String s) {
		  Date[] date=new Date[2];
		  date=transformToPeriod(s);
		  if(date==null)
			  return null;
		  String[] res=new String[2];
		  SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
		  res[0] = sdf.format(date[0]);
		  res[1] = sdf.format(date[1]);
		  return res;
		  
	}
	
	/**
	 * Validate the user input to check whether both input dates are in the correct format
	 * @param s			User input of the date
	 * @return			a boolean variable indicate whether the input dates are in correct format
	 */
	public static boolean isValidDateInPeriod(String s) {
		int fromIndex=0,toIndex=0;
		for(int i=3;i<s.length();i++) {
			if(s.charAt(i)=='m' && s.charAt(i-1)=='o' && s.charAt(i-2)=='r' && s.charAt(i-3)=='f')
				fromIndex=i;
			if(s.charAt(i)=='o' && s.charAt(i-1)=='t')
				toIndex=i;
		}
		if(fromIndex!=3 || (toIndex!=18 && toIndex!=19))
			return false;
		String s1=s.substring(fromIndex+2,toIndex-2);
		String s2=s.substring(toIndex+2);
		return (DateParser.isValidDate(s1)&&DateParser.isValidDate(s2));
	}
	
	/**
	 * Validate the user input to check whether both input dates are within legal range
	 * @param s			User input of the date
	 * @return			a boolean variable indicate whether the input dates are within legal range
	 */
	public static boolean isDateWithinRangeInPeriod(String s) {
		if(isValidDateInPeriod(s)) {
			int fromIndex=0,toIndex=0;
			for(int i=3;i<s.length();i++) {
				if(s.charAt(i)=='m' && s.charAt(i-1)=='o' && s.charAt(i-2)=='r' && s.charAt(i-3)=='f')
					fromIndex=i;
				if(s.charAt(i)=='o' && s.charAt(i-1)=='t')
					toIndex=i;
			}
			String s1=s.substring(fromIndex+2,toIndex-2);
			String s2=s.substring(toIndex+2);
			return (DateParser.isDateWithinRange(s1)&&DateParser.isDateWithinRange(s2));
		}
		else
			return false;
		
	}
	
	/**
	 * Validate the user input to check whether the starting date is no later than ending date
	 * @param s			User input of the date
	 * @return			a boolean variable indicating whether the starting date is no later than ending date
	 */
	public static boolean isStartBeforeEnd(String s) {
		if(isDateWithinRangeInPeriod(s)) {
			Date date[]=transformToPeriod(s);
			return date[0].before(date[1]);
		}
		else
			return false;
	}
}
