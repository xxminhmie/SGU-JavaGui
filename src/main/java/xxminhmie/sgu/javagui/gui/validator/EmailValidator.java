package xxminhmie.sgu.javagui.gui.validator;

import java.util.regex.Pattern;
/*
 * https://stackoverflow.com/questions/42266148/email-validation-regex-java
 */
public class EmailValidator {
	private Pattern pattern;
	private static final String EMAIL_PATTERN =  "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
	
	public EmailValidator() {
		pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
	}
	public boolean validate(final String email) {
		return pattern.matcher(email).matches();
		
	}
}
