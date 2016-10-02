package problems.strings;

import static org.junit.Assert.*;

import org.junit.Test;

public class TemplateMatching {

	@Test
	public void test() {
		assertEquals("something",
				bestMatch("something", "awesome", "ingenious"));
		assertEquals("a", bestMatch("havka", "eto", "papstvo"));
		assertEquals("a a", bestMatch("a a a a a a", "a a", "a"));
		assertEquals("abrac", bestMatch("abracadabra", "habrahabr", "bracket"));
		assertEquals("ippi", bestMatch("mississippi", "promise", "piccolo"));
		
		
		// assertEquals("bu",findMaxCommonString("arbuz", "bulsjfd"));
		// assertEquals("testtesttest",findMaxCommonString("testmmtesttestmmmtesttesttestkk",
		// "testtesttest"));
		// assertEquals(true, findString("sljfasddlfkja", "fas"));
		// (false, findString("sljfasddlfkja", "lfsj"));
		// assertEquals(true, findString("abcde", "cde"));
	}

	public String findMaxCommonString(String str1, String str2) {
		String cw = "";
		for (int i = 0; i < str1.length(); i++) {
			for (int j = i + 1; j <= str1.length(); j++) {
				String subString = str1.substring(i, j);
				if (findString(str2, subString)
						&& subString.length() > cw.length()) {
					cw = subString;
				}
			}
		}
		return cw;
	}

	private boolean findString(String str, String token) {
		int maxLen = str.length() - token.length();
		for (int i = 0; i <= maxLen; i++) {
			String subStr = str.substring(i, i + token.length());
			if (subStr.equals(token)) {
				return true;
			}
		}
		return false;
	}

	public String bestMatch(String text, String prefix, String suffix) {
		int n = text.length();

		int mps = -1;
		int mp = -1;
		String ans = "hi, Egor!";
		// generate all substrings
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j <= n; j++) {
				// t=substring
				String t = text.substring(i, j);
				// System.out.println(t);
				// 1)in case text substring is shorter than prefix; we need to
				// find the common part of prefix and substring
				// 2)at the beginning we assume the longest common part of
				// prefix
				// and text substring
				int p = Math.min(t.length(), prefix.length());
				// 1)find index of position where substring at the end of prefix
				// is equal to substring at the beginning of text substring
				// in other words: find the position of common part of prefix
				// and text substring

				// p>0 because p can't be negative (as it is the index)
				while (p > 0
						&& !prefix.substring(prefix.length() - p).equals(
								t.substring(0, p)))
					p--;

				int s = Math.min(t.length(), suffix.length());
				while (s > 0
						&& !suffix.substring(0, s).equals(
								t.substring(t.length() - s)))
					s--;
				// suffix and prefix can have zero length
				// text substring with the highest matching point wins
				if (p + s > mps) {
					mps = p + s;
					mp = p;
					ans = t;
					// if the matching point is the same then select the one
					// which has higher prefix matching point
				} else if (p + s == mps && p > mp) {
					mp = p;
					ans = t;
					// this is rather for cases where prefix&suffix are empty
					// and the result is with one letter from text which is the
					// lowest lexicographically
				} else if (p + s == mps && p == mp && ans.compareTo(t) > 0) {
					ans = t;
				}
			}
		}
		return ans;
	}

}
