package classdesign;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class MyHashMap {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 16; i++) {
			list.add("str" + Integer.toString(i));
		}
		
		String[] strs = new String[8];
		
		for (String str : list) {
			int idx = str.hashCode() & (strs.length - 1);
			System.out.println(String.format("key:%s idx:%d", str, idx));
			if (strs[idx] == null) {
				strs[idx] = str;
			} else {
				strs[idx] = strs[idx] + "->" + str;
			}
		}
		
		for (String str : strs) {
			System.out.print(str + " ");
		}
	}
	
	

}
