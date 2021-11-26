package stringnumtest;

import java.util.ArrayDeque;

import numsorttest.CommonTest;

/**
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * @author Dreihunde
 *
 */
public class DecodeStr {
	
	public String decodeString(String s) {
        ArrayDeque<Integer> numStack = new ArrayDeque<>();
        ArrayDeque<StringBuffer> strStack = new ArrayDeque<>();
        char[] arr = s.toCharArray();
        int num = 0;
        StringBuffer sb = new StringBuffer();

        for (char c : arr) {
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '[') {
                numStack.push(num);
                strStack.push(sb);
                num = 0;
                sb = new StringBuffer();
            } else if (c == ']') {
                StringBuffer temp = strStack.pop();
                int n = numStack.pop();
                for (int i = 0; i < n; i++) {
                    temp.append(sb);
                }
                sb = temp;
            } else {
                sb.append(c);
            }     
        }

        return sb.toString();
        
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "2[a]3[bc]";
		DecodeStr ds = new DecodeStr();
		long startTime = System.nanoTime();
		System.out.println(ds.decodeString(str));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}
