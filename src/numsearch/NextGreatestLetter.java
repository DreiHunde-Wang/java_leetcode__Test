package numsearch;

/**
 * 给你一个排序后的字符列表 letters ，列表中只包含小写英文字母。另给出一个目标字母 target，请你寻找在这一有序列表里比目标字母大的最小字母。
 * 在比较时，字母是依序循环出现的。举个例子：
 * 如果目标字母 target = 'z' 并且字符列表为 letters = ['a', 'b']，则答案返回 'a'
 * 链接：https://leetcode-cn.com/problems/find-smallest-letter-greater-than-target
 * @author Dreihunde
 *
 */
public class NextGreatestLetter {
	//method 1 模拟 O(n) O(1)
    public char nextGreatestLetter1(char[] letters, char target) {
        char min = (char) ('z' + 26);
        for (char letter : letters) {
            if (diff(letter, target) > 0 && diff(target, letter) - diff(target, min) < 0) {
                min = letter;
            }
        }
        return min;
    }

    private int diff(char a, char b) {
        if (b == a) {
            return 0;
        }
        if (b - a < 0) {
            return b - 'a' + 'z' - a + 1;
        } else {
            return b - a;
        }
    }

    //method 2 二分 O(logn) O(1)
    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        if (target >= letters[n - 1]) {
            return letters[0];
        }
        int lt = 0;
        int rt = n - 1;
        while (lt < rt) {
            int mid = (lt + rt) >> 1;
            if (target < letters[mid]) {
                rt = mid;
            } else {
                lt = mid + 1;
            }
        }
        return letters[lt];
    }

}
