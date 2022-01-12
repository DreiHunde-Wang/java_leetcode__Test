package listtest;

public class InOrdercycleList {
	//method 1 ���� O(n) O(1)
    public ListNode insert(ListNode head, int insertVal) {
        if(head == null) {
            head = new ListNode(insertVal);
            head.next = head;
            return head;
        }
        ListNode p = head, tmp = null;
        // tmp�����ж�pָ���Ƿ�����һȦ
        while(p != tmp) {           
            if(tmp == null) {
                tmp = head;
            }
            if((p.val <= insertVal && insertVal <= p.next.val) || // �����p��p.next��ֵ֮����ֱ�Ӳ���;
               (p.val > p.next.val && (insertVal <= p.next.val || insertVal >= p.val))) {      
            	ListNode node = new ListNode(insertVal, p.next);         // ��������ֵ����Сֵ֮��,�Ҵ������ֵ����С����Сֵ,�����
                p.next = node;
                return head;
            }
            p = p.next;
        }
        // ���Ҳ������Բ���ĵط�,˵��������ȫ��ֵ�����,����������
        ListNode node = new ListNode(insertVal, p.next);
            p.next = node;
        return head;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
