package dptest;

/**
 * 为了 打出 一个字母，Alice 需要 按 对应字母 i 次，i 是该字母在这个按键上所处的位置。
 * 比方说，为了按出字母 's' ，Alice 需要按 '7' 四次。类似的， Alice 需要按 '5' 两次得到字母  'k' 。
 * 注意，数字 '0' 和 '1' 不映射到任何字母，所以 Alice 不 使用它们。
 * 但是，由于传输的错误，Bob 没有收到 Alice 打字的字母信息，反而收到了 按键的字符串信息 。
 * 比方说，Alice 发出的信息为 "bob" ，Bob 将收到字符串 "2266622" 。
 * 给你一个字符串 pressedKeys ，表示 Bob 收到的字符串，请你返回 Alice 总共可能发出多少种文字信息 。
 * 由于答案可能很大，将它对 109 + 7 取余 后返回。
 * 链接：https://leetcode-cn.com/problems/count-number-of-texts
 * @author Dreihunde
 *
 */
public class ountTexts {
	//method 1 模拟+dp O(n) O(n)
    static int N = 100010;
    static long[] three = new long[N];
    static long[] four = new long[N];

    static int MOD = (int) 1e9 + 7;
    //f [n] 为同一个字母的连续长度 n 含有的组合可能数
    //对于3个字母的数字有 f[n] = f[n-1] + f[n-2] + f[n-3]
    //对于4个字母的数字7、9,有 f[n] = f[n-1] + f[n-2] + f[n-3] + f[n-4]
    static {
        three[0] = 1;
        three[1] = 1;
        three[2] = 2;
        three[3] = 4;
        four[0] = 1;
        four[1] = 1;
        four[2] = 2;
        four[3] = 4;
        for (int i = 4; i < N; i++) {
            three[i] = three[i - 1] + three[i - 2] + three[i - 3];
            three[i] %= MOD;
            four[i] = four[i - 1] + four[i - 2] + four[i - 3] + four[i - 4];
            four[i] %= MOD;
        }
    }


    public int countTexts(String pressedKeys) {
        char[] cs = pressedKeys.toCharArray();
        int n = cs.length, i = 0;
        long ans = 1;
        while (i < n) {
            int j = i;
            while (j + 1 < n && cs[j] == cs[j + 1]) j++;
            boolean isFour = cs[i] == '7' | cs[i] == '9';
            long cur = isFour ? four[j - i + 1] : three[j - i + 1];
            ans = (ans * cur) % MOD;
            i = j + 1;
        }
        return (int) ans;
    }

}
