package src.printtreenode;

import java.util.HashMap;
import java.util.Map;

public class CopyRandomList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
class RNode {
	int val;
	RNode next;
	RNode random;
	
	public RNode(int val) {
		this.val = val;
		this.next = null;
		this.random = null;
	}
}

class CRLSolution1 {    
    Map<RNode, RNode> map = new HashMap<>();
    public RNode copyRandomList1(RNode head) {
        if (head == null) {
            return null;
        }

        if (!map.containsKey(head)) {
        	RNode newHead = new RNode(head.val);
            map.put(head, newHead);
            newHead.next = copyRandomList1(head.next);
            newHead.random = copyRandomList1(head.random);
        }
        return map.get(head);
    }
}

//method 2 节点拆分 O(n) O(1)
class CRLSolution2 {
    public RNode copyRandomList(RNode head) {
        if (head == null) {
            return null;
        }
        //原始链表中插入newNode
        for (RNode node = head; node != null; node = node.next.next) {
        	RNode nodeNew = new RNode(node.val);
            nodeNew.next = node.next;
            node.next = nodeNew;
        }
        //给newNode链接random
        for (RNode node = head; node != null; node = node.next.next) {
        	RNode nodeNew = node.next;
            nodeNew.random = (node.random != null) ? node.random.next : null;
        }
        RNode headNew = head.next;
        //重新拆分
        for (RNode node = head; node != null; node = node.next) {
        	RNode nodeNew = node.next;
            node.next = node.next.next;
            nodeNew.next = (nodeNew.next != null) ? nodeNew.next.next : null;
        }
        return headNew;
    }
}
