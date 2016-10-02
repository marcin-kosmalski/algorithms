package problems.strings;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class Library {

	@Test
	public void test() {
		assertEquals(2, documentAccess(new String[]{"diary computers editor","fairytale gardening editor","comix cars author","comix cars librarian"}, new String[]{"employee","editor","author"}, new String[]{"history","cars","computers"}));
		assertEquals(3, documentAccess(new String[]{"a b c","a b d","b b c","b b d","e c d","e c b","e c c","t d e"},
				new String[]{"c","d","x"}, new String[]{"a","b","c"}));
		
	}
	
	private int documentAccess(String[] records, String[] userGroups, String[] roomRights){
		
		Set<String> d=new HashSet<>();
		for(String r:records){
			String[] pr=r.split(" ");
			if(contains(userGroups, pr[2])&&contains(roomRights, pr[1])){
				d.add(pr[0]);
			}
		}
		return d.size();
	}
	
	private boolean contains(String[] array,String token){
		for(String elem:array){
			if(token.equals(elem)){
				return true;
			}
		}
		return false;
	}

}
