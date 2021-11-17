package listtest;

import java.util.Deque;
import java.util.ArrayDeque;

public class RevListNode {
	/**
	 * method 1 ½èÖú¶îÍâ¿Õ¼ä´¢´æ
	 * @param head
	 * @return
	 */
	public static ListNode reverseList1(ListNode head) {
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
	 * µü´ú
	 * @param head
	 * @return
	 */
	public static ListNode reverseList2(ListNode head) {
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
	
	public static ListNode reverseList3(ListNode head) {
		if(head == null || head.next == null)
			return head;
		
		ListNode newHead = reverseList3(head.next);
		head.next.next = head;
		head.next = null;
		
		return newHead;
		
	}
	
	
}
