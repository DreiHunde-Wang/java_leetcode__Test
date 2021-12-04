package stringnumtest;

import java.util.HashMap;
import java.util.Map;

/**
 * Ϊ�˲���������б�¶�ּ�������־������������Ҫ����ĸ����ɵ����������˼��
 * ����һ������� (ransomNote) �ַ�����һ����־(magazine)�ַ������ж� ransomNote �ܲ����� magazines ������ַ����ɡ�
 * ������Թ��ɣ����� true �����򷵻� false ��
 * magazine �е�ÿ���ַ�ֻ���� ransomNote ��ʹ��һ�Ρ�
 * @author Dreihunde
 *
 */
public class FromBCanConstructA {
	//method 1 ��Ƶͳ�� O(m + n) O(C)
    public boolean canConstruct1(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length())    return false;
        int[] mask = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            mask[magazine.charAt(i) - 'a']++;
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            //���жϸ���
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
