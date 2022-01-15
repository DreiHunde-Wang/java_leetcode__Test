package listtest;

/**
 * ����һ������� ͷ�ڵ� head �����ж����Ƿ�Ϊ��������
 * ���һ�������ǻ��ģ���ô����ڵ����д�ǰ���󿴺ʹӺ���ǰ������ͬ�ġ�
 * @author Dreihunde
 *
 */
public class IsPalindrome {
	public boolean isPalindrome(ListNode head) {
        ListNode tail = head;
        ListNode mid = findMidNode(head);
        ListNode tail2 = mid.next;
        mid.next = null;
        tail2 = reverse(tail2);
        while (tail != null && tail2 != null) {
            if (tail.val != tail2.val) {
                return false;
            }
            tail = tail.next;
            tail2 = tail2.next;
        }
        return true;
    }
	
	private ListNode findMidNode(ListNode head) {
		ListNode fast = head;
		ListNode slow = head;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[] {1, 1, 1, 1, 1};
		ListNode head = ListNode.numToListNode(nums);
		IsPalindrome ip = new IsPalindrome();
		System.out.println(ip.isPalindrome(head));

	}

}
