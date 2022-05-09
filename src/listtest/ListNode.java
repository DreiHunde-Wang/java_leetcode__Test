package src.listtest;

class ListNode {
	public int val;
	public ListNode next;
	
	public ListNode() {
		
	}
	
	public ListNode(int x) {
		val = x;
	}
	
	public ListNode(int x, ListNode next) {
		val = x;
		this.next = next;
	}
	
	public static ListNode numToListNode(int[] nums) {
		if (nums.length == 0) {
			return null;
		}
		ListNode head = new ListNode(nums[0]);
		
		ListNode cur = head;
		for (int i = 1; i < nums.length; i++) {
			ListNode node = new ListNode(nums[i]);
			cur.next = node;
			cur = cur.next;
		}
		return head;
	}
	public static void printNode(ListNode head) {
    	while (head != null) {
    		System.out.print(head.val + " ");
    		head = head.next;
    	}
    	System.out.println();
    }

}
