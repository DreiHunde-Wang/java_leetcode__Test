package src.listtest;

/**
 * ����һ������ɾ������ĵ����� n ����㣬���ҷ��������ͷ��㡣
 * @author Dreihunde
 *
 */
public class DeleteLastNNode {
	
	public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        if (fast == null) {
            return slow.next;
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
