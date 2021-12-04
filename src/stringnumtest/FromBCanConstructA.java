package stringnumtest;

import java.util.HashMap;
import java.util.Map;

/**
 * 为了不在赎金信中暴露字迹，从杂志上搜索各个需要的字母，组成单词来表达意思。
 * 给你一个赎金信 (ransomNote) 字符串和一个杂志(magazine)字符串，判断 ransomNote 能不能由 magazines 里面的字符构成。
 * 如果可以构成，返回 true ；否则返回 false 。
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 * @author Dreihunde
 *
 */
public class FromBCanConstructA {
	//method 1 词频统计 O(m + n) O(C)
    public boolean canConstruct1(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length())    return false;
        int[] mask = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            mask[magazine.charAt(i) - 'a']++;
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            //先判断更快
            if (mask[ransomNote.charAt(i) - 'a'] <= 0)   return false;
            mask[ransomNote.charAt(i) - 'a']--;
            
        }

        return true;
    }

    //method 2 map O(m + n) O(C)
    public boolean canConstruct2(String ransomNote, String magazine) {
        Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            freq.put(magazine.charAt(i), freq.getOrDefault(magazine.charAt(i), 0) + 1);
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            if (!freq.containsKey(ransomNote.charAt(i)))    return false;
            freq.put(ransomNote.charAt(i), freq.get(ransomNote.charAt(i)) - 1);
            if (freq.get(ransomNote.charAt(i)) < 0)     return false;
        }

        return true;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ransomNote = "aab";
		String magazine = "aaba";
		FromBCanConstructA fb = new FromBCanConstructA();
		
		long startTime = System.nanoTime();
		System.out.println(fb.canConstruct1(ransomNote, magazine));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
				
		startTime = System.nanoTime();
		System.out.println(fb.canConstruct2(ransomNote, magazine));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		

	}

}
