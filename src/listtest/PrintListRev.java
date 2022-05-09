package src.listtest;

import java.util.Deque;

import src.listtest.ListNode;

import java.util.ArrayDeque;


class PrintListRev {
	public static int[] reversePrint(ListNode head) {
        
        Deque<Integer> stack = new ArrayDeque<>();

        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        int size = stack.size();
        int[] rev = new int[size];

        for (int i = 0; i < size; i++) {
            rev[i] = stack.pop();
        }

        return rev;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode head = new ListNode(1);
		ListNode tail = head;
		for(int i = 0; i < 3; i++) {
			tail.next = new ListNode(i + 2);
			tail = tail.next;
		}
		int[] rev = reversePrint(head);
		
		for(int num: rev)
			System.out.print(num + " ");
		
	}

}
