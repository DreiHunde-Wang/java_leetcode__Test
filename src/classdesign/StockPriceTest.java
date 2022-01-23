package classdesign;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 给你一支股票价格的数据流。数据流中每一条记录包含一个 时间戳 和该时间点股票对应的 价格 。
 * 不巧的是，由于股票市场内在的波动性，股票价格记录可能不是按时间顺序到来的。某些情况下，有的记录可能是错的。
 * 如果两个有相同时间戳的记录出现在数据流中，前一条记录视为错误记录，后出现的记录 更正 前一条错误的记录。
 * 请你设计一个算法，实现：
 * 更新 股票在某一时间戳的股票价格，如果有之前同一时间戳的价格，这一操作将 更正 之前的错误价格。
 * 找到当前记录里 最新股票价格 。最新股票价格 定义为时间戳最晚的股票价格。
 * 找到当前记录里股票的 最高价格 。
 * 找到当前记录里股票的 最低价格 。
 * 请你实现 StockPrice 类：
 * StockPrice() 初始化对象，当前无股票价格记录。
 * void update(int timestamp, int price) 在时间点 timestamp 更新股票价格为 price 。
 * int current() 返回股票 最新价格 。
 * int maximum() 返回股票 最高价格 。
 * int minimum() 返回股票 最低价格 。
 * @author Dreihunde
 *
 */
public class StockPriceTest {
	public static void main(String[] args) {
		StockPrice sp = new StockPrice();
		StockPrice1 sp1 = new StockPrice1();
		
		int[][] updates = new int[][] {{1, 2}, {2, 10}};
		for (int[] up: updates) {
			sp.update(up[0], up[1]);
			sp1.update(up[0], up[1]);
		}
		
		System.out.println(sp.current() + " " + sp.maximum() + " " + sp.minimum());
		System.out.println(sp1.current() + " " + sp1.maximum() + " " + sp1.minimum());
	}
}


//method 1 HashMap + TreeMap update O(logn)其他O(1)  O(n)
class StockPrice1 {
  int cur;
  //timestamp, prices
  Map<Integer, Integer> map;
  //prices, cnt
  TreeMap<Integer, Integer> ts;

  public StockPrice1() {
      cur = -1;
      map = new HashMap<>();
      ts = new TreeMap<>();
  }

  public void update(int timestamp, int price) {
      cur = Math.max(cur, timestamp);
      //如果已经存在该时间
      if (map.containsKey(timestamp)) {
          //取出旧的价格
          int old = map.get(timestamp);
          //在TreeMap获取旧的价格对应的个数
          int cnt = ts.get(old);
          //如果只有一个就删除该价格
          if (cnt == 1) ts.remove(old);
          //否则减少一个旧价格计数
          else ts.put(old, cnt - 1);
      }

      //更新新值
      map.put(timestamp, price);
      //对应的价格计数+1
      ts.put(price, ts.getOrDefault(price, 0) + 1);
  }
  
  public int current() {
      return map.get(cur);
  }
  
  public int maximum() {
      return ts.lastKey();
  }
  
  public int minimum() {
      return ts.firstKey();
  }
}

//method 2 Hashmap + 两个优先队列 update O(logn)其他O(1) O(n)
class StockPrice {
  int cur;
  Map<Integer, Integer> map = new HashMap<>();
  PriorityQueue<int[]> maxHeap;
  PriorityQueue<int[]> minHeap;
  public StockPrice() {
      cur = -1;
      map = new HashMap<Integer, Integer>();
      maxHeap = new PriorityQueue<int[]>((a, b) -> b[0] - a[0]);
      minHeap = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
  }

  public void update(int timestamp, int price) {
      cur = Math.max(cur, timestamp);
      map.put(timestamp, price);
      maxHeap.offer(new int[]{price, timestamp});
      minHeap.offer(new int[]{price, timestamp});
  }
  
  public int current() {
      return map.get(cur);
  }
  
  public int maximum() {
      while (true) {
          int[] top = maxHeap.peek();
          int topPrice = top[0];
          int topTime = top[1];
          //如果堆顶的值与map记录的值对的上，则返回，否则删除堆顶
          if (map.get(topTime) == topPrice) {
              return topPrice;
          }
          maxHeap.poll();
      }
  }
  
  public int minimum() {
      while (true) {
          int[] top = minHeap.peek();
          int topPrice = top[0];
          int topTime = top[1];
          //如果堆顶的值与map记录的值对的上，则返回，否则删除堆顶
          if (map.get(topTime) == topPrice) {
              return topPrice;
          }
          minHeap.poll();
      }
  }
}