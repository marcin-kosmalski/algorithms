package _2018december_challenge;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

public class Main {

	public static void main(String[] args) throws Exception {

//		File file = Paths.get("input.txt").toFile();
//		if (file.exists()) {
//			System.setIn(new FileInputStream(file));
//		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.valueOf(reader.readLine().trim());
		for (int tt = 0; tt < t; tt++) {
			int n = Integer.valueOf(reader.readLine().trim());
			StringBuilder sb=new StringBuilder();
			sb.append("2 ");
			if (n % 3 == 0) {
				int[] a = a(n - 5, reader);
				//System.out.println(Arrays.toString(a));
				int[] aa = new int[6];
				aa[1] = ask(n - 4, n - 3, n - 2, reader);
				aa[2] = ask(n - 4, n - 3, n - 1, reader);
				aa[3] = ask(n - 2, n - 1, n, reader);
				aa[4] = ask(n, n - 2, n - 4, reader);
				aa[5] = ask(n, n - 1, n - 3, reader);

				int[] a2 = new int[6];
				a2[5] = aa[1] ^ aa[2] ^ aa[3];
				a2[2] = aa[1] ^ a2[5] ^ aa[4];
				a2[1] = aa[2] ^ a2[5] ^ aa[5];
				a2[3] = aa[1] ^ a2[2] ^ a2[1];
				a2[4] = aa[2] ^ a2[2] ^ a2[1];
				//System.out.println(Arrays.toString(a2));
				
				for(int i=1;i<a.length;i++) {
					sb.append(a[i]+" ");
				}
				for(int i=1;i<a2.length;i++) {
					sb.append(a2[i]+" ");
				}
				
			} else {
				int[] a = a(n, reader);
				for(int i=1;i<a.length;i++) {
					sb.append(a[i]+" ");
				}
				//System.out.println(Arrays.toString(a));
			}
			System.out.println(sb.toString().trim());

		}
	}

	private static int[] a(int n, BufferedReader reader) throws Exception {
		int[] cache = new int[n + 1];
		for (int i = 1; i <= n - 2; i++) {
			cache[i] = ask(i, i + 1, i + 2, reader);
		}
		cache[n - 1] = ask(n - 1, n, 1, reader);
		cache[n] = ask(n, 1, 2, reader);
		int[] ref = new int[n + 1];
		Arrays.fill(ref, -1);
		int[] v = new int[n + 1];
		Arrays.fill(v, -1);
		for (int i = 1; i < n; i++) {
			int to = i + 3;
			if (to > n) {
				to = to - n;
			}
			v[i] = cache[i] ^ cache[i + 1];
			ref[i] = to;
		}
		int[] a = new int[n + 1];
		Arrays.fill(a, -1);
		// System.out.println(Arrays.toString(v));
		// System.out.println(Arrays.toString(ref));
		for (int i = 1; i <= 3; i++) {
			int j = i;
			// System.out.println(j);
			int temp = v[i];
			while (j < ref[j]) {
				j = ref[j];
				temp ^= v[j];
				// System.out.println(j);
			}
			if (ref[j] != -1) {
				// System.out.println("XOR: "+temp);
				if (i == 1 && ref[j] == 2 || i == 2 && ref[j] == 1) {
					a[3] = temp ^ cache[1];
				} else if (i == 1 && ref[j] == 3 || i == 3 && ref[j] == 1) {
					a[2] = temp ^ cache[1];
				} else {
					a[1] = temp ^ cache[1];
				}

			}
			// System.out.println(ref[j]);
			// System.out.println("====");
		}
		if (a[3] != -1 && a[2] != -1) {
			a[1] = a[3] ^ a[2] ^ cache[1];
		} else if (a[1] != -1 && a[2] != -1) {
			a[3] = a[1] ^ a[2] ^ cache[1];
		} else {
			a[2] = a[1] ^ a[3] ^ cache[1];
		}

		for (int k = 2; k <= n - 2; k++) {
			a[k + 2] = cache[k] ^ a[k] ^ a[k + 1];
		}
		// System.out.println(Arrays.toString(a));
		return a;
	}

	//private static int[] k = gen(200 + 1);

	private static int ask(int a, int b, int c, BufferedReader reader) throws Exception {
		System.out.println(1 + " " + a + " " + b + " " + c);

		 return Integer.valueOf(reader.readLine().trim());
		//return k[a] ^ k[b] ^ k[c];
	}

	private static int[] gen(int n) {
		int[] v = new int[n];

		Random r = new Random();
		for (int i = 0; i < n; i++) {
			v[i] = r.nextInt(100);
		}

		System.out.println(Arrays.toString(v));
		return v;

	}

}
