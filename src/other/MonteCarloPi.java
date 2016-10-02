package other;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

//calc PI number using monte-carlo method
public class MonteCarloPi {

	@Test
	public void test() {

		long n = 10;
		System.out.println(n + " - " + equation(n));
		n = 100;
		System.out.println(n + " - " + equation(n));
		n = 1000;
		System.out.println(n + " - " + equation(n));
		n = 10000;
		System.out.println(n + " - " + equation(n));
		n = 100000;
		System.out.println(n + " - " + equation(n));
		n = 1000000;
		System.out.println(n + " - " + equation(n));
		n = 10000000;
		System.out.println(n + " - " + equation(n));
		n = 100000000;
		System.out.println(n + " - " + equation(n));
		n = 1000000000;
		System.out.println(n + " - " + equation(n));
		// n = 10000000000l;
		// System.out.println(n + " - " + equation(n));
		// 3.14159265359
	}

	private double equation(long n) {
		// cv=pi*r*r
		// qv=2r*2r=4r*r
		// cv/cv=circle/rectangle=pi/4=montoCarlo(n)/n
		// pi=(4*montoCarlo(n))/n
		return (4d * montoCarlo(n) / n);
	}

	private Random rand = new Random();

	private long parMontoCarlo(long no) {
		rand = new Random();

		ExecutorService pool = Executors.newCachedThreadPool();
		long noPerC = no / Runtime.getRuntime().availableProcessors();
		List<Future<Long>> futureList = new ArrayList<>();
		for (int c = 0; c < Runtime.getRuntime().availableProcessors(); c++) {
			futureList.add(pool.submit(() -> {
				return montoCarlo(noPerC);
			}));
		}
		return futureList.stream().mapToLong(f -> {
			try {
				return f.get();
			} catch (Exception e) {
				return 0;
			}
		}).sum();
	}

	private long montoCarlo(long no) {

		long s = 0;

		for (long i = 0; i < no; i++) {
			double x = rand.nextDouble();
			double y = rand.nextDouble();

			if ((x * x + y * y) < 1) {
				s++;
			}
		}

		return s;

	}

}
