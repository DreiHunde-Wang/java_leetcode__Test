package src.binaryandtest;
/**
 * 给定一个正整数，检查它的二进制表示是否总是 0、1 交替出现：换句话说，就是二进制表示中相邻两位的数字永不相同。
 * https://leetcode-cn.com/problems/binary-number-with-alternating-bits/
 * @author Dreihunde
 *
 */
public class HasAlternatingBits {
	
	//method 1 位运算+模拟 O(logn) O(1)
    public boolean hasAlternatingBits1(int n) {
        boolean ret = true;
        while (n > 0 || (n >> 1) > 0) {
            ret = ret && ((n & 1) != ((n >> 1) & 1));
            if (!ret) {
                return ret;
            }
            n >>= 1;
        }
        return ret;
    }

    //method 2 位运算+优化 O(1) O(1)
    public boolean hasAlternatingBits(int n) {
        int a = n ^ (n >> 1);
        return (a & (a + 1)) == 0;
    }

}
