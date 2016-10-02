package problems.strings;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;

public class SerialNumbers {

	@Test
	public void test() {

		assertArrayEquals(
				new String[] { "A", "ABCD", "Z321", "145C", "A910" },
				sortSerials2(new String[] { "ABCD", "145C", "A", "A910", "Z321" }));

		assertArrayEquals(new String[] { "Z20", "Z19" },
				sortSerials2(new String[] { "Z19", "Z20" }));

		assertArrayEquals(new String[] { "FIPJOTEA5", "PYF1J14TF", "34H2BJS6N",
				"PIM12MD7RCOLWW09" }, sortSerials2(new String[] { "34H2BJS6N",
				"PIM12MD7RCOLWW09", "PYF1J14TF", "FIPJOTEA5" }));

		assertArrayEquals(new String[] { "ABCDA", "ABCDE", "ACAAA", "BAAAA",
				"BCDEF" }, sortSerials2(new String[] { "ABCDE", "BCDEF",
				"ABCDA", "BAAAA", "ACAAA" }));
	}

	public String[] sortSerials2(String[] serialNumbers) {
		Arrays.sort(serialNumbers, new Comparator<String>() {

			@Override
			public int compare(String a, String b) {
				if (a.length() != b.length()) {
					return Integer.compare(a.length(), b.length());
				}
				int sumA = a.chars().map(c -> c - '0')
						.filter(c -> c >= 0 && c <= 9).sum();
				int sumB = b.chars().map(c -> c - '0')
						.filter(c -> c >= 0 && c <= 9).sum();
				if (sumA != sumB) {
					return Integer.compare(sumA, sumB);
				}
				return a.compareTo(b);
			}

		});
		return serialNumbers;
	}

	public String[] sortSerials(String[] serialNumbers) {
		String temp = null;
		for (int i = 0; i < serialNumbers.length - 1; i++) {
			for (int j = 0; j < serialNumbers.length - 1; j++) {
				if (compare(serialNumbers[j + 1], serialNumbers[j]) < 0) {
					temp = serialNumbers[j + 1];
					serialNumbers[j + 1] = serialNumbers[j];
					serialNumbers[j] = temp;
				}
			}
		}
		return serialNumbers;
	}

	private int compare(String a, String b) {
		if (a.length() != b.length()) {
			return Integer.compare(a.length(), b.length());
		}
		int sumA = a.chars().map(c -> c - '0').filter(c -> c >= 0 && c <= 9)
				.sum();
		int sumB = b.chars().map(c -> c - '0').filter(c -> c >= 0 && c <= 9)
				.sum();
		if (sumA != sumB) {
			return Integer.compare(sumA, sumB);
		}
		return a.compareTo(b);
	}
}
