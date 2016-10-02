package dynamic.programming;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class QuickSums {

	@Test
	public void test() {

		assertEquals(2, minSums("382834", 100));
		assertEquals(4, minSums("9230560001", 71));

		assertEquals(-1, minSums("99999", 100));
		assertEquals(8, minSums("0123456789", 45));
	}

	
	//1)use brute force - use long instead of integer
	//2)use recusive solution
	public int minSums(final String nums, int sum) {

		String numbers = nums.replace("0", "");
		List<Integer>[] sums = new List[numbers.length()];
		List<Integer>[] weights = new List[numbers.length()];
		sums[0] = new ArrayList<>();
		sums[0].add(new Integer(""+numbers.charAt(0)));
		weights[0] = new ArrayList<>();
		weights[0].add(0);

		for (int i = 1; i < numbers.length(); i++) {
			int num = new Integer(""+numbers.charAt(i));
			sums[i] = sums[i - 1].stream().map(v -> v + num)
					.collect(Collectors.toList());
			weights[i] = weights[i - 1].stream().map(v -> v + 1)
					.collect(Collectors.toList());
			int lastTwoNums = new Integer(""+numbers.charAt(i-1)+numbers.charAt(i));
	
			if (lastTwoNums <= sum) {
				if (i - 2 >= 0) {
					sums[i].addAll(sums[i - 2].stream()
							.map(v -> v + lastTwoNums)
							.collect(Collectors.toList()));
					weights[i].addAll(weights[i - 2].stream().map(v -> v + 1)
							.collect(Collectors.toList()));
				} else {
					sums[i].add(lastTwoNums);
					weights[i].add(0);
				}

			}
		}

		int minWeight = Integer.MAX_VALUE;
		List<Integer> lastSums = sums[numbers.length() - 1];
		List<Integer> lastWeights = weights[numbers.length() - 1];
		for (int i = 0; i < lastSums.size(); i++) {
			if (lastSums.get(i) == sum) {
				if (lastWeights.get(i) < minWeight) {
					minWeight = lastWeights.get(i);
				}
			}
		}
		return minWeight!=Integer.MAX_VALUE?minWeight:-1;
	}

}
