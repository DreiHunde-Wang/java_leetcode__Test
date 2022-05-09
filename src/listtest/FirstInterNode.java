package src.listtest;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定两个单链表的头节点 headA 和 headB ，请找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 * @author Dreihunde
 *
 */
public class FirstInterNode {
	
	//method 1 Set O(n + m) O(n)
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        
        ListNode tailA = headA;
        while (tailA != null) {
            set.add(tailA);
            tailA = tailA.next;
        }

        ListNode tailB = headB;
        while (tailB != null) {
            if (set.contains(tailB)) {
                return tailB;
            }
            tailB = tailB.next;
        }

        return null;
    }

    //method 2 双指针法 O(n + m) O(1)
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode tailA = headA;
        ListNode tailB = headB;
        
        //n + m = m + n
        while (tailA != tailB) {
            tailA = tailA == null ? headB : tailA.next;
            tailB = tailB == null ? headA : tailB.next;
        }
        return tailA;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
