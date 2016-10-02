package problems.sorting;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TheEncryptionDivTwo {

	@Test
	public void test() {
		assertEquals("abccd", encrypt("hello"));
		assertEquals("abcd", encrypt("abcd"));
		assertEquals("abcdbefg", encrypt("topcoder"));
		assertEquals("abcdefghib", encrypt("encryption"));
	}

	public String encrypt(String message) {
		Map<String, String> map = new HashMap<String, String>();
		String[] msg = message.chars().mapToObj(c -> (char) c + "")
				.toArray(String[]::new);
		String newMsg = "";
		char nextChar = 'a';
		for (int i = 0; i < msg.length; i++) {
			if (map.containsKey(msg[i])) {
				newMsg += map.get(msg[i]);
			} else {
				newMsg += nextChar + "";
				map.put(msg[i], nextChar + "");
				nextChar++;
			}
		}
		return newMsg;
	}
}
