package src.numsorttest;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定�?个正整数 n ，你可以做如下操作：
 * 如果 n 是偶数，则用 n / 2替换 n �?
 * 如果 n 是奇数，则可以用 n + 1或n - 1替换 n �?
 * n 变为 1 �?�?的最小替换次数是多少�?
 * @author Dreihunde
 *
 */
public class NumReplace {
	
	//method 1
    public int integerReplacement1(int n) {
        if (n == 1) {
            return 0;
        }
        if (n % 2 == 0) {
            return 1 + integerReplacement(n / 2);
        }
        return 2 + Math.min(integerReplacement(n / 2), integerReplacement(n / 2 + 1));
    }

    //method 2 优化递归，记录出现过的数�?
    Map<Integer, Integer> memo = new HashMap<Integer, Integer>();
    public int integerReplacement2(int n) {
        if (n == 1) {
            return 0;
        }
        if (!memo.containsKey(n)) {
            if (n % 2 == 0) {
                memo.put(n, 1 + integerReplacement(n / 2));
            } else {
                memo.put(n, 2 + Math.min(integerReplacement(n / 2), integerReplacement(n / 2 + 1)));
            }
        }
        return memo.get(n);
    }

    //method 3
    public int integerReplacement(int n) {
        if (n == 1) {
            return 0;
        }
        int ans = 0;
        while (n != 1) {
            if (n % 2 == 0) {
                n /= 2;
                ans++;
            }    
            else if (n % 4 == 1) {
                n = n / 2;
                ans += 2;
            } else {
                if (n == 3) {
                    n = n / 2;
                    ans += 2;
                } else {
                    n = n/2 + 1;
                    ans += 2;
                }
            }
        }

        return ans;
    }

}
