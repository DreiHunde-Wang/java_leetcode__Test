package src.slidewindowtest;

/**
 * �?位�?�师正在出一场由 n 道判断题构成的�?�试，每道题的答案为 true （用 'T' 表示）或�? false （用 'F' 表示）�??
 * 老师想增加学生对自己做出答案的不确定性，方法是 最大化 �? 连续相同 结果的题数�?�（也就是连续出�? true 或�?�连续出�? false）�??
 * 给你�?个字符串 answerKey ，其中 answerKey[i] 是第 i 个问题的正确结果。除此以外，还给你一个整�? k ，表示你能进行以下操作的�?多次数：
 * 每次操作中，将问题的正确答案改为 'T' 或�?��?'F' （也就是�? answerKey[i] 改为 'T' 或�?��?'F' ）�??
 * 请你返回在不超过 k 次操作的情况下，�?大 连�? 'T' 或�?? 'F' 的数目�??
 * 链接：https://leetcode-cn.com/problems/maximize-the-confusion-of-an-exam
 * @author Dreihunde
 *
 */
public class MaxConsecutiveAnswers {
	//method 1 暴力+前缀�?+双遍�? O(n) O(n)
    public int maxConsecutiveAnswers1(String answerKey, int k) {
        int n = answerKey.length();
        int[][] preFix = new int[n + 1][2];

        for (int i = 1; i <= n; i++) {
            char c = answerKey.charAt(i - 1);
            if (c == 'T') {
                preFix[i][0] = preFix[i - 1][0] + 1;
                preFix[i][1] = preFix[i - 1][1];
            } else {
                preFix[i][0] = preFix[i - 1][0];
                preFix[i][1] = preFix[i - 1][1] + 1;
            }
        }

        int maxLen = 0;
        int preIndex = 0;
        for (int i = 1; i <= n; i++) {
            if (preFix[i][1] - preFix[preIndex][1] <= k) {
                maxLen = Math.max(maxLen, i - preIndex);
            } else {
                while (preIndex < i && preFix[i][1] - preFix[preIndex][1] > k) {
                    preIndex++;
                }
            }
        }

        preIndex = 0;
        for (int i = 1; i <= n; i++) {
            if (preFix[i][0] - preFix[preIndex][0] <= k) {
                maxLen = Math.max(maxLen, i - preIndex);
            } else {
                while (preIndex < i && preFix[i][0] - preFix[preIndex][0] > k) {
                    preIndex++;
                }
            }
        }

        return maxLen;
    }

    //method 2 滑动窗口 O(n) O(1)
    public int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(getMaxSub(answerKey, 'T', k), getMaxSub(answerKey, 'F', k));
    }

    private int getMaxSub(String answerKey, char target, int k) {
        int n = answerKey.length();
        int left = 0;
        int right = 0;
        int maxLen = 0;
        int sum = 0;
        while (right < n) {
            char c = answerKey.charAt(right);
            if (c != target) {
                sum++;
            }
            while (sum > k) {
                if (answerKey.charAt(left) != target) {
                    sum--;
                }
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }

        return maxLen;
    }

}
