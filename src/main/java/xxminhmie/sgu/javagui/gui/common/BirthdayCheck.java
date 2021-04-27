package xxminhmie.sgu.javagui.gui.common;

import java.util.Calendar;

public class BirthdayCheck {
	
	public static int checkBirthday(java.sql.Date birthday) {
		
		int birthdayYear = getYear(String.valueOf(birthday));
		int currYear = getYear(String.valueOf(GetCurrentDate.getDate()));
		
		int age = currYear - birthdayYear;
		return age;
	}
	public static int getYear(String date) {
		java.sql.Date dat = java.sql.Date.valueOf(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dat);
		
		int year = cal.get(Calendar.YEAR);
		return year;
	}
	public static int getMonth(String date) {
		java.sql.Date dat = java.sql.Date.valueOf(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dat);
		
		int month = cal.get(Calendar.MONTH);
		return month+1;
	}

}
