package training.datastructs;

import static org.junit.Assert.*;

import org.junit.Test;

public class OpenAddressingHashMapTest {

	@Test
	public void test() {
	
		
		
		OpenAddressingHashMap map=new OpenAddressingHashMap(10);
		
		map.insert(4);
		map.insert(9);
		map.insert(14);
		
		assertEquals(true, map.find(4));
		assertEquals(true, map.find(9));
		assertEquals(true, map.find(14));
		
		//map.print();
	}
	
	
	


}
