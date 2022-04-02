package binaryandtest;
 
/**
 * 给定一个表示数据的整数数组 data ，返回它是否为有效的 UTF-8 编码。
 * UTF-8 中的一个字符可能的长度为 1 到 4 字节，遵循以下的规则：
 * 对于 1 字节 的字符，字节的第一位设为 0 ，后面 7 位为这个符号的 unicode 码。
 * 对于 n 字节 的字符 (n > 1)，第一个字节的前 n 位都设为1，第 n+1 位设为 0 ，后面字节的前两位一律设为 10 。
 * 剩下的没有提及的二进制位，全部为这个符号的 unicode 码。
 * 链接：https://leetcode-cn.com/problems/utf-8-validation
 * @author Dreihunde
 *
 */
public class ValidUTF8 {
	//method 1 模拟 O(n) O(1)
    public boolean validUtf8(int[] data) {
        int n = data.length;
        int index = 0;
        while (index < n) {
            //要么第一位是0
            if ((data[index] & (1 << 7)) == 0) {
                index++;
            } else {
                int count = 0;
                //要么有连续count个1
                while ((data[index] & (1 << (7 - count))) != 0) {
                    count++;
                }
                //字符长度只有1-4个字节
                if (count == 1 || count > 4) {
                    return false;
                }
                index++;
                //有count - 1 个10开头的data[index]
                for (int i = 0; i < count - 1; i++) {
                    if (index < n && (data[index] & (1 << 7)) != 0 && (data[index] & (1 << 6)) == 0) {
                        index++;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
