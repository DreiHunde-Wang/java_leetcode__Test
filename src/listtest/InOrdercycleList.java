package listtest;

/**
 * 
 * 给定循环单调非递减列表中的一个点，写一个函数向这个列表中插入一个新元素 insertVal ，使这个列表仍然是循环升序的。
 * 给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。
 * 如果有多个满足条件的插入位置，可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。
 * 如果列表为空（给定的节点是 null），需要创建一个循环有序列表并返回这个节点。否则。请返回原先给定的节点。
 * @author Dreihunde
 *
 */
public class InOrdercycleList {
	//method 1 遍历 O(n) O(1)
    public ListNode insert(ListNode head, int insertVal) {
        if(head == null) {
            head = new ListNode(insertVal);
            head.next = head;
            return head;
        }
        ListNode p = head, tmp = null;
        // tmp用来判断p指针是否走了一圈
        while(p != tmp) {           
            if(tmp == null) {
                tmp = head;
            }
            if((p.val <= insertVal && insertVal <= p.next.val) || // 如果在p和p.next的值之间则直接插入;
               (p.val > p.next.val && (insertVal <= p.next.val || insertVal >= p.val))) {      
            	ListNode node = new ListNode(insertVal, p.next);         // 如果在最大值和最小值之间,且大于最大值或者小于最小值,则插入
                p.next = node;
                return head;
            }
            p = p.next;
        }
        // 若找不到可以插入的地方,说明链表内全部值都相等,即可随便插入
        ListNode node = new ListNode(insertVal, p.next);
        p.next = node;
        return head;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
