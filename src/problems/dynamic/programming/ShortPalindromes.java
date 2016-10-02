package problems.dynamic.programming;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShortPalindromes {

	@Test
	public void test() {
		assertEquals("Q", shortest("Q"));
		assertEquals("ECARACE", shortest("RACE"));
		assertEquals("REDTOCPCOTDER", shortest("TOPCODER"));
		assertEquals("MADAMIMADAM", shortest("MADAMIMADAM"));
		assertEquals("AFLRCAGIOEOUAEOCEGRURGECOEAUOEOIGACRLFA", shortest("ALRCAGOEUAOEURGCOEUOOIGFA"));
	}

	public String shortest(String base) {
		if (isPalidrom(base)) {
			return base;
		}
		if (isStartCharEqualsEndChar(base)) {
			return base.charAt(0) + shortest(cutStartEndChars(base))
					+ base.charAt(base.length() - 1);
		}

		char startChar = base.charAt(0);
		char endChar = base.charAt(base.length() - 1);
		String startStr = startChar + shortest(cutStartChars(base)) + startChar;
		String endStr = endChar + shortest(cutEndChar(base)) + endChar;
		return min(startStr, endStr);
	}

	private String min(String str1, String str2) {
		if (str1.length() == str2.length()) {
			return str1.compareTo(str2) > 0 ? str2 : str1;
		}
		return str1.length() > str2.length() ? str2 : str1;
	}

	private boolean isStartCharEqualsEndChar(String str) {
		if (str.length() == 1) {
			return true;
		}
		return str.charAt(0) == str.charAt(str.length() - 1);
	}

	private String cutStartEndChars(String str) {
		if (str.length() <= 2) {
			return "";
		}
		return str.substring(1, str.length() - 1);
	}

	private String cutStartChars(String str) {
		if (str.length() == 0) {
			return "";
		}
		if (str.length() == 1) {
			return "";
		}
		return str.substring(1, str.length());
	}

	private String cutEndChar(String str) {
		if (str.length() == 0) {
			return "";
		}
		if (str.length() == 1) {
			return "";
		}
		return str.substring(0, str.length() - 1);
	}

	private boolean isPalidrom(String str) {
		if (str.length() == 1) {
			return true;
		}
		int compareIndex = 0;
		if (str.length() % 2 == 0) {
			compareIndex = str.length() / 2;
		} else {
			compareIndex = str.length() / 2 + 1;
		}
		int halfIndex = compareIndex - 1;
		for (int i = 0; i <= halfIndex; i++) {
			if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
				return false;
			}
		}
		return true;

	}

}
