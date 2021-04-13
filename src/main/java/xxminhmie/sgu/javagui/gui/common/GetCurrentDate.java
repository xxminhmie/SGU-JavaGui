package xxminhmie.sgu.javagui.gui.common;


public class GetCurrentDate {
	public static java.sql.Date getDate() {
		java.util.Date date = new java.util.Date(); // This object contains the current date value
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");		
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;

	}

}
