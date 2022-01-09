package listtest;

import java.util.ArrayList;

/**
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * L0 → L1 → … → Ln-1 → Ln 
 * 请将其重新排列后变为：
 * L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * @author Dreihunde
 *
 */
public class ReOrderList {
	//method 1 查找链表中间值 + 反转链表 + 合并链表 O(n) O(1)
	public void reorderList1(ListNode head) {
        ListNode left = head;
        ListNode mid = head;
        ListNode right = head;

        while (right.next != null && right.next.next != null) {
            mid = mid.next;
            right = right.next.next;
        }
        
        right = reverse(mid.next);
        mid.next = null;

        while (left != null && right != null) {
            ListNode leftNext = left.next;
            ListNode rightNext = right.next;

            left.next = right;
            right.next = leftNext;
            left = leftNext;
            right = rightNext;
        }
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode tail = head;

        while (tail != null) {
            ListNode next = tail.next;
            tail.next = pre;
            pre = tail;
            tail = next;
        }
        return pre;
    }
    
  //method 2 链表记录 O(n) O(n)
    public void reorderList2(ListNode head) {
        ListNode tail = head;
        ArrayList<ListNode> list = new ArrayList<>();

        while (tail != null) {
            list.add(tail);
            tail = tail.next;
        }

        int left = 0;
        int right =list.size() - 1;

        while (left < right) {
            list.get(left).next = list.get(right);
            left++;
            if (left == right) {
                break;
            }
            list.get(right).next = list.get(left);
            right--;
        }
        list.get(left).next = null;

    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode head = new ListNode(1);
		ListNode cur = head;
		for (int i = 2; i <= 4; i++) {
			cur.next = new ListNode(i);
			cur = cur.next;
		}
		
		ReOrderList ro = new ReOrderList();
		PrintListNode.printListNode(head);
		ro.reorderList1(head);
		PrintListNode.printListNode(head);
		

	}

}
