package src.mathtest;

import java.util.ArrayList;
import java.util.List;

/**
 * n 位格雷码序列 是一个由 2n 个整数组成的序列，其中：
 * 每个整数都在范围 [0, 2n - 1] 内（�? 0 �? 2n - 1�?
 * 第一个整数是 0
 * �?个整数在序列中出�? 不超过一�?
 * 每对 相邻 整数的二进制表示 恰好�?位不�? ，且
 * 第一�? �? �?后一�? 整数的二进制表示 恰好�?位不�?
 * 给你�?个整�? n ，返回任�?有效�? n 位格雷码序列 �?
 * @author Dreihunde
 *
 */
public class GrayCode {
	//method 1 镜像处理 O(2^n) O(1)
    public List<Integer> grayCode1(int n) {
        List<Integer> ans = new ArrayList<>();
        ans.add(0);
        for (int i = 0; i < n; i++) {
            for (int j = ans.size() - 1; j >= 0; j--) {
                ans.add(ans.get(j) | 1 << i);
            }
        }
        return ans;
    }

    //method 2 公式g(i)=b(i+1)⊕b(i) O(2^n) O(1)
    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < (1 << n); i++) {
            ans.add(i ^ (i >> 1));
        }

        return ans;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
