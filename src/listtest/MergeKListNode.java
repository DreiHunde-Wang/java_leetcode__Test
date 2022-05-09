package src.listtest;

import java.util.PriorityQueue;

/**
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 * @author Dreihunde
 *
 */
public class MergeKListNode {
	//method 1 优先队列 O(knlogk) O(k) k为lists.length
    class Status {
        int val;
        ListNode node;
        Status(int _val, ListNode _node) {
            this.val = _val;
            this.node = _node;
        }
    }
    public ListNode mergeKLists1(ListNode[] lists) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        PriorityQueue<Status> heap = new PriorityQueue<>((a, b) -> (a.val - b.val));
        int k = lists.length;
        if (k == 0) {
            return null;
        }
        for (int i = 0; i < k; i++) {
            if (lists[i] != null) {
                heap.offer(new Status(lists[i].val, lists[i]));
            }
        }
        while (!heap.isEmpty()) {
            Status t = heap.poll();
            cur.next = t.node;
            cur = cur.next;
            if (t.node.next != null) {
                heap.offer(new Status(t.node.next.val, t.node.next));
            }
        }
        return pre.next;
    }

    //method 2 朴素合并 O(nk^2) O(1)
    public ListNode mergeKLists2(ListNode[] lists) {
        ListNode ans = null;
        for (int i = 0; i < lists.length; i++) {
            ans = mergeTwoLists(ans, lists[i]);
        }
        return ans;
    }

    private ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a == null ? b : a;
        }
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        ListNode aPtr = a;
        ListNode bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val <= bPtr.val) {
                cur.next = aPtr;
                aPtr = aPtr.next;
            } else {
                cur.next = bPtr;
                bPtr = bPtr.next;
            }
            cur = cur.next;
        }
        cur.next = (aPtr == null ? bPtr : aPtr);
        return pre.next;
    }

    //method 3 分治合并 O(nklogn) O(logk)
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return mergeOfLists(lists, 0, lists.length - 1);
    }
    private ListNode mergeOfLists(ListNode[] lists, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return lists[start];
        }
        int mid = start + (end - start) / 2;
        return mergeTwoLists(mergeOfLists(lists, start, mid), mergeOfLists(lists, mid + 1, end));
    }
    
    private ListNode numsToListNode(int[] nums) {
		ListNode pre = new ListNode(0);
		ListNode cur = pre;
		for (int num : nums) {
			cur.next = new ListNode(num);
			cur = cur.next;
		}
		return pre.next;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MergeKListNode mk = new MergeKListNode();
		ListNode[] lists = new ListNode[3];
		lists[0] = null;
		int[] nums1 = new int[] {1, 2, 3, 4, 5};
		lists[1] = mk.numsToListNode(nums1);
		int[] nums2 = new int[] {2, 4, 6};
		lists[2] = mk.numsToListNode(nums2);
		
		ListNode ans1 = mk.mergeKLists1(lists);
		while (ans1 != null) {
			System.out.print(ans1.val + " ");
			ans1 = ans1.next;
		}
		

	}

}
