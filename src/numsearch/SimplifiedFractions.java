package src.numsearch;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数 n ，请你返回所有 0 到 1 之间（不包括 0 和 1）满足分母小于等于  n 的 最简 分数 。分数可以以 任意 顺序返回。
 * https://leetcode-cn.com/problems/simplified-fractions/
 * @author Dreihunde
 *
 */
public class SimplifiedFractions {
	//method 1 暴力遍历 O(n^3) O(1)
    public List<String> simplifiedFractions1(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (hasGcd(i, j)) {
                    continue;
                }
                list.add(getFraction(j, i));
            }
        }
        return list;
    }

    private String getFraction(int num1, int num2) {
        StringBuffer sb = new StringBuffer();
        sb.append(String.valueOf(num1));
        sb.append("/");
        sb.append(String.valueOf(num2));
        return sb.toString();
    }

    boolean hasGcd(int i, int j) {
        if (i != 1 && j % i == 0) {
            return true;
        }
        for (int t = 2; t < i; t++) {
            if (j % t == 0 && i % t == 0) {
                return true;
            }
        }
        return false;
    }

    //method 2 遍历+优化 O(n^2logn) O(1)
    public List<String> simplifiedFractions(int n) {
        List<String> ans = new ArrayList<String>();
        for (int denominator = 2; denominator <= n; ++denominator) {
            for (int numerator = 1; numerator < denominator; ++numerator) {
                if (gcd(numerator, denominator) == 1) {
                    ans.add(numerator + "/" + denominator);
                }
            }
        }
        return ans;
    }

    public int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
