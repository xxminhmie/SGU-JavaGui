package xxminhmie.sgu.javagui.gui.common;

import java.text.DecimalFormat;

public class MoneyFormat {

	static public String customFormat(double value) {
		String pattern = "###,###.###";
		DecimalFormat myFormatter = new DecimalFormat(pattern);
		String output = myFormatter.format(value);
		return output;
//		System.out.println(value + "  " + pattern + "  " + output);
	}
	
	static public void main(String[] args) {

		String value = "100,000";

		customFormat(Double.parseDouble(value));

	}
}
