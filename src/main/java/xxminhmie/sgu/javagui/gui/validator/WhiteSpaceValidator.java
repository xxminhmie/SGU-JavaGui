package xxminhmie.sgu.javagui.gui.validator;

public class WhiteSpaceValidator {
/*
 * https://stackoverflow.com/questions/2932392/java-how-to-replace-2-or-more-spaces-with-single-space-in-string-and-delete-lead
 */
	public static String validate(String str) {
		return str.trim().replaceAll(" +", " ");
		}

}
