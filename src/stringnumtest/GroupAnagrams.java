package src.stringnumtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import src.printtreenode.ComnTest;

/**
 * ����һ���ַ������� strs ���� ��λ�� �����һ�� ���԰�����˳�򷵻ؽ���б�
 * ע�⣺�������ַ�����ÿ���ַ����ֵĴ�������ͬ��������ǻ�Ϊ��λ�ʡ�
 * https://leetcode-cn.com/problems/group-anagrams/
 * @author Dreihunde
 *
 */
public class GroupAnagrams {
	//method 1 �Զ���hash O(nk) O(nk)
    public static long[] primes = new long[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
    public List<List<String>> groupAnagrams1(String[] strs) {
        Map<Long, List<String>> map = new HashMap<>();
        List<List<String>> ans = new ArrayList<>();
        for (String str : strs) {
            long ret = strToInt(str);
            if (!map.containsKey(ret)) {
                List<String> temp = new ArrayList<>();
                map.put(ret, temp);
            }
            map.get(ret).add(str);
        }
        for (Map.Entry<Long, List<String>> entry : map.entrySet()) {
            ans.add(entry.getValue());
        }
        return ans;
    }


    public long strToInt(String str) {
        long ret = 1L;
        long ret2 = 0L;
        for (int i = 0; i < str.length(); i++) {
            int idx = str.charAt(i) - 'a';
            ret *= primes[idx];
            ret2 += 1 << primes[idx];
        }
        return ret ^ ret2;
    }


    //method 2 �����ϣ O(nklogk) O(nk)
    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

    //method 3 stream��  O(nk) O(nk)
    public List<List<String>> groupAnagrams3(String[] strs) {
        return new ArrayList<>(Arrays.stream(strs)
            .collect(Collectors.groupingBy(str -> {
                // ���� str �����Ľ����
                // �������Ľ����grouping by������������ sql ��� group by��
                char[] array = str.toCharArray();
                Arrays.sort(array);
                return new String(array);
            })).values());
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] strs = new String[] {"eat","tea","tan","ate","nat","bat"};
		GroupAnagrams ga = new GroupAnagrams();
		ComnTest.printStringList(ga.groupAnagrams1(strs));
		ComnTest.printStringList(ga.groupAnagrams2(strs));

	}

}
