package problem.search;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

import org.junit.Test;

public class FairWorkload {

	@Test
	public void test() {
		assertEquals(
				170,
				getMostWork(new int[] { 10, 20, 30, 40, 50, 60, 70, 80, 90 }, 3));
	}

	public int getMostWork(int[] folders, int workers) {

		int n = folders.length;
		int lo = IntStream.of(folders).max().getAsInt();
		int hi = IntStream.of(folders).sum();
		System.out.println("init lo=" + lo);
		System.out.println("init hi=" + hi);
		while (lo < hi) {
			int x = lo + (hi - lo) / 2;
			System.out.println("===============");
			System.out.println("x=" + x);
			System.out.println("lo=" + lo);
			System.out.println("hi=" + hi);
			int requiredWorkers = 1;
			int countedFoldersPerWorker = 0;
			for (int i = 0; i < n; i++) {
				if (countedFoldersPerWorker + folders[i] <= x) {
					countedFoldersPerWorker += folders[i];
				} else {
					++requiredWorkers;
					countedFoldersPerWorker = folders[i];
				}
			}
			System.out.println("workers=" + requiredWorkers);
			if (requiredWorkers <= workers) {
				hi = x;
			} else {
				lo = x+ 1;
			}
		}
		return lo;
	}
}

// Problem Statement for FairWorkload
//
// Problem Statement
// Fabian is in charge of a law firm working on an important case. For a case
// coming up, he needs a specific folder which is stored in one of the filing
// cabinets arranged in a line against the wall of the records room. He has
// assigned a number of workers to find the folder from the filing cabinets. He
// doesn't want the workers to get in each other's way, nor does he want folders
// from different filing cabinets getting mixed up, so he has decided to
// partition the cabinets, and assign a specific section to each worker. Each
// worker will have at least 1 cabinet to search through.
//
// More specifically, Fabian wants to divide the line of filing cabinets into N
// sections (where N is the number of workers) so that every cabinet that the
// ith worker looks through is earlier in the line than every cabinet that the
// jth worker has to look through, for i < j.
//
// His initial thought was to make all the sections equal, giving each worker
// the same number of filing cabinets to look through, but then he realized that
// the filing cabinets differed in the number of folders they contained. He now
// has decided to partition the filing cabinets so as to minimize the maximum
// number of folders that a worker would have to look through. For example,
// suppose there were three workers and nine filing cabinets with the following
// number of folders:
//
// 10 20 30 40 50 60 70 80 90
//
// He would divide up the filing cabinets into the following sections:
//
// 10 20 30 40 50 | 60 70 | 80 90
//
//
// The worker assigned to the first section would have to look through 150
// folders. The worker assigned to the second section would have to search
// through 130 folders, and the last worker would filter through 170 folders. In
// this partitioning, the maximum number of folders that a worker looks through
// is 170. No other partitioning has less than 170 folders in the largest
// partition.
//
//
//
// Write a class FairWorkload with a method getMostWork which takes a int[]
// folders (the number of folders for each filing cabinet) and an int workers
// (the number of workers). The method should return an int which is the maximum
// amount of folders that a worker would have to look through in an optimal
// partitioning of the filing cabinets. For the above example, the method would
// have returned 170.
//
// Definition
//
// Class: FairWorkload
// Method: getMostWork
// Parameters: int[], int
// Returns: int
// Method signature: int getMostWork(int[] folders, int workers)
// (be sure your method is public)
