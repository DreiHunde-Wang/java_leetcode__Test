package listtest;

/**
 * ����һ���������������������ڵĽڵ㣬�����ؽ����������ͷ�ڵ㡣������ڲ��޸Ľڵ��ڲ���ֵ���������ɱ��⣨����ֻ�ܽ��нڵ㽻������
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 * @author Dreihunde
 *
 */
public class SwapPair {
	//method 1 ����ǰ�ýڵ� O(n) O(1)
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

    //method 2 �ݹ� (n) O(1)
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
