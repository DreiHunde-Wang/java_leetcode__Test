package listtest;

/**
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 * @author Dreihunde
 *
 */
public class SwapPair {
	//method 1 增加前置节点 O(n) O(1)
    public ListNode swapPairs1(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode preHead = new ListNode(0);
        preHead.next = head;
        ListNode pre = preHead;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            ListNode nextCur = cur;
            for (int i = 0; i < 2; i++) {
                nextCur = nextCur.next;
            }
            swap(pre, cur);
            pre = cur;
            cur = nextCur;
        }
        return preHead.next;

    }

    public void swap(ListNode pre, ListNode cur) {
        ListNode next = cur.next;
        cur.next = next.next;
        next.next = cur;
        pre.next = next;
    }

    //method 2 递归 (n) O(1)
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }

}
