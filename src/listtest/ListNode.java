package listtest;

class ListNode {
	public int val;
	public ListNode next;
	
	public ListNode() {
		
	}
	
	public ListNode(int x) {
		val = x;
	}
	
	public static void printNode(ListNode head) {
    	while (head != null) {
    		System.out.print(head.val + " ");
    		head = head.next;
    	}
    	System.out.println();
    }

}
