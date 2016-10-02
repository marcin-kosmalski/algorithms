package problems.strings;

import static org.junit.Assert.*;

import org.junit.Test;

public class WolfDelaymaster {

	@Test
	public void test() {
		assertEquals("VALID", check("wolf"));
		assertEquals("INVALID", check("wwolfolf"));
		assertEquals("INVALID", check("flowolf"));
		assertEquals("VALID", check("wolfwwoollffwwwooolllfffwwwwoooollllffff"));

	}

	public String check(String str) {
		int[] t = str.chars().toArray();
		int[] w = "wolf".chars().toArray();
		int lastNum = -1;
		int currNum = 0;
		int letterIndex = 0;
		for (int i = 0; i < t.length; i++) {

			if (t[i] == w[letterIndex]) {
				currNum++;
				if (lastNum > -1 && currNum > lastNum) {
					return "INVALID";
				}
			} else if (t[i] == w[(letterIndex + 1) % 4]) {
				if (lastNum != currNum && lastNum != -1) {
					return "INVALID";
				}
				letterIndex = (letterIndex + 1) % 4;
				if (letterIndex == 0) {
					lastNum = -1;
				} else {
					lastNum = currNum;
				}
				currNum = 1;
			}
		}
		return "VALID";
	}

}
