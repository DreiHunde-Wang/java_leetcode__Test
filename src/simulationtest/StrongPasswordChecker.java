package src.simulationtest;

/**
 * 如果�?个密码满足下述所有条件，则认为这个密码是强密码：
 * 由至�? 6 个，至多 20 个字符组成�??
 * 至少包含 �?个小�? 字母，一个大�? 字母，和 �?个数�? �?
 * 同一字符 不能 连续出现三次 (比如 "...aaa..." 是不允许�?, 但是 "...aa...a..." 如果满足其他条件也可以算是强密码)�?
 * 给你�?个字符串 password ，返回 将 password 修改到满足强密码条件�?要的�?少修改步数�?�如�? password 已经是强密码，则返回 0 �?
 * 在一步修改操作中，你可以�?
 * 插入�?个字符到 password �?
 * �? password 中删除一个字符，�?
 * 用另�?个字符来替换 password 中的某个字符�?
 * 链接：https://leetcode-cn.com/problems/strong-password-checker
 * @author 1
 *
 */
public class StrongPasswordChecker {
	//method 1 分类讨论 O(n) O(1)
    public int strongPasswordChecker(String password) {
        int n = password.length();
        int hasLower = 0, hasUpper = 0, hasDigit = 0;
        for (int i = 0; i < n; ++i) {
            char ch = password.charAt(i);
            if (Character.isLowerCase(ch)) {
                hasLower = 1;
            } else if (Character.isUpperCase(ch)) {
                hasUpper = 1;
            } else if (Character.isDigit(ch)) {
                hasDigit = 1;
            }
        }
        int categories = hasLower + hasUpper + hasDigit;

        if (n < 6) {
            return Math.max(6 - n, 3 - categories);
        } else if (n <= 20) {
            int replace = 0;
            int cnt = 0;
            char cur = '#';

            for (int i = 0; i < n; ++i) {
                char ch = password.charAt(i);
                if (ch == cur) {
                    ++cnt;
                } else {
                    replace += cnt / 3;
                    cnt = 1;
                    cur = ch;
                }
            }
            replace += cnt / 3;
            return Math.max(replace, 3 - categories);
        } else {
            // 替换次数和删除次�?
            int replace = 0, remove = n - 20;
            // k mod 3 = 1 的组数，即删�? 2 个字符可以减�? 1 次替换操�?
            int rm2 = 0;
            int cnt = 0;
            char cur = '#';

            for (int i = 0; i < n; ++i) {
                char ch = password.charAt(i);
                if (ch == cur) {
                    ++cnt;
                } else {
                    if (remove > 0 && cnt >= 3) {
                        if (cnt % 3 == 0) {
                            // 如果�? k % 3 = 0 的组，那么优先删�? 1 个字符，减少 1 次替换操�?
                            --remove;
                            --replace;
                        } else if (cnt % 3 == 1) {
                            // 如果�? k % 3 = 1 的组，那么存下来备用
                            ++rm2;
                        }
                        // k % 3 = 2 的组无需显式考虑
                    }
                    replace += cnt / 3;
                    cnt = 1;
                    cur = ch;
                }
            }
            if (remove > 0 && cnt >= 3) {
                if (cnt % 3 == 0) {
                    --remove;
                    --replace;
                } else if (cnt % 3 == 1) {
                    ++rm2;
                }
            }
            replace += cnt / 3;

            // 使用 k % 3 = 1 的组的数量，由剩余的替换次数、组数和剩余的删除次数共同决�?
            int use2 = Math.min(Math.min(replace, rm2), remove / 2);
            replace -= use2;
            remove -= use2 * 2;
            // 由于每有�?次替换次数就�?定有 3 个连续相同的字符（k / 3 决定），因此这里可以直接计算出使�? k % 3 = 2 的组的数�?
            int use3 = Math.min(replace, remove / 3);
            replace -= use3;
            remove -= use3 * 3;
            return (n - 20) + Math.max(replace, 3 - categories);
        }
    }

}
