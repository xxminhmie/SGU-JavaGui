import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertDate {
	public static void main(String[] args) {
		Date date = new Date(); // This object contains the current date value
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(formatter.format(date));
		
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        System.out.print(sqlDate);


	}
}
