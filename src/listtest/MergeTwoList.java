package listtest;

/**
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 * @author Dreihunde
 *
 */
public class MergeTwoList {
	
	//method 1 递归
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        if (l1.val > l2.val) {
            l2.next = mergeTwoLists1(l1, l2.next);
            return l2;
        } else {
            l1.next =mergeTwoLists1(l1.next, l2);
            return l1;
        }

    }

    //method 2 迭代
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode rev = new ListNode(0);
        ListNode cur = rev;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                cur.next = l2;
                l2 = l2.next;
            } else {
                cur.next = l1;
                l1 = l1.next;
            }
            cur = cur.next;
        }
        cur.next = (l1 == null) ? l2 : l1;
        return rev.next;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MergeTwoList mt = new MergeTwoList();
		ListNode head1 = new ListNode(1);
		ListNode head2 = new ListNode(3);
		ListNode temp1 = head1;
		ListNode temp2 = head2;
		for (int i = 2; i <= 5; i++) {
			temp1.next = new ListNode(i);
			temp2.next = new ListNode(2 + i);
			temp1 = temp1.next;
			temp2 = temp2.next;
		}
		
		long startTime = System.nanoTime();
		ListNode.printNode(mt.mergeTwoLists1(head1, head2));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		head1 = new ListNode(1);
		head2 = new ListNode(3);
		temp1 = head1;
		temp2 = head2;
		for (int i = 2; i <= 5; i++) {
			temp1.next = new ListNode(i);
			temp2.next = new ListNode(2 + i);
			temp1 = temp1.next;
			temp2 = temp2.next;
		}
		
		startTime = System.nanoTime();
		ListNode.printNode(mt.mergeTwoLists2(head1, head2));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}
