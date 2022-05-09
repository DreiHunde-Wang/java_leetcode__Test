package src.listtest;

import java.util.ArrayDeque;
import src.listtest.ListNode;

/**
 * ����һ����������������е�����k���ڵ㡣Ϊ�˷��ϴ�����˵�ϰ�ߣ������1��ʼ�������������β�ڵ��ǵ�����1���ڵ㡣
 * ���磬һ�������� 6 ���ڵ㣬��ͷ�ڵ㿪ʼ�����ǵ�ֵ������ 1��2��3��4��5��6���������ĵ����� 3 ���ڵ���ֵΪ 4 �Ľڵ㡣
 * @author Dreihunde
 *
 */
public class PrintListKNode {
	//method 1 ����ָ�� O(n) O(1)
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

    //method 2 ջ O(n + k)��O(n) O(n)
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
