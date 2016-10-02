package problems.sorting;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class FoxAndMp3Easy {

	@Test
	public void test() {
		assertArrayEquals(new String[] { "1.mp3", "2.mp3", "3.mp3" },
				playList(3));
		assertArrayEquals(new String[] { "1.mp3", "10.mp3", "2.mp3", "3.mp3",
				"4.mp3", "5.mp3", "6.mp3", "7.mp3", "8.mp3", "9.mp3" },
				playList(10));
		assertArrayEquals(new String[] { "1.mp3", "10.mp3", "11.mp3", "12.mp3",
				"13.mp3", "14.mp3", "15.mp3", "16.mp3", "17.mp3", "18.mp3",
				"19.mp3", "2.mp3", "20.mp3", "21.mp3", "22.mp3", "23.mp3",
				"24.mp3", "25.mp3", "26.mp3", "27.mp3", "28.mp3", "29.mp3",
				"3.mp3", "30.mp3", "31.mp3", "32.mp3", "4.mp3", "5.mp3",
				"6.mp3", "7.mp3", "8.mp3", "9.mp3" }, playList(32));
	}

	public String[] playList(int n) {
		List<String> list=new ArrayList<>();
		for(int i=1;i<=n;i++){
			list.add(i+".mp3");
		}
		Collections.sort(list);
		return list.toArray(new String[0]);
	}

}
