package src.listtest;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定�?个链表，返回链表�?始入环的第一个节点�?? 从链表的头节点开始沿�? next 指针进入环的第一个节点为环的入口节点�?
 * 如果链表无环，则返回 null�?
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引�? 0 �?始）�? 
 * 如果 pos �? -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中�??
 * 说明：不允许修改给定的链表�??
 * @author Dreihunde
 *
 */
public class FindCycleNode {
	//method 1 哈希�? O(n) O(n)
    public ListNode detectCycle1(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode tail = head;
        while (tail != null) {
            if (set.contains(tail)) {
                return tail;
            }
            set.add(tail);
            tail = tail.next;
        }
        return null;
    }

    //method 2 快慢指针 O(n) O(1)
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        int i = 0;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                ListNode ptr = head;
                while (ptr != slow) {
                    slow = slow.next;
                    ptr = ptr.next;
                }
                return ptr;
            }
        }
        return null;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
