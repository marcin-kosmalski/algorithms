package training.strings;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class SearchInText {

	@Test
	public void test() {
		assertEquals(true, bruteForce("ammbbk", "mmbb"));
		assertEquals(true, bruteForce("a;lkdsjfmmbblfjsd", "mmbb"));

		assertEquals(true, rabinKarp("mmbbmmmk", "mmbb"));
		assertEquals(true, rabinKarp("ammbbk", "mmbb"));
		assertEquals(true, rabinKarp("a;lkdsjfmmbblfjsd", "mmbb"));

		assertEquals(true, knuthMorrisPratt("mmbbmmmk", "mmbb"));
		assertEquals(true, knuthMorrisPratt("ammbbk", "mmbb"));
		assertEquals(true, knuthMorrisPratt("a;lkdsjfmmbblfjsd", "mmbb"));

	}

	public boolean knuthMorrisPratt(String text, String pattern) {

		int[] t = text.chars().toArray();
		int[] p = pattern.chars().toArray();
		// let n be the size of the text, m the
		// size of the pattern, and F[] - the
		// "failure function"

		int[] f = failureFunction(pattern.chars().toArray());

		int i = 0; // the initial state of the automaton is
					// the empty string

		int j = 0; // the first character of the text

		for (;;) {
			if (j == text.length())
				break; // we reached the end of the text

			// if the current character of the text "expands" the
			// current match
			if (t[j] == p[i]) {
				i++; // change the state of the automaton
				j++; // get the next character from the text
				if (i == p.length) {
					return true;
				}
			}

			// if the current state is not zero (we have not
			// reached the empty string yet) we try to
			// "expand" the next best (largest) match
			else if (i > 0)
				i = f[i];

			// if we reached the empty string and failed to
			// "expand" even it; we go to the next
			// character from the text, the state of the
			// automaton remains zero
			else
				j++;
		}
		return true;
	}

	private int[] failureFunction(int[] pattern) {
		int[] f = new int[pattern.length+1];
		f[0] = f[1] = 0;
		for (int i = 2; i <= pattern.length; i++) {
			// j is the index of the largest next partial match
			// (the largest suffix/prefix) of the string under
			// index i - 1
			int j = f[i - 1];
			for (;;) {
				// check to see if the last character of string i -
				// - pattern[i - 1] "expands" the current "candidate"
				// best partial match - the prefix under index j
				if (pattern[j] == pattern[i - 1]) {
					f[i] = j + 1;
					break;
				}
				// if we cannot "expand" even the empty string
				if (j == 0) {
					f[i] = 0;
					break;
				}
				// else go to the next best "candidate" partial match
				j = f[j];
			}
		}
		return f;
	}

	public boolean rabinKarp(String text, String pattern) {

		int M = 8893;// prime number
		int B = 'z' - 'a' + 1;

		int[] tArr = text.chars().toArray();
		int[] pArr = pattern.chars().toArray();

		if (tArr.length < pArr.length) {
			return false;
		}

		int hp = 0;
		for (int i = 0; i < pArr.length; i++) {
			hp = calcMod(hp * B + pArr[i], M);
		}

		int ht = 0;
		for (int i = 0; i < pArr.length; i++) {
			ht = calcMod(ht * B + tArr[i], M);
		}

		if (ht == hp
				&& Arrays
						.equals(Arrays.copyOfRange(tArr, 0, pArr.length), pArr)) {
			return true;
		}

		int E = (int) (Math.pow(B, pArr.length - 1) % M);
		for (int i = pArr.length; i < tArr.length; i++) {
			ht = calcMod(ht - calcMod(tArr[i - pArr.length] * E, M), M);
			ht = calcMod(ht * B, M);
			ht = calcMod(ht + tArr[i], M);
			if (ht == hp
					&& Arrays.equals(Arrays.copyOfRange(tArr, i - pArr.length
							+ 1, i + 1), pArr)) {
				return true;
			}
		}
		return false;
	}

	private int calcMod(int a, int b) {
		return (a % b + b) % b;
	}

	public boolean bruteForce(String text, String pattern) {
		int[] tArr = text.chars().toArray();
		int[] pArr = pattern.chars().toArray();

		for (int i = 0; i < tArr.length; i++) {
			int j = 0;
			for (j = 0; j < pArr.length && i + j < tArr.length; j++) {
				if (tArr[i + j] != (pArr[j])) {
					break;
				}
			}
			if (j == pArr.length) {
				return true;
			}
		}
		return false;
	}

}
