package stringnumtest;

import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

/**
 * 给你一个字符串数组 words ，找出并返回 length(words[i]) * length(words[j]) 的最大值，
 * 并且这两个单词不含有公共字母。如果不存在这样的两个单词，返回 0 。
 * @author Dreihunde
 *
 */
public class MaxProduct {
	private static Random random = new Random();
    public static int maxProduct(String[] words) {
        int len = words.length;
        int[][] lengthIndex = new int[len][2];
        for (int i = 0; i < len; i++) {
            lengthIndex[i][0] = words[i].length();
            lengthIndex[i][1] = i;
        }

        quickSort(lengthIndex, 0, len - 1);

        int max = 0;
        for (int i = len - 1; i > 0; i--)
            for (int j = i - 1; j >= 0; j--) {
                if (!isHaveCommonCharacter(words[lengthIndex[i][1]], words[lengthIndex[j][1]]))
                    max = Math.max(max, lengthIndex[i][0]*lengthIndex[j][0]);
            }
        
        return max;


    }

    private static boolean isHaveCommonCharacter(String s, String p) {
    	int[] mask = new int[26];
        for (int i = 0; i < s.length(); i++) {
            mask[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < p.length(); i++) {
            if (mask[p.charAt(i) - 'a'] > 0) {
                return true;
            }
        }
        return false;
    }

    private static void swapNum(int[][] nums, int i, int j) {
        int[] temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void quickSort(int[][] nums, int left, int right) {
    	if (right - left <= 7) {
    		insertSort(nums);
    		return;
    	}
    	if(left > right)
    		return;
        int pIndex = partition(nums, left, right);
        quickSort(nums, left, pIndex - 1);
        quickSort(nums, pIndex + 1, right);
    }
    
    private static void insertSort(int[][] nums) {
    	int len = nums.length;
    	
    	for (int i = 1; i < len; i++) {
    		int[] temp = nums[i];
    		int j = i;
    		while (j > 0 && nums[j - 1][0] > temp[0]) {
    			nums[j] = nums[j - 1];
    			j--;
    		}
    		nums[j] = temp;
    	}
    }

    private static int partition(int[][] nums, int left, int right) {
        int pIndex = left + random.nextInt(right - left + 1);
        swapNum(nums, left, pIndex);
        int pivot = nums[left][0];
        int lt = left + 1;
        int gt = right;

        while (true) {
            while (lt <= right && nums[lt][0] < pivot) 
                lt++;
            while (gt > left && nums[gt][0] > pivot)
                gt--;
            if(lt >= gt)
                break;
            swapNum(nums, lt, gt);
            lt++;
            gt--;
        }
        swapNum(nums, left, gt);
        return gt;
    }
    
    public static int maxProduct2(String[] words) {
    	int len = words.length;
    	int[] mask = new int[len];
    	int index = 0;
    	
    	
    	for (String s: words) {
    		int strLen = s.length();
    		for (int i = 0; i < strLen; i++) {
    			int temp = s.charAt(i) - 'a';
    			mask[index] |= (1 << temp);
    		}
    		index++;
    	}
    	
    	int max = 0;
    	for (int i = 0; i < len - 1; i++) {
    		for (int j = i + 1; j < len; j++) {
    			if ((mask[i] & mask[j]) == 0)
    				max = Math.max(max, words[i].length() * words[j].length());
    		}
    	}
    	
    	return max;
    }
    
    public static int maxProduct3(String[] words) {
    	Map<Integer, Integer> map = new HashMap<>();
    	
    	
    	for (String s: words) {
    		int strLen = s.length();
    		int t = 0;
    		for (int i = 0; i < strLen; i++) {
    			int u = s.charAt(i) - 'a';
    			t = (1 << u);
    		}
    		if (!map.containsKey(t) || map.get(t) < strLen)
    			map.put(t, strLen);
    	}
    	
    	int max = 0;
    	for (int a: map.keySet()) {
    		for (int b: map.keySet()) {
    			if ((a & b) == 0)
    				max = Math.max(max, map.get(a) * map.get(b));
    		}
    	}
    	
    	return max;
    }
    
    public static void main(String[] args) {
		String[] words = new String[] {"abcdaa", "aa", "bbb", "aaaa"};
		
		long startTime=System.nanoTime(); 
		System.out.println(maxProduct(words));
		long endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime=System.nanoTime(); 
		System.out.println(maxProduct2(words));
		endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime=System.nanoTime(); 
		System.out.println(maxProduct3(words));
		endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
	}

}
