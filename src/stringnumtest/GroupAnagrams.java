package stringnumtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import printtreenode.ComnTest;

/**
 * ����һ���ַ������� strs ���� ��λ�� �����һ�� ���԰�����˳�򷵻ؽ���б�
 * ע�⣺�������ַ�����ÿ���ַ����ֵĴ�������ͬ��������ǻ�Ϊ��λ�ʡ�
 * @author Dreihunde
 *
 */
public class GroupAnagrams {
	//hash + mapӳ�� O(nm) O(n)
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
        //hashֵ
        long needleHash = 1;
        for (int i = 0; i < m; i++) {
            needleHash *= hashCode[str.charAt(i) - 'a'];
        }
        return needleHash;
    }

    //method 2 �������ڱȽ� O(nm(1 + logm)) O(nm)
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
