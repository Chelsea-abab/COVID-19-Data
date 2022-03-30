package comp3111.covid.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Parse and validate the date of input, and convert to the standard format in the dataset
 */
public class DateParser {
	/**
	 * Parse the input of the user and convert to a {@link java.util.Date} object
	 * @param s		User input of the date
	 * @return		A {@link java.util.Date} object on the input date
	 */
	public static Date transformToDate(String s) {
		Date date=null;
		String year="",month="",day="";
		int commaIndex=0;
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)==',')
				commaIndex=i;
		}
		if(commaIndex!=5 && commaIndex!=6)
			return date;
		if(s.charAt(commaIndex+5)=='1') {
			year="2021";
		}
		else if(s.charAt(commaIndex+5)=='0') {
			year="2020";
		}
		if(s.charAt(0)=='J' && s.charAt(1)=='a' && s.charAt(2)=='n') {
			month="01";
		}
		if(s.charAt(0)=='F' && s.charAt(1)=='e' && s.charAt(2)=='b') {
			month="02";
		}
		if(s.charAt(0)=='M' && s.charAt(1)=='a' && s.charAt(2)=='r') {
			month="03";
		}
		if(s.charAt(0)=='A' && s.charAt(1)=='p' && s.charAt(2)=='r') {
			month="04";
		}
		if(s.charAt(0)=='M' && s.charAt(1)=='a' && s.charAt(2)=='y') {
			month="05";
		}
		if(s.charAt(0)=='J' && s.charAt(1)=='u' && s.charAt(2)=='n') {
			month="06";
		}
		if(s.charAt(0)=='J' && s.charAt(1)=='u' && s.charAt(2)=='l') {
			month="07";
		}
		if(s.charAt(0)=='A' && s.charAt(1)=='u' && s.charAt(2)=='g') {
			month="08";
		}
		if(s.charAt(0)=='S' && s.charAt(1)=='e' && s.charAt(2)=='p') {
			month="09";
		}
		if(s.charAt(0)=='O' && s.charAt(1)=='c' && s.charAt(2)=='t') {
			month="10";
		}
		if(s.charAt(0)=='N' && s.charAt(1)=='o' && s.charAt(2)=='v') {
			month="11";
		}
		if(s.charAt(0)=='D' && s.charAt(1)=='e' && s.charAt(2)=='c') {
			month="12";
		}
		day=s.substring(4,commaIndex);
		if(year=="" || month=="" || day=="")
			return date;
		String result=year+"-"+month+"-"+day;
		//System.out.println("3921???");
		//System.out.println(result);
		//java.text.SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd ");
		//System.out.println(Integer.parseInt(year));
		//System.out.println(Integer.parseInt(month));
		//System.out.println(Integer.parseInt(day));
		date = new Date(Integer.parseInt(year) - 1900, Integer.parseInt(month) - 1, Integer.parseInt(day));
		//System.out.println(date);
		/*try {
			date = formatter.parse(result);
		} catch (ParseException e) {
			System.out.println("transformToDate ERROR!");

		}*/
		return date;
	}
	/**
	 * Validate the user input to check whether the input is in the correct format
	 * @param s			User input of the date
	 * @return			a boolean variable indicate whether the input is in correct format
	 */
	public static boolean isValidDate(String s) {
		if(s.length()!=11 && s.length()!=12)
			return false;
		//check month
		boolean validMonth=false;
		if((s.charAt(0)=='J' && s.charAt(1)=='a' && s.charAt(2)=='n')||(s.charAt(0)=='F' && s.charAt(1)=='e' && s.charAt(2)=='b')||
		   (s.charAt(0)=='M' && s.charAt(1)=='a' && s.charAt(2)=='r')||(s.charAt(0)=='A' && s.charAt(1)=='p' && s.charAt(2)=='r')||
		   (s.charAt(0)=='M' && s.charAt(1)=='a' && s.charAt(2)=='y')||(s.charAt(0)=='J' && s.charAt(1)=='u' && s.charAt(2)=='n')||
		   (s.charAt(0)=='J' && s.charAt(1)=='u' && s.charAt(2)=='l')||(s.charAt(0)=='A' && s.charAt(1)=='u' && s.charAt(2)=='g')||
		   (s.charAt(0)=='S' && s.charAt(1)=='e' && s.charAt(2)=='p')||(s.charAt(0)=='O' && s.charAt(1)=='c' && s.charAt(2)=='t')||
		   (s.charAt(0)=='N' && s.charAt(1)=='o' && s.charAt(2)=='v')||(s.charAt(0)=='D' && s.charAt(1)=='e' && s.charAt(2)=='c')) {
			if(s.charAt(3)==' ') {
				validMonth=true;
			}
		}
		if(validMonth==false)
			return false;
		//check day number
		boolean validDay=false;
		int commaIndex=0;
		int commaCount=0;
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)==',') {
				commaIndex=i;
				commaCount++;
			}
		}
		if(commaCount!=1)
			return false;
		if(commaIndex!=5 && commaIndex!=6)
			return false;
		if(commaIndex==6) {
			if(s.charAt(4)>='0' && s.charAt(4)<='9' && s.charAt(5)>='0' && s.charAt(5)<='9') {
				if(s.charAt(7)==' ')
					validDay=true;
			}
		}
		else if(commaIndex==5) {
			if(s.charAt(4)>='0' && s.charAt(4)<='9') {
				if(s.charAt(6)==' ')
					validDay=true;
			}
		}
		//check year
		boolean validYear=false;
		if(s.charAt(commaIndex+2)=='2' && s.charAt(commaIndex+3)=='0' && s.charAt(commaIndex+4)=='2' && (s.charAt(commaIndex+5)=='0' || s.charAt(commaIndex+5)=='1')) {
			validYear=true;
		}
		if(validYear && validMonth && validDay)
			return true;
		else
			return false;
	}
	
	/**
	 * Validate the user input to check whether the input is within legal range
	 * @param s			User input of the date
	 * @return			a boolean variable indicate whether the input is within legal range
	 */
	public static boolean isDateWithinRange(String s) {//true==in range     false==out of range
		if(isValidDate(s)) {
			int commaIndex=0;
			for(int i=0;i<s.length();i++) {
				if(s.charAt(i)==',')
					commaIndex=i;
			}
			String day=s.substring(4,commaIndex);
			int dayNum=Integer.valueOf(day).intValue();
			boolean isDayInRange=false;
			if(s.charAt(0)=='J' && s.charAt(1)=='a' && s.charAt(2)=='n') {
				if (s.charAt(commaIndex + 5) == '1' && dayNum >= 1 && dayNum <= 31)
					isDayInRange=true;
			}
			if(s.charAt(commaIndex + 5)=='1' && s.charAt(0)=='F' && s.charAt(1)=='e' && s.charAt(2)=='b') {
				if(dayNum>=1 && dayNum<=28)
					isDayInRange=true;
			}
			if(s.charAt(0)=='M' && s.charAt(1)=='a' && s.charAt(2)=='r') {
				if(dayNum>=1 && dayNum<=31)
					isDayInRange=true;
			}
			if(s.charAt(0)=='A' && s.charAt(1)=='p' && s.charAt(2)=='r') {
				if(dayNum>=1 && dayNum<=30)
					isDayInRange=true;
			}
			if(s.charAt(0)=='M' && s.charAt(1)=='a' && s.charAt(2)=='y') {
				if(dayNum>=1 && dayNum<=31)
					isDayInRange=true;
			}
			if(s.charAt(0)=='J' && s.charAt(1)=='u' && s.charAt(2)=='n') {
				if(dayNum>=1 && dayNum<=30)
					isDayInRange=true;
			}
			if(s.charAt(0)=='J' && s.charAt(1)=='u' && s.charAt(2)=='l') {
				if((s.charAt(commaIndex + 5) == '1' && dayNum >= 1 && dayNum <= 20) ||
						(s.charAt(commaIndex + 5) == '0' && dayNum >= 1 && dayNum <= 31))
					isDayInRange=true;
			}
			if(s.charAt(0)=='A' && s.charAt(1)=='u' && s.charAt(2)=='g') {
				if(s.charAt(commaIndex + 5) == '0' && dayNum>=1 && dayNum<=31)
					isDayInRange=true;
			}
			if(s.charAt(0)=='S' && s.charAt(1)=='e' && s.charAt(2)=='p') {
				if(s.charAt(commaIndex + 5) == '0' && dayNum>=1 && dayNum<=30)
					isDayInRange=true;
			}
			if(s.charAt(0)=='O' && s.charAt(1)=='c' && s.charAt(2)=='t') {
				if(s.charAt(commaIndex + 5) == '0' && dayNum>=1 && dayNum<=31)
					isDayInRange=true;
			}
			if(s.charAt(0)=='N' && s.charAt(1)=='o' && s.charAt(2)=='v') {
				if(s.charAt(commaIndex + 5) == '0' && dayNum>=1 && dayNum<=30)
					isDayInRange=true;
			}
			if(s.charAt(0)=='D' && s.charAt(1)=='e' && s.charAt(2)=='c') {
				if(s.charAt(commaIndex + 5) == '0' && dayNum>=1 && dayNum<=31)
					isDayInRange=true;
			}
			if(isDayInRange)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	/**
	 * Parse the input of the user and convert to the standard form of the dataset
	 * @param s		User input of the date
	 * @return		A String of the date in standard form of the dataset
	 */
	public static String transformToDateString(String s) {
		  Date date=transformToDate(s);
		  if(date==null)
			  return null;
		  //System.out.println("start");
		  //System.out.println(s);
		  //System.out.println(date.toString());
		  String res;
		  SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
		  res = sdf.format(date);
		  //System.out.println(res);
		  return res;
	}
}
