package listtest;

/**
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 * 返回删除后的链表的头节点。
 * @author Dreihunde
 *
 */
public class DeleteNode {
	//method 1 pre.next = cur.next
    public ListNode deleteNode1(ListNode head, int val) {
        if (head.val == val)
            return head.next;
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            
            if (cur.val == val) {
                pre.next = cur.next;
                break;
            } else {
                pre = cur;
                cur = cur.next;
            }
 
        }
        return head;
    }

    //method 2 cur.next = cur.next.next;
    public ListNode deleteNode2(ListNode head, int val) {
        if (head.val == val)
            return head.next;
        ListNode cur = head;

        while (cur.next != null) {
            
            if (cur.next.val == val) {
                cur.next = cur.next.next;
                break;
            } else {
                cur = cur.next;
            }
 
        }
        return head;
    }
    
    private static void printNode(ListNode head) {
    	while (head != null) {
    		System.out.print(head.val + " ");
    		head = head.next;
    	}
    	System.out.println();
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode head = new ListNode(1);
		ListNode temp = head;
		for (int i = 2; i <= 5; i++) {
			temp.next = new ListNode(i);
			temp = temp.next;
		}
		int val1 = 3;
		int val2 = 5;
		
		DeleteNode dn = new DeleteNode();
		long startTime = System.nanoTime();
		printNode(dn.deleteNode1(head, val1));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		printNode(dn.deleteNode2(head, val2));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");


	}

}
