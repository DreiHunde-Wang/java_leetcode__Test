package src.listtest;

import java.util.ArrayDeque;
import src.listtest.ListNode;

/**
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
 * @author Dreihunde
 *
 */
public class PrintListKNode {
	//method 1 快慢指针 O(n) O(1)
    public ListNode getKthFromEnd1(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;

        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    //method 2 栈 O(n + k)≈O(n) O(n)
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode tail = head;
        ArrayDeque<ListNode> stack = new ArrayDeque<>();
    

        while (tail != null) {
            stack.push(tail);
            tail = tail.next;
        }

        while (k > 1) {
            stack.pop();
            k--;
        }
        return stack.pop();
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode head = new ListNode(1);
		ListNode temp = head;
		for (int i = 2; i <= 5; i++) {
			temp.next = new ListNode(i);
			temp = temp.next;
		}
		int val1 = 3;
		int val2 = 5;
		
		PrintListKNode pk = new PrintListKNode();
		long startTime = System.nanoTime();
		ListNode.printNode(pk.getKthFromEnd1(head, val1));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		ListNode.printNode(pk.getKthFromEnd1(head, val2));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}
