package src.listtest;

import src.numsearch.FindMinSubMoreThanTarget;

/**
 * 多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。
 * 这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。
 * 给定位于列表第一级的头节点，请扁平化列表，即将这样的多级双向链表展平成普通的双向链表，使所有结点出现在单级双链表中。
 * @author Dreihunde
 *
 */
public class FlattenMultiRankListNode {
	//method 1 迭代回溯 O(n) O(n)栈深度取决于child的深度
    public Node flatten1(Node head) {
        if (head == null) {
            return head;
        }
        Node tail = head;
        while (tail != null) {
            //当发现节点有子节点是，链接子节点
            if (tail.child != null) {
                tail = linkChildList(tail);
                if (tail == null) {
                    break;
                }
            }
            tail = tail.next;
        }
        
        return head;    
    }

    private Node linkChildList(Node head) {
        //保存当前节点，当前节点的子节点和当前节点的下一个节点，按照当前节点，子节点和下一个节点的顺序链接
        Node cur = head;
        Node next = cur.next;
        Node child = cur.child;
        cur.next = child;
        child.prev = cur;
        cur.child = null;
        while (child != null) {
            if (child.child != null) {
                child = linkChildList(child);
            }
            if (child.next == null) {
                break;
            }
            child = child.next;
        }

        if (next != null && child.next == null) {
                child.next = next;
                next.prev = child;
                next = null;
            }

        return child;
    }
    
    //method 2 dfs O(n) O(1)
    public Node flatten2(Node head) {
        dfs(head);
        return head;
    }

    public Node dfs(Node node) {
        Node cur = node;
        // 记录链表的最后一个节点
        Node last = null;

        while (cur != null) {
            Node next = cur.next;
            //  如果有子节点，那么首先处理子节点
            if (cur.child != null) {
                Node childLast = dfs(cur.child);

                next = cur.next;
                //  将 node 与 child 相连
                cur.next = cur.child;
                cur.child.prev = cur;

                //  如果 next 不为空，就将 last 与 next 相连
                if (next != null) {
                    childLast.next = next;
                    next.prev = childLast;
                }

                // 将 child 置为空
                cur.child = null;
                last = childLast;
            } else {
                last = cur;
            }
            cur = next;
        }
        return last;
    }



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node head = new Node(1);
		head.addChildNode(new Node(2));
		head.child.addChildNode(new Node(3));
		
		FlattenMultiRankListNode fm = new FlattenMultiRankListNode();
		Node.printNode(fm.flatten1(head));
		
		head.addChildNode(new Node(2));
		head.child.addChildNode(new Node(3));
		Node.printNode(fm.flatten2(head));
		

	}

}

class Node {
	int val;
	Node prev;
	Node next;
	Node child;
	
	public Node() {
		
	}
	
	public Node(int x) {
		val = x;
	}
	
	public void linkNextNode(Node next) {
		this.next = next;
		this.next.prev = this;
	}
	
	public void addChildNode(Node child) {
		this.child = child;
	}
	
	public static void printNode(Node head) {
		while (head != null) {
			System.out.print(head.val + " ");
			head = head.next;
		}
		System.out.println();
	}
}