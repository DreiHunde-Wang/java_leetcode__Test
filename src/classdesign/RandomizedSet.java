package src.classdesign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构：
 * insert(val)：当元素 val 不存在时返回 true ，并向集合中插入该项，否则返回 false 。
 * remove(val)：当元素 val 存在时返回 true ，并从集合中移除该项，否则返回 false 。
 * getRandom：随机返回现有集合中的一项。每个元素应该有 相同的概率 被返回。
 * @author Dreihunde
 *
 */
//method 1 list存储数值，map存储索引位置 O(1) O(n)
class RandomizedSet {
  List<Integer> list;
  Map<Integer, Integer> map;
  int lastIndex;
  Random rand;
  /** Initialize your data structure here. */
  public RandomizedSet() {
      list = new ArrayList<>();
      map = new HashMap<>();
      lastIndex = -1;
      rand = new Random();
  }
  
  /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
  public boolean insert(int val) {
      if (map.containsKey(val)) {
          return false;
      }
      list.add(val);
      lastIndex++;
      map.put(val, lastIndex);
      
      return true;
  }
  
  /** Removes a value from the set. Returns true if the set contained the specified element. */
  public boolean remove(int val) {
      if (list.isEmpty() || !map.containsKey(val)) {
          return false;
      }
      int idx = map.get(val);
      int lastNum = list.get(lastIndex);
      list.set(idx, lastNum);
      map.put(lastNum, idx);
      list.remove(lastIndex);
      map.remove(val);
      lastIndex--;
      return true;
  }
  
  /** Get a random element from the set. */
  public int getRandom() {
      int randIdx = rand.nextInt(lastIndex + 1);
      return list.get(randIdx);
  }
}
