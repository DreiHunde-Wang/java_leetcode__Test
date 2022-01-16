package listtest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ����һ�����������ѡ�������һ���ڵ㣬��������Ӧ�Ľڵ�ֵ��ÿ���ڵ� ��ѡ�еĸ���һ�� ��
 * ʵ�� Solution �ࣺ
 * Solution(ListNode head) ʹ�����������ʼ������
 * int getRandom() �����������ѡ��һ���ڵ㲢���ظýڵ��ֵ�����������нڵ㱻ѡ�еĸ�����ȡ�
 * @author Dreihunde
 *
 */
public class RandomChoiceNode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] num = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
		ListNode head = ListNode.numToListNode(num);
		Solution1 s1 = new Solution1(head);
		System.out.println(s1.getRandom());
		
		Solution2 s2 = new Solution2(head);
		System.out.println(s2.getRandom());
		

	}

}


//method 1 ���浽���� ��ʼ��ʱ�临�Ӷ�O(n) ѡ��O(1)  �ռ� O(n)
class Solution1 {
  List<ListNode> list;
  int size;
  Random rand;
  public Solution1(ListNode head) {
      list = new ArrayList<>();
      ListNode tail = head;
      while (tail != null) {
          list.add(tail);
          tail = tail.next;
      }
      size = list.size();
      rand = new Random();
  }
  
  public int getRandom() {
      int idx = rand.nextInt(size);
      return list.get(idx).val;
  }
}

//method 2 ��ˮ���� ��ʼ��ʱ�临�Ӷ�O(1) ÿ�γ�ȡ O(n) �ռ临�Ӷ�O(1)
class Solution2 {
  ListNode head;
  Random random;

  public Solution2(ListNode head) {
      this.head = head;
      random = new Random();
  }

  public int getRandom() {
      int i = 1, ans = 0;
      for (ListNode node = head; node != null; node = node.next) {
          if (random.nextInt(i) == 0) { // 1/i �ĸ���ѡ�У��滻Ϊ�𰸣�
              ans = node.val;
          }
          ++i;
      }
      return ans;
  }
}