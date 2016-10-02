package problems.dynamic.programming;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class QuickSums {

	@Test
	public void test() {

		assertEquals(2, minSumsDP("382834", 100));
		assertEquals(4, minSumsDP("9230560001", 71));

		assertEquals(-1, minSumsDP("99999", 100));
		assertEquals(8, minSumsDP("0123456789", 45));

		assertEquals(2, minSumsBruteForce("382834", 100));
		assertEquals(4, minSumsBruteForce("9230560001", 71));
		assertEquals(-1, minSumsBruteForce("99999", 100));
		assertEquals(8, minSumsBruteForce("0123456789", 45));
	}
	
	// 1)use brute force - use long instead of integer-DONE
	// 2)use recusive solution
	public int minSumsBruteForce(final String numbers, int sum) {

		String nums = numbers.replaceAll("0", "");

		String[] n = nums.chars().mapToObj(v -> (char) v + "")
				.toArray(String[]::new);

		int minSum = Integer.MAX_VALUE;
		if (sum(n) == sum) {
			minSum = nums.length() - 1;
		}

		for (int startMergingFromIndex = 0; startMergingFromIndex < n.length; startMergingFromIndex += 1) {
			int i = startMergingFromIndex;
			String[] tempNums = Arrays.copyOf(n, n.length);
			while (i + 1 <= tempNums.length) {
				tempNums = merge(tempNums, i);
				if (sum(tempNums) == sum) {
					if (tempNums.length - 1 < minSum) {
						minSum = tempNums.length - 1;
					}
				}
				i++;
			}
		}
		return minSum==Integer.MAX_VALUE?-1:minSum;
	}
	

	private String[] merge(String[] nums, int index) {
		String[] newNums = new String[nums.length - 1];
		boolean skipOne = false;
		for (int i = 0; i < newNums.length; i++) {
			if (i == index + 1) {
				skipOne = true;
			}
			if (i == index) {
				newNums[i] = nums[i] + nums[i + 1];
			} else {
				newNums[i] = nums[i + (skipOne ? 1 : 0)];
			}
		}

		return newNums;
	}

	private long sum(String[] nums) {
		return Arrays.stream(nums).mapToLong(v -> Long.valueOf(v)).sum();
	}

	private void plusVal(int[] sums,int val){
		for(int i=0;i<sums.length;i++){
			sums[i]=sums[i]+val;
		}
	}

	public int minSumsDP(final String nums, int sum) {

		String numbers = nums.replace("0", "");
		List<Integer>[] sums = new List[numbers.length()];
		List<Integer>[] weights = new List[numbers.length()];
		sums[0] = new ArrayList<>();
		sums[0].add(new Integer("" + numbers.charAt(0)));
		weights[0] = new ArrayList<>();
		weights[0].add(0);

		for (int i = 1; i < numbers.length(); i++) {
			int num = new Integer("" + numbers.charAt(i));
			sums[i] = sums[i - 1].stream().map(v -> v + num)
					.collect(Collectors.toList());
			weights[i] = weights[i - 1].stream().map(v -> v + 1)
					.collect(Collectors.toList());
			int lastTwoNums = new Integer("" + numbers.charAt(i - 1)
					+ numbers.charAt(i));

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
		return minWeight != Integer.MAX_VALUE ? minWeight : -1;
	}

}
