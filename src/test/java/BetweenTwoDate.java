import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BetweenTwoDate {
	public static void main(String[] args) {
		String dateBeforeString = "2017-05-24";
		String dateAfterString = "2017-04-29";

		// Parsing the date
		LocalDate dateBefore = LocalDate.parse(dateBeforeString);
		LocalDate dateAfter = LocalDate.parse(dateAfterString);

		// calculating number of days in between
		long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);

		// displaying the number of days
		System.out.println(noOfDaysBetween);
	}
	
	public static long betweenTwoDate(String start,String end) {
		LocalDate dateBefore = LocalDate.parse(start);
		LocalDate dateAfter = LocalDate.parse(end);
		
		long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
		return noOfDaysBetween;
	}
}
