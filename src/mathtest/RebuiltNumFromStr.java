package src.mathtest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个字符串 s ，其中包含字母顺序打乱的用英文单词表示的若干数字（0-9）。按 升序 返回原始的数字。
 * @author Dreihunde
 *
 */
public class RebuiltNumFromStr {
	//method 1 字频匹配 超时
    public String originalDigits1(String s) {
        int[] mask = new int[26];
        StringBuffer sb = new StringBuffer();
        int count = 0;
        for (char c : s.toCharArray()) {
            mask[c - 'a'] ++;
            count++;
        }
        while (count > 0) {
             while (mask['z' - 'a'] > 0) {
                mask['z' - 'a']--;
                mask['e' - 'a']--;
                mask['r' - 'a']--;
                mask['o' - 'a']--;
                count -= 4;
                sb.append(0);
            }
            while (mask['o' - 'a'] > 0 && mask['n' - 'a'] > 0 && mask['e' - 'a'] > 0) {
                mask['o' - 'a']--;
                mask['n' - 'a']--;
                mask['e' - 'a']--;
                count -= 3;
                sb.append(1);
            }
            while (mask['t' - 'a'] > 0 && mask['w' - 'a'] > 0 && mask['o' - 'a'] > 0) {
                mask['t' - 'a']--;
                mask['w' - 'a']--;
                mask['o' - 'a']--;
                count -= 3;
                sb.append(2);
            }
            while (mask['t' - 'a'] > 0 && mask['h' - 'a'] > 0 && mask['r' - 'a'] > 0 && mask['e' - 'a'] > 1) {
                mask['t' - 'a']--;
                mask['h' - 'a']--;
                mask['r' - 'a']--;
                mask['e' - 'a'] -= 2;
                count -= 5;
                sb.append(3);
            }
            while (mask['f' - 'a'] > 0 && mask['o' - 'a'] > 0 && mask['u' - 'a'] > 0 && mask['r' - 'a'] > 0) {
                mask['f' - 'a']--;
                mask['o' - 'a']--;
                mask['u' - 'a']--;
                mask['r' - 'a'] --;
                count -= 4;
                sb.append(4);
            }
            while (mask['f' - 'a'] > 0 && mask['i' - 'a'] > 0 && mask['v' - 'a'] > 0 && mask['e' - 'a'] > 0) {
                mask['f' - 'a']--;
                mask['i' - 'a']--;
                mask['v' - 'a']--;
                mask['e' - 'a'] --;
                count -= 4;
                sb.append(5);
            }
            while (mask['s' - 'a'] > 0 && mask['i' - 'a'] > 0 && mask['x' - 'a'] > 0) {
                mask['s' - 'a']--;
                mask['i' - 'a']--;
                mask['x' - 'a']--;
                count -= 3;
                sb.append(6);
            }
            while (mask['s' - 'a'] > 0 && mask['n' - 'a'] > 0 && mask['v' - 'a'] > 0 && mask['e' - 'a'] > 1) {
                mask['s' - 'a']--;
                mask['v' - 'a']--;
                mask['n' - 'a']--;
                mask['e' - 'a'] -= 2;
                count -= 5;
                sb.append(7);
            }
            while (mask['t' - 'a'] > 0 && mask['h' - 'a'] > 0 && mask['g' - 'a'] > 0 && mask['i' - 'a'] > 0 && mask['e' - 'a'] > 0) {
                mask['t' - 'a']--;
                mask['h' - 'a']--;
                mask['i' - 'a']--;
                mask['g' - 'a']--;
                mask['e' - 'a']--;
                count -= 5;
                sb.append(8);
            }
            while (mask['n' - 'a'] > 1 && mask['i' - 'a'] > 0 && mask['e' - 'a'] > 0) {
                mask['i' - 'a']--;
                mask['e' - 'a']--;
                mask['n' - 'a'] -= 2;
                count -= 4;
                sb.append(9);
            }
        }

        
        return sb.toString();

    }

    //method 2 解方程
    public String originalDigits2(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        StringBuffer sb = new StringBuffer();
        int[] res = new int[10];
        res[0] = map.getOrDefault('z', 0);
        res[2] = map.getOrDefault('w', 0);
        res[4] = map.getOrDefault('u', 0);
        res[6] = map.getOrDefault('x', 0);
        res[8] = map.getOrDefault('g', 0);

        res[1] = map.getOrDefault('o', 0) - res[0] - res[2] -res[4]; 
        res[3] = map.getOrDefault('r', 0) - res[0] - res[4];
        res[5] = map.getOrDefault('f', 0) - res[4];  
        res[7] = map.getOrDefault('s', 0) - res[6];   
        res[9] = map.getOrDefault('i', 0) - res[5] - res[6] - res[8];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < res[i]; j++) {
                sb.append(i);
            }
        }
        return sb.toString();
    }

    //method 3 词频+排序
    static String[] ss = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    static int[] priority = new int[]{0, 8, 6, 3, 2, 7, 5, 9, 4, 1};
    public String originalDigits3(String s) {
        int n = s.length();
        int[] cnts = new int[26];
        for (int i = 0; i < n; i++) cnts[s.charAt(i) - 'a']++;
        StringBuilder sb = new StringBuilder();
        for (int i : priority) {
            int k = Integer.MAX_VALUE;
            for (char c : ss[i].toCharArray()) k = Math.min(k, cnts[c - 'a']);
            for (char c : ss[i].toCharArray()) cnts[c - 'a'] -= k;
            while (k-- > 0) sb.append(i);
        }
        char[] cs = sb.toString().toCharArray();
        Arrays.sort(cs);
        return String.valueOf(cs);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "owoztneoer";
		RebuiltNumFromStr rb = new RebuiltNumFromStr();
		
		long startTime=System.nanoTime(); 
		System.out.println(rb.originalDigits1(str));
		long endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime=System.nanoTime(); 
		System.out.println(rb.originalDigits2(str));
		endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime=System.nanoTime(); 
		System.out.println(rb.originalDigits3(str));
		endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
	}

}
