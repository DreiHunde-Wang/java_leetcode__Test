package numsorttest;

import java.util.ArrayDeque;

/**
 * ����һ�����ַ�����ʾ�ķǸ����� num ��һ������ k ���Ƴ�������е� k λ���֣�ʹ��ʣ�µ�������С���������ַ�����ʽ���������С�����֡�
 * ���� 0 ����֮�⣬num �����κ�ǰ����
 * @author Dreihunde
 *
 */
public class RemoveKNum {
	//method 1 ˳����� O(n) O(n)
    public String removeKdigits(String num, int k) {
        int n = num.length();
        if (k == n)
            return "0";
        //ɾ��k����ζ�ű���n - k ��
        int remain = n - k;
        ArrayDeque<Character> queue = new ArrayDeque<>();

        //ά��һ����������
        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            while (!queue.isEmpty() && queue.peekLast() > c && k > 0) {
                queue.pollLast();
                k--;
            }
            queue.offerLast(c);
        }
        //���ڷ�0��
        boolean isZero = true;
        StringBuffer sb = new StringBuffer();
        //ֻ����ǰn - k��
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
        //���û�з�0������ôҲֻ���һ��0
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
