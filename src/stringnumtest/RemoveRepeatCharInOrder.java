package stringnumtest;

import java.util.ArrayDeque;

/**
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * @author Dreihunde
 *
 */
public class RemoveRepeatCharInOrder {
	//method 1 顺序队列 O(n) O(C)
    public String removeDuplicateLetters(String s) {
        StringBuffer sb = new StringBuffer();
        //记录每个字符最后出现的位置
        int[] lastIndex = new int[26];
        //维护顺序队列
        ArrayDeque<Character> queue = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        //判断当前字符是否观测到
        boolean[] visited = new boolean[26];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!visited[c - 'a']) {
                //当顺序队列的队尾元素大于当前元素且之后还存在时，取出队尾元素
                while (!queue.isEmpty() && queue.peekLast() > c && lastIndex[queue.peekLast() - 'a'] > i) {
                    visited[queue.peekLast() - 'a'] = false;
                    queue.pollLast();
                }
                queue.offerLast(c);
                visited[c - 'a'] = true;
            }
        }

        while (!queue.isEmpty()) {
            sb.append(queue.pollFirst());
        }

        return sb.toString();
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "cbacdcbc";
		
		RemoveRepeatCharInOrder rr = new RemoveRepeatCharInOrder();
		System.out.println(rr.removeDuplicateLetters(s));

	}

}
