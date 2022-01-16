package listtest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 给你一个单链表，随机选择链表的一个节点，并返回相应的节点值。每个节点 被选中的概率一样 。
 * 实现 Solution 类：
 * Solution(ListNode head) 使用整数数组初始化对象。
 * int getRandom() 从链表中随机选择一个节点并返回该节点的值。链表中所有节点被选中的概率相等。
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


//method 1 保存到链表 初始化时间复杂度O(n) 选择O(1)  空间 O(n)
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

//method 2 流水抽样 初始化时间复杂度O(1) 每次抽取 O(n) 空间复杂度O(1)
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
          if (random.nextInt(i) == 0) { // 1/i 的概率选中（替换为答案）
              ans = node.val;
          }
          ++i;
      }
      return ans;
  }
}