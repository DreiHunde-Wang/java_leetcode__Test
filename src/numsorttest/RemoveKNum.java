package numsorttest;

import java.util.ArrayDeque;

/**
 * 给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字。
 * 除了 0 本身之外，num 不含任何前导零
 * @author Dreihunde
 *
 */
public class RemoveKNum {
	//method 1 顺序队列 O(n) O(n)
    public String removeKdigits(String num, int k) {
        int n = num.length();
        if (k == n)
            return "0";
        //删除k个意味着保留n - k 个
        int remain = n - k;
        ArrayDeque<Character> queue = new ArrayDeque<>();

        //维护一个递增队列
        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            while (!queue.isEmpty() && queue.peekLast() > c && k > 0) {
                queue.pollLast();
                k--;
            }
            queue.offerLast(c);
        }
        //存在非0数
        boolean isZero = true;
        StringBuffer sb = new StringBuffer();
        //只保留前n - k个
        for (int i = 0; i < remain; i++) {
            if (!queue.isEmpty()) {
                if (isZero && queue.peekFirst() != '0') {
                    isZero = false;
                }
                if (!isZero) {
                    sb.append(queue.pollFirst());
                } else {
                    queue.pollFirst();
                }
            }      
        }
        //如果没有非0数，那么也只输出一个0
        if (isZero)
            sb.append("0");

        return sb.toString();
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String num = "10200";
		int k = 1;
		RemoveKNum rk = new RemoveKNum();
		System.out.println(rk.removeKdigits(num, k));

	}

}
