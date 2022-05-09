package src.listtest;

import java.util.HashSet;

/**
 * 输入两个链表，找出它们的第一个公共节点。
 * @author Dreihunde
 *
 */
public class ListCommonNode {
	//method 1 set记录法 O(m + n) O(m)
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        HashSet<ListNode> set = new HashSet<>();
        ListNode temp = headA;
        while (temp != null) {
            set.add(temp);
            temp = temp.next;
        }

        temp = headB;
        while (temp != null) {
            if (set.contains(temp)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    //method 2 双指针法 O(m + n) O(2)
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        
        ListNode tail1 = headA;
        ListNode tail2 = headB;

        while (tail1 != tail2) {
            tail1 = (tail1 == null) ? headB : tail1.next;
            tail2 = (tail2 == null) ? headA : tail2.next;
        }

        return tail1;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListCommonNode lc = new ListCommonNode();
		ListNode headA = new ListNode(1);
		ListNode headB = new ListNode(2);
		ListNode temp = headA;
		for (int i = 2; i < 5; i++) {
			temp.next = new ListNode(i);
			temp = temp.next;
		}
		headB.next = headA.next.next;
		
		long startTime = System.nanoTime();
		ListNode.printNode(lc.getIntersectionNode1(headA, headB));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		startTime = System.nanoTime();
		ListNode.printNode(lc.getIntersectionNode2(headA, headB));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}
