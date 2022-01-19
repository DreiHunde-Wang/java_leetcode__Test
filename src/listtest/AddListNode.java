package listtest;

import java.util.ArrayDeque;

/**
 * 给定两个 非空链表 l1和 l2 来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。
 * 将这两数相加会返回一个新的链表。
 * 可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * @author Dreihunde
 *
 */
public class AddListNode {
	//method 1 反转 O(n) O(1)
	public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode cur1 = reverse(l1);
        ListNode cur2 = reverse(l2);
        ListNode node = new ListNode(0);
        ListNode ans = null;
        int carry = 0;
        while (cur1 != null || cur2 != null || carry != 0) {
            int val1 = cur1 == null ? 0 : cur1.val;
            int val2 = cur2 == null ? 0 : cur2.val;
            int add = val1 + val2 + carry;
            carry = 0;
            if (add > 9) {
                add = add % 10;
                carry = 1;
            }
            node = new ListNode(add);
            node.next = ans;
            ans = node;
            if (cur1 != null) {
                cur1 = cur1.next;
            }
            if (cur2 != null) {
                cur2 = cur2.next;
            }
        }
        
        return ans;
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }
    
   //method 2 栈 O(n) O(n)
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        ArrayDeque<Integer> stack1 = new ArrayDeque<>();
        ArrayDeque<Integer> stack2 = new ArrayDeque<>();
        while (cur1 != null) {
            stack1.push(cur1.val);
            cur1 = cur1.next;
        }

        while (cur2 != null) {
            stack2.push(cur2.val);
            cur2 = cur2.next;
        }
        ListNode node = new ListNode(0);
        ListNode ans = null;
        int carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            int val1 = stack1.isEmpty() ? 0 : stack1.pop();
            int val2 = stack2.isEmpty() ? 0 : stack2.pop();
            int add = val1 + val2 + carry;
            carry = 0;
            if (add > 9) {
                add = add % 10;
                carry = 1;
            }
            node = new ListNode(add);
            node.next = ans;
            ans = node;
        }
        
        return ans;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub\
		ListNode l1 = new ListNode(7);
		ListNode cur1 = l1;
		for (int i = 1; i < 3; i++) {
			ListNode node = new ListNode(7 - i);
			cur1.next = node;
			cur1 = node;
		}
		ListNode l2 = new ListNode(5);
		ListNode cur2 = l2;
		for (int i = 1; i < 3; i++) {
			ListNode node = new ListNode(5 - i);
			cur2.next = node;
			cur2 = node;
		}
		
		AddListNode al = new AddListNode();
		PrintListNode.printListNode(al.addTwoNumbers1(l1, l2));
		
		cur1 = l1;
		for (int i = 1; i < 3; i++) {
			ListNode node = new ListNode(7 - i);
			cur1.next = node;
			cur1 = node;
		}
		cur2 = l2;
		for (int i = 1; i < 3; i++) {
			ListNode node = new ListNode(5 - i);
			cur2.next = node;
			cur2 = node;
		}
		PrintListNode.printListNode(al.addTwoNumbers2(l1, l2));

	}

}
