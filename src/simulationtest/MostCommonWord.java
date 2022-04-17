package simulationtest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * 给定一个段落 (paragraph) 和一个禁用单词列表 (banned)。返回出现次数最多，同时不在禁用列表中的单词。
 * 题目保证至少有一个词不在禁用列表中，而且答案唯一。
 * 禁用列表中的单词用小写字母表示，不含标点符号。段落中的单词不区分大小写。答案都是小写字母。
 * 链接：https://leetcode-cn.com/problems/most-common-word
 * @author Dreihunde
 *
 */
public class MostCommonWord {
	//method 1 模拟 O(n + m) O(n + m)
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> banSet = new HashSet<>();
        for (String ban : banned) {
            banSet.add(ban);
        }
        Map<String, Integer> map = new HashMap<>();
        int maxCount = 0;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i <= paragraph.length(); i++) {
            char c = '.';
            if (i < paragraph.length()) {
                c = paragraph.charAt(i);
            }
            if (!Character.isLetter(c)) {
                if (sb.length() != 0) {
                    String temp = sb.toString();
                    if (!banSet.contains(temp)) {
                        map.put(temp, map.getOrDefault(temp, 0) + 1);
                        maxCount = Math.max(maxCount, map.get(temp));
                    }   
                }
                sb.setLength(0);
                continue;
            }
            c = Character.toLowerCase(c);
            sb.append(c);
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == maxCount) {
                return entry.getKey();
            }
        }
        return null;
    }
	
	@Test
	public void testMostCommonWord() {
		String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
		String[] banned = new String[] {"hit"};
		System.out.println(paragraph);
		for (String ban : banned) {
			System.out.print(ban + " ");
		}
		System.out.println();
		String result = mostCommonWord(paragraph, banned);
		System.out.println(result);
	}

}
