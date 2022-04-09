package dfsandbfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
 * 返回所有可能的结果。答案可以按 任意顺序 返回。
 * https://leetcode-cn.com/problems/remove-invalid-parentheses/
 * @author Dreihunde
 *
 */
public class RemoveInvalidParentheses {
	//method 1 回溯+剪枝 O(n*2^n) O(n^2)
    List<String> ans = new ArrayList<>();
    public List<String> removeInvalidParentheses1(String s) {
        int lremove = 0;
        int rremove = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                lremove++;
            } else if (c == ')'){
                if (lremove == 0) {
                    rremove++;
                } else {
                    lremove--;
                }
            }
        }
        helper(s, 0, lremove, rremove);
        return ans;
    }

    private void helper(String s, int start, int lremove, int rremove) {
        if (lremove == 0 && rremove == 0) {
            if (isValid(s)) {
                ans.add(s);
            }
            return;
        }
        int n = s.length();
        for (int i = start; i < n; i++) {
            if (i != start && s.charAt(i) == s.charAt(i - 1)) {
                continue;
            }
            char c = s.charAt(i);
            if (lremove + rremove > n - i) {
                return;
            }
            if (lremove > 0 && c == '(') {
                helper(s.substring(0, i) + s.substring(i + 1, n), i, lremove - 1, rremove);
            }

            if (rremove > 0 && c == ')') {
                helper(s.substring(0, i) + s.substring(i + 1, n), i, lremove, rremove - 1);
            }
        }

    }

    private boolean isValid(String s) {
        int n = s.length();
        int lt = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                lt++;
            } else if (c == ')') {
                if (lt > 0) {
                    lt--;
                } else {
                    return false;
                }
            }
        }
        return lt == 0;
    }

    //method 2 bfs O(n * 2^n) O(nC(m, n)) 其中n为字符串的长度，m为字符串中非法括号的数目
    public List<String> removeInvalidParentheses2(String s) {
        Set<String> set = new HashSet<>();
        set.add(s);
        while (true) {
            for (String str : set) {
                if (isValid(str)) {
                    ans.add(str);
                }
            }
    
            if (!ans.isEmpty()) {
                break;
            }
            Set<String> nSet = new HashSet<>();
            for (String str : set) {
                int n = str.length();
                for (int i = 0; i < n; i++) {
                    if (i > 0 && str.charAt(i) == str.charAt(i - 1)) {
                        continue;
                    }
                    char c = str.charAt(i);
                    if (c == '(' || c == ')') {
                        nSet.add(str.substring(0, i) + str.substring(i + 1));
                    }
                }
            }
            set = nSet;
        }
        return ans;
    }

    //method 3 枚举 O(n*2^n) O(nC(2/n, n))
    public List<String> removeInvalidParentheses(String s) {
        int lremove = 0;
        int rremove = 0;
        List<Integer> left = new ArrayList<Integer>();
        List<Integer> right = new ArrayList<Integer>();
        List<String> ans = new ArrayList<String>();
        Set<String> cnt = new HashSet<String>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left.add(i);
                lremove++;
            } else if (s.charAt(i) == ')') {
                right.add(i);
                if (lremove == 0) {
                    rremove++;
                } else {
                    lremove--;
                }
            }
        }

        int m = left.size();
        int n = right.size();
        List<Integer> maskArr1 = new ArrayList<Integer>();
        List<Integer> maskArr2 = new ArrayList<Integer>();
        for (int i = 0; i < (1 << m); i++) {
            if (Integer.bitCount(i) != lremove) {
                continue;
            }
            maskArr1.add(i);
        }
        for (int i = 0; i < (1 << n); i++) {
            if (Integer.bitCount(i) != rremove) {
                continue;
            }
            maskArr2.add(i);
        }
        for (int mask1 : maskArr1) {
            for (int mask2 : maskArr2) {
                if (checkValid(s, mask1, left, mask2, right)) {
                    cnt.add(recoverStr(s, mask1, left, mask2, right));
                }
            }
        }
        for (String v : cnt) {
            ans.add(v);
        }

        return ans;
    }

    private boolean checkValid(String str, int lmask, List<Integer> left, int rmask, List<Integer> right) {
        int pos1 = 0;
        int pos2 = 0;
        int cnt = 0;

        for (int i = 0; i < str.length(); i++) {
            if (pos1 < left.size() && i == left.get(pos1)) {
                if ((lmask & (1 << pos1)) == 0) {
                    cnt++;
                }
                pos1++;
            } else if (pos2 < right.size() && i == right.get(pos2)) {
                if ((rmask & (1 << pos2)) == 0) {
                    cnt--;
                    if (cnt < 0) {
                        return false;
                    }
                }
                pos2++;
            }
        }

        return cnt == 0;
    }

    private String recoverStr(String str, int lmask, List<Integer> left, int rmask, List<Integer> right) {
        StringBuilder sb = new StringBuilder();
        int pos1 = 0;
        int pos2 = 0;

        for (int i = 0; i < str.length(); i++) {
            if (pos1 < left.size() && i == left.get(pos1)) {
                if ((lmask & (1 << pos1)) == 0) {
                    sb.append(str.charAt(i));
                }
                pos1++;
            } else if (pos2 < right.size() && i == right.get(pos2)) {
                if ((rmask & (1 << pos2)) == 0) {
                    sb.append(str.charAt(i));
                }
                pos2++;
            } else {
                sb.append(str.charAt(i));
            }
        }

        return sb.toString();
    }

}
