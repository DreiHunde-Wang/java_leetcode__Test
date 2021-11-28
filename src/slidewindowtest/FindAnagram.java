package slidewindowtest;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import numsorttest.CommonTest;
import printtreenode.ComnTest;

/**
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 * @author Dreihunde
 *
 */
public class FindAnagram {
	//method 1 滑动窗口 + 字频匹配
    public List<Integer> findAnagrams1(String s, String p) {
        if (s.length() < p.length())
            return new LinkedList<Integer>();

        int[] masks = new int[26];
        int[] maskp = new int[26];
        List<Integer> ans = new LinkedList<>();

        int sLen = s.length();
        int pLen = p.length();

        for (int i = 0; i < pLen; i++) {
            masks[s.charAt(i) - 'a']++;
            maskp[p.charAt(i) - 'a']++;
        }

        if (Arrays.equals(maskp, masks)) {
            ans.add(0);
        }

        for (int i = 0; i < sLen - pLen; i++) {
            masks[s.charAt(i) - 'a']--;
            masks[s.charAt(i + pLen) - 'a']++;
            if (Arrays.equals(masks, maskp)) {
                ans.add(i + 1);
            }
        }
        return ans;
        
    }

    //method 2 滑动窗口 + 差值匹配
    public List<Integer> findAnagrams2(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();

        if (sLen < pLen) {
            return new LinkedList<Integer>();
        }

        List<Integer> ans = new LinkedList<>();
        int[] count = new int[26];

        for (int i = 0; i < pLen; i++) {
            count[s.charAt(i) - 'a']++;
            count[p.charAt(i) - 'a']--;
        }
        int diff = 0;
        for (int c : count) {
            if (c != 0) {
                diff++;
            }
        }
        if (diff == 0) {
            ans.add(0);
        }

        for (int i = 0; i < sLen - pLen; i++) {
            if (count[s.charAt(i) - 'a'] == 1) {
                diff--;
            } else if (count[s.charAt(i) - 'a'] == 0) {
                diff++;
            }
            count[s.charAt(i) - 'a']--;

            if (count[s.charAt(i + pLen) - 'a'] == -1) {
                diff--;
            } else if (count[s.charAt(i + pLen) - 'a'] == 0) {
                diff++;
            }
            count[s.charAt(i + pLen) - 'a']++;

            if (diff == 0) {
                ans.add(i + 1);
            }
        }

        return ans;
    }


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "cbaebabacd";
		String p = "abc";
		
		FindAnagram fa = new FindAnagram();
		long startTime = System.nanoTime();
		ComnTest.printList(fa.findAnagrams1(s, p));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		ComnTest.printList(fa.findAnagrams2(s, p));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
	}

}
