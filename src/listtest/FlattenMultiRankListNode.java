package src.listtest;

import src.numsearch.FindMinSubMoreThanTarget;

/**
 * �༶˫�������У�����ָ����һ���ڵ��ǰһ���ڵ�ָ��֮�⣬������һ��������ָ�룬����ָ�򵥶���˫������
 * ��Щ���б�Ҳ���ܻ���һ�������Լ�������������ƣ����ɶ༶���ݽṹ���������ʾ����ʾ��
 * ����λ���б��һ����ͷ�ڵ㣬���ƽ���б����������Ķ༶˫������չƽ����ͨ��˫������ʹ���н������ڵ���˫�����С�
 * @author Dreihunde
 *
 */
public class FlattenMultiRankListNode {
	//method 1 �������� O(n) O(n)ջ���ȡ����child�����
    public Node flatten1(Node head) {
        if (head == null) {
            return head;
        }
        Node tail = head;
        while (tail != null) {
            //�����ֽڵ����ӽڵ��ǣ������ӽڵ�
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
        //���浱ǰ�ڵ㣬��ǰ�ڵ���ӽڵ�͵�ǰ�ڵ����һ���ڵ㣬���յ�ǰ�ڵ㣬�ӽڵ����һ���ڵ��˳������
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
        // ��¼��������һ���ڵ�
        Node last = null;

        while (cur != null) {
            Node next = cur.next;
            //  ������ӽڵ㣬��ô���ȴ����ӽڵ�
            if (cur.child != null) {
                Node childLast = dfs(cur.child);

                next = cur.next;
                //  �� node �� child ����
                cur.next = cur.child;
                cur.child.prev = cur;

                //  ��� next ��Ϊ�գ��ͽ� last �� next ����
                if (next != null) {
                    childLast.next = next;
                    next.prev = childLast;
                }

                // �� child ��Ϊ��
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