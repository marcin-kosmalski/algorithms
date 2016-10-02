package coursera.alg1.week2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import edu.princeton.cs.algs4.StdIn;

public class Subset {

	public static void main(String[] args) throws IllegalAccessException, InterruptedException, ExecutionException, TimeoutException {
		if (args.length < 1) {
			throw new IllegalAccessException("k must be specified!");
		}
		int k = Integer.valueOf(args[0]);

		RandomizedQueue<String> rq=new RandomizedQueue<String>();
		while(!StdIn.isEmpty()){
			rq.enqueue(StdIn.readString());
		}
		
		for(int i=0;i<k;i++){
			System.out.println(rq.dequeue());
		}
	}
	
	

}
