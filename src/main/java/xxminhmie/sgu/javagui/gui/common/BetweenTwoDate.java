package xxminhmie.sgu.javagui.gui.common;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BetweenTwoDate {
	public static long betweenTwoDate(String start,String end) {
		LocalDate dateBefore = LocalDate.parse(start);
		LocalDate dateAfter = LocalDate.parse(end);
		
		long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
		return noOfDaysBetween;
	}

}
