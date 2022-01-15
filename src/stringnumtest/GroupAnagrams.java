package stringnumtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import printtreenode.ComnTest;

/**
 * 给定一个字符串数组 strs ，将 变位词 组合在一起。 可以按任意顺序返回结果列表。
 * 注意：若两个字符串中每个字符出现的次数都相同，则称它们互为变位词。
 * @author Dreihunde
 *
 */
public class GroupAnagrams {
	//hash + map映射 O(nm) O(n)
    int[] hashCode = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
    public List<List<String>> groupAnagrams1(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        Map<Long, Integer> map = new HashMap<>();
        for (String str: strs) {
            long curHash = getHash(str);
            if (map.containsKey(curHash)) {
                int idx = map.get(curHash);
                ans.get(idx).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                ans.add(list);
                map.put(curHash, ans.size() - 1);
            }
        }
        return ans;
    }

    private long getHash(String str) {
        if (str.length() == 0) {
            return 0;
        }
        int m = str.length();
        //hash值
        long needleHash = 1;
        for (int i = 0; i < m; i++) {
            needleHash *= hashCode[str.charAt(i) - 'a'];
        }
        return needleHash;
    }

    //method 2 先排序，在比较 O(nm(1 + logm)) O(nm)
    public List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String str: strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            if (map.containsKey(new String(arr))) {
                int idx = map.get(new String(arr));
                ans.get(idx).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                ans.add(list);
                map.put(new String(arr), ans.size() - 1);
            }
        }
        return ans;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] strs = new String[] {"eat","tea","tan","ate","nat","bat"};
		GroupAnagrams ga = new GroupAnagrams();
		ComnTest.printStringList(ga.groupAnagrams1(strs));
		ComnTest.printStringList(ga.groupAnagrams2(strs));

	}

}
