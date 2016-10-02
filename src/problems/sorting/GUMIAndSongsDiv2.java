package problems.sorting;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class GUMIAndSongsDiv2 {

	@Test
	public void test() {
		assertEquals(
				3,
				maxSongs(new int[] { 3, 5, 4, 11 }, new int[] { 2, 1, 3, 1 },
						17));
		assertEquals(
				0,
				maxSongs(new int[] { 100, 200, 300 }, new int[] { 1, 2, 3 }, 10));
		assertEquals(
				4,
				maxSongs(new int[] { 1, 2, 3, 4 }, new int[] { 1, 1, 1, 1 },
						100));
		assertEquals(
				3,
				maxSongs(new int[] { 10, 10, 10 }, new int[] { 58, 58, 58 }, 30));
		assertEquals(
				1,
				maxSongs(new int[] { 8, 11, 7, 15, 9, 16, 7, 9 }, new int[] {
						3, 8, 5, 4, 2, 7, 4, 1 }, 14));
		assertEquals(
				8,
				maxSongs(new int[] { 5611, 39996, 20200, 56574, 81643, 90131,
						33486, 99568, 48112, 97168, 5600, 49145, 73590, 3979,
						94614 }, new int[] { 2916, 53353, 64924, 86481, 44803,
						61254, 99393, 5993, 40781, 2174, 67458, 74263, 69710,
						40044, 80853 }, 302606));
	}

	/**
	 * There is 2^N possibilities of having items in your bag. <br />
	 * For each possibility, we add all the duration cost, while sort the tunes.<br />
	 * This trick could make sure we'll have the least cost for these jumping
	 * tones. Brilliant trick.
	 * */
	public int maxSongs(int[] duration, int[] tone, int T) {
		int N = duration.length;
		int max = 0;
		for (int i = 1; i < (1 << N); i++) {
			int sum = 0;
			int count = 0;
			ArrayList<Integer> list = new ArrayList<Integer>();
			for (int j = 0; j < N; j++) {
				if (((i >> j) & 1) == 1) {
					sum += duration[j];
					list.add(tone[j]);
					count++;
				}
			}
			sum += Collections.max(list) - Collections.min(list);
			if (sum <= T && max < count)
				max = count;
		}

		return max;
	}


	//not working!
	private int maxSongs2(int[] duration, int[] tone, int T) {
		int songsNum = 0;
		int currentSongIdx = -1;
		int lastSongIdx = selectInitSong(duration, tone, T);
		if (lastSongIdx == -1) {
			return songsNum;
		}
		songsNum++;
		int songsSum = duration[lastSongIdx];
		List<Integer> songIdxList = new ArrayList<>();
		songIdxList.add(lastSongIdx);
		do {
			currentSongIdx = selectNextSong(lastSongIdx, duration, tone,
					songIdxList, T - songsSum);
			if (currentSongIdx == -1) {
				break;
			}
			songsSum += (duration[currentSongIdx] + Math.abs(tone[lastSongIdx]
					- tone[currentSongIdx]));
			lastSongIdx = currentSongIdx;
			songIdxList.add(lastSongIdx);
			songsNum++;
		} while (true);
		return songsNum;
	}

	private int selectNextSong(int lastSongIdx, int[] duration, int[] tone,
			List<Integer> alreadySelectedSongsList, int T) {
		int minVal = Integer.MAX_VALUE;
		int minIdx = -1;
		int n = duration.length;
		for (int i = 0; i < n; i++) {
			if (alreadySelectedSongsList.contains(i)) {
				continue;
			}
			int val = duration[i];
			// +
			// Math.abs(tone[i] - tone[lastSongIdx]);
			// System.out.println("VAL: "+val
			// +"(i="+i+";lastIdx="+lastSongIdx+")");
			if (val > T) {
				continue;
			}
			if (val < minVal) {
				minVal = val;
				minIdx = i;
			}
		}
		// System.out.println("S: "+minVal);
		return minIdx;
	}

	private int selectInitSong(int[] duration, int[] tone, int T) {
		int minVal = Integer.MAX_VALUE;
		int minIdx = -1;
		int n = duration.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; i != j && j < n; j++) {
				if (duration[i] > T) {
					continue;
				}
				int val = duration[i];// +
				// Math.abs(tone[i] - tone[j]);
				// +duration[j];
				if (val < minVal) {
					minVal = val;
					minIdx = i;
				}
			}
		}
		return minIdx;
	}

	private int selectInitSong2(int[] duration, int[] tone, int T) {
		int minVal = Integer.MAX_VALUE;
		int minIdx = -1;
		int n = duration.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; i != j && j < n; j++) {
				if (duration[i] > T) {
					continue;
				}
				if (duration[i] < minVal) {
					System.out.println("I: " + i + " J: " + j);
					minVal = duration[i];
					minIdx = i;
				}
			}
		}
		return minIdx;
	}

}
