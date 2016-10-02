package other;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class CombinationTest {

	@Test
	public void test() {
		// allComb("abcd");

		allComb2(gen(20), 20);

	}

	private static class Bag {
		public int value;
		public int weight;

		public static Bag valueOf(int value, int weight) {
			Bag bag = new Bag();
			bag.value = value;
			bag.weight = weight;
			return bag;
		}

		@Override
		public String toString() {
			return "Bag [value=" + value + ", weight=" + weight + "]";
		}
	}

	private Bag[] gen(int num) {
		Random rand = new Random();
		Bag[] bags = new Bag[num];
		for (int i = 0; i < num; i++) {
			bags[i] = Bag.valueOf(rand.nextInt(9) + 1, rand.nextInt(9) + 1);
		}
		return bags;
	}

	private void allComb2(Bag[] bags, int maxWeight) {

		int n = bags.length;
		List<List<Bag>> combs = new ArrayList<List<Bag>>();
		for (int i = 1; i < (1 << n); i++) {
			List<Bag> comb = new ArrayList<>();
			for (int j = 0; j < n; j++) {
				if (((i >> j) & 1) == 1) {
					comb.add(bags[j]);
				}
			}
			combs.add(comb);
		}

		int maxVal = Integer.MIN_VALUE;
		List<Bag> maxBagList = null;
		for (List<Bag> bagList : combs) {
			int weight = bagList.stream().mapToInt(b -> b.weight).sum();
			if (weight <= maxWeight) {
				int value = bagList.stream().mapToInt(b -> b.value).sum();
				if (value > maxVal) {
					maxVal = value;
					maxBagList = bagList;
				}
			}
		}
		System.out.println("Max value: " + maxVal);
		System.out.println(maxBagList);
	}

	private void allComb(String str) {

		String[] in = str.chars().mapToObj(c -> (char) c + "")
				.toArray(String[]::new);

		int n = in.length;

		for (int i = 1; i < (1 << n); i++) {
			String temp = "";
			for (int j = 0; j < n; j++) {
				if (((i >> j) & 1) == 1) {
					temp += in[j];
				}
			}
			System.out.println(temp);
		}
	}
}
