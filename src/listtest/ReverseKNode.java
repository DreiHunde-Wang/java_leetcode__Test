package src.listtest;

/**
 * 给你�?个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表�?
 * k 是一个正整数，它的�?�小于或等于链表的长度�??
 * 如果节点总数不是 k 的整数�?�，那么请将�?后剩余的节点保持原有顺序�?
 * 进阶�?
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗�?
 * 你不能只是单纯的改变节点内部的�?�，而是�?要实际进行节点交换�??
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 * @author Dreihunde
 *
 */
public class ReverseKNode {
	//method 1 迭代 O(n) O(1)
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode cur = head;
        ListNode pre = hair;
        while (head != null) {
            ListNode tail = pre;
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (tail == null) {
                    return hair.next;
                }
            }
            ListNode next = tail.next;
            ListNode[] rev = reverse(head, tail);
            head = rev[0];
            tail = rev[1];
            //重新接入原链�?
            pre.next = head;
            tail.next = next;
            pre = tail;
            head = next;

        }
        return hair.next;
    }

    private ListNode[] reverse(ListNode head, ListNode tail) {
        if (head == null) {
            return null;
        }
        ListNode pre = tail.next;
        ListNode cur = head;
        while (pre != tail) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return new ListNode[]{tail, head};
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
