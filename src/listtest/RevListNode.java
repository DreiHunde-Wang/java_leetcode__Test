package src.listtest;

import java.util.Deque;
import java.util.ArrayDeque;

/**
 * �����������ͷ�ڵ� head ���뷴ת���������ط�ת��������ͷ�ڵ㡣
 * @author Dreihunde
 *
 */
public class RevListNode {
	/**
	 * method 1 ��������ռ䴢��
	 * @param head
	 * @return
	 */
	public ListNode reverseList1(ListNode head) {
        if(head == null)
            return head;
        Deque<Integer> stack = new ArrayDeque<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }

        ListNode rev = new ListNode(stack.pop());
        ListNode tail = rev;

        while (!stack.isEmpty()) {       
            tail.next = new ListNode(stack.pop());
            tail = tail.next;

        }

        return rev;
    }
	
	/**
	 * ���� O(n) O(1)
	 * @param head
	 * @return
	 */
	public ListNode reverseList2(ListNode head) {
		if(head == null)
			return head;
		
		ListNode cur = head;
		ListNode pre = null;
		while (cur != null) {
			ListNode next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		
		return pre;
	}
	
	//method 2 �ݹ� O(n) O(n)
	public ListNode reverseList3(ListNode head) {
//		if(head == null || head.next == null)
//			return head;
//		//�ݹ�����ڵ�
//		ListNode newHead = reverseList3(head.next);
//		//��תcur��pre�ڵ�
//		head.next.next = head;
//		head.next = null;
//		return newHead;
		return recur(head, null);
		
	}
	
	private ListNode recur(ListNode cur, ListNode pre) {
        if (cur == null) return pre; // ��ֹ����
        ListNode res = recur(cur.next, cur);  // �ݹ��̽ڵ�
        cur.next = pre;              // �޸Ľڵ�����ָ��
        return res;                  // ���ط�ת�����ͷ�ڵ�
    }


	
	
}
