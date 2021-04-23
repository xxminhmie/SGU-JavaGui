package xxminhmie.sgu.javagui.gui.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GetCurrentDate {
	public static java.sql.Date getDate() {
		java.util.Date date = new java.util.Date(); // This object contains the current date value
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");		
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;

	}
	public static String getCurrentDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);

	}


}
