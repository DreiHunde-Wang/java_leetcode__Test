package src.dptest;

import java.util.Arrays;

/**
 * 如果字符串中不含有任�? 'aaa'�?'bbb' �? 'ccc' 这样的字符串作为子串，那么该字符串就是一个�?�快乐字符串」�??
 * 给你三个整数 a，b ，c，请你返�? 任意�?�? 满足下列全部条件的字符串 s�?
 * s 是一个尽可能长的快乐字符串�??
 * s �? �?�? 有a 个字�? 'a'、b 个字�? 'b'、c 个字�? 'c' �?
 * s 中只含有 'a'�?'b' �?'c' 三种字母�?
 * 如果不存在这样的字符�? s ，请返回�?个空字符�? ""�?
 * 链接：https://leetcode-cn.com/problems/longest-happy-string
 * @author Dreihunde
 *
 */
public class LongestDiverseStr {
	//method 1 贪心 O((a + b + c) * ClogC) O(C) C 表示字母的种�? C = 3
    public String longestDiverseString(int a, int b, int c) {
        StringBuilder res = new StringBuilder();
        Pair[] arr = {new Pair(a, 'a'), new Pair(b, 'b'), new Pair(c, 'c')};
        
        while (true) {
            //按字频�?�减排序
            Arrays.sort(arr, (p1, p2) -> p2.freq - p1.freq);
            boolean hasNext = false;
            for (Pair pair : arr) {
                //没有剩余的字符就跳过
                if (pair.freq <= 0) {
                    break;
                }
                int m = res.length();
                //如果当前字符与字符串的�?�数前二个字符相同，则跳�?
                if (m >= 2 && res.charAt(m - 2) == pair.ch && res.charAt(m - 1) == pair.ch) {
                    continue;
                }
                hasNext = true;
                res.append(pair.ch);
                pair.freq--;
                break;
            }
            if (!hasNext) {
                break;
            }
        }
      
        return res.toString();
    }

    class Pair {
        int freq;
        char ch;

        public Pair(int freq, char ch) {
            this.freq = freq;
            this.ch = ch;
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
