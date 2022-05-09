package src.listtest;

import java.util.Deque;
import java.util.ArrayDeque;

/**
 * 给定单链表的头节点 head ，请反转链表，并返回反转后的链表的头节点。
 * @author Dreihunde
 *
 */
public class RevListNode {
	/**
	 * method 1 借助额外空间储存
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
	 * 迭代 O(n) O(1)
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
	
	//method 2 递归 O(n) O(n)
	public ListNode reverseList3(ListNode head) {
//		if(head == null || head.next == null)
//			return head;
//		//递归后续节点
//		ListNode newHead = reverseList3(head.next);
//		//反转cur和pre节点
//		head.next.next = head;
//		head.next = null;
//		return newHead;
		return recur(head, null);
		
	}
	
	private ListNode recur(ListNode cur, ListNode pre) {
        if (cur == null) return pre; // 终止条件
        ListNode res = recur(cur.next, cur);  // 递归后继节点
        cur.next = pre;              // 修改节点引用指向
        return res;                  // 返回反转链表的头节点
    }


	
	
}
