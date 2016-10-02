package dynamic.programming;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class Coins {

	@Test
	public void test() {
		assertEquals(3, findMinCoinsNumForSum(new int[] { 1, 2, 5 }, 8));
		assertEquals(5, findMinCoinsNumForSum(new int[] { 1, 2, 5 }, 21));
		assertEquals(3, findMinCoinsNumForSum2(new int[] { 1, 2, 5 }, 8));
		assertEquals(5, findMinCoinsNumForSum2(new int[] { 1, 2, 5 }, 21));
	}

	private int findMinCoinsNumForSum(int[] coins, int sum) {
		Map<Integer, Integer> sumToCoinsNum = new HashMap<>();
		sumToCoinsNum.put(0, 0);
		for (int sumIter = 1; sumIter <= sum; sumIter++) {
			int coinsMin = Integer.MAX_VALUE;
			for (int coinIter = 0; coinIter < coins.length; coinIter++) {
				int coinValue = coins[coinIter];
				if (coinValue <= sumIter) {
					int prevCoinsNum = sumToCoinsNum.get(sumIter - coinValue);
					int potentialCoinsMin = prevCoinsNum + 1;
					if (potentialCoinsMin < coinsMin) {
						coinsMin = potentialCoinsMin;
					}
				}
			}
			sumToCoinsNum.put(sumIter, coinsMin);
		}
		return sumToCoinsNum.getOrDefault(sum, -1);
	}

	private int findMinCoinsNumForSum2(int[] coins, int sum) {
		Map<Integer, Integer> sumToCoinsNum = new HashMap<Integer, Integer>();
		sumToCoinsNum.put(0, 0);
		for (int subSum = 1; subSum <= sum; subSum++) {
			sumToCoinsNum.put(subSum, Integer.MAX_VALUE);
		}
		for (int coin : coins) {
			for (int subSum = 0; subSum <= sum; subSum++) {
				int tempSum = subSum + coin;
				if (tempSum <= sum) {
					int tempCoinNum = sumToCoinsNum.get(subSum) + 1;
					if (sumToCoinsNum.get(tempSum) > tempCoinNum) {
						sumToCoinsNum.put(tempSum, tempCoinNum);
					}
				}
			}
		}
		return sumToCoinsNum.get(sum);
	}

}
