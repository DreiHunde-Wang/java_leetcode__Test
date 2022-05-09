package src.dfsandbfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定�?个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回�?
 * 给出数字到字母的映射如下（与电话按键相同）�?�注�? 1 不对应任何字母�??
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * @author Dreihunde
 *
 */
public class LetterCombination {
    List<List<Character>> pairs;
    ArrayList<String> ans;
    //method 1 dfs + 回溯 O(4^n) O(n)
    public List<String> letterCombinations(String digits) {
        pairs = new ArrayList<>();
        ans = new ArrayList<>();
        if (digits.length() == 0) {
            return ans;
        }
        for (int i = 0; i < 8; i++) {
            pairs.add(new ArrayList<>());
        }
        int index = 0;
        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            pairs.get(index).add(c);
            if ((i + 1) % 3 == 0 && i + 1 <= 15) {
                index++;
            } else if (i + 1 == 19 || i + 1 == 22) {
                index++;
            }
        }
        dfs(digits, 0, new StringBuffer());

        return ans;
    }

    private void dfs(String digits, int index, StringBuffer sb) {
        if (index == digits.length()) {
            ans.add(sb.toString());
            return;
        }

        int idx = digits.charAt(index) - '0' - 2;

        for (char c : pairs.get(idx)) {
            sb.append(c);
            dfs(digits, index + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
