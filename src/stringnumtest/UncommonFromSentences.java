package stringnumtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 句子 是一串由空格分隔的单词。每个 单词 仅由小写字母组成。
 * 如果某个单词在其中一个句子中恰好出现一次，在另一个句子中却 没有出现 ，那么这个单词就是 不常见的 。
 * 给你两个 句子 s1 和 s2 ，返回所有 不常用单词 的列表。返回列表中单词可以按 任意顺序 组织。
 * https://leetcode-cn.com/problems/uncommon-words-from-two-sentences/
 * @author Dreihunde
 *
 */
public class UncommonFromSentences {
	//method 1 map计数 O(|s1| + |s2|) O(|s1| + |s2|)
    public String[] uncommonFromSentences(String s1, String s2) {
        List<String> rev = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        String[] s1str = s1.split(" ");
        String[] s2str = s2.split(" ");
        for (String str1 : s1str) {
            map.put(str1, map.getOrDefault(str1, 0) + 1);
        }
        for (String str2 : s2str) {
            map.put(str2, map.getOrDefault(str2, 0) + 1);
        }
        
        for (String key : map.keySet()) {
            if (map.get(key) == 1) {
                rev.add(key);
            }
        }
        return rev.toArray(new String[rev.size()]);
        // String[] ans = new String[rev.size()];
        // for (int i = 0; i < rev.size(); i++) {
        //     ans[i] = rev.get(i);
        // }
        // return ans;
    }
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
