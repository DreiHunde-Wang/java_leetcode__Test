package stringnumtest;

import java.util.ArrayDeque;

/**
 * ����һ���ַ��� s ������ȥ���ַ������ظ�����ĸ��ʹ��ÿ����ĸֻ����һ�Ρ��豣֤ ���ؽ�����ֵ�����С��Ҫ���ܴ��������ַ������λ�ã���
 * @author Dreihunde
 *
 */
public class RemoveRepeatCharInOrder {
	//method 1 ˳����� O(n) O(C)
    public String removeDuplicateLetters(String s) {
        StringBuffer sb = new StringBuffer();
        //��¼ÿ���ַ������ֵ�λ��
        int[] lastIndex = new int[26];
        //ά��˳�����
        ArrayDeque<Character> queue = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        //�жϵ�ǰ�ַ��Ƿ�۲⵽
        boolean[] visited = new boolean[26];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!visited[c - 'a']) {
                //��˳����еĶ�βԪ�ش��ڵ�ǰԪ����֮�󻹴���ʱ��ȡ����βԪ��
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
