package xxminhmie.sgu.javagui.gui.validator;

import java.util.regex.Pattern;

/*
 * https://www.regextester.com/98578
 */
public class PhoneValidator {
	private Pattern pattern;
	private static final String PHONE_PATTERN = "(09|01[2|6|8|9])+([0-9]{8})\\b";
	public PhoneValidator() {
		pattern = Pattern.compile(PHONE_PATTERN, Pattern.CASE_INSENSITIVE);
	}
	public boolean validate(final String phone) {
		return pattern.matcher(phone).matches();
	}
}
