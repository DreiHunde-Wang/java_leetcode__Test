package src.printtreenode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你�?棵根节点�? 0 的 二叉树 ，它总共�? n 个节点，节点编号为�?0 到 n - 1 �?
 * 同时给你�?个下标从 0 �?始的整数数组 parents 表示这棵树，其中 parents[i] 是节�? i 的父节点�?
 * 由于节点 0 是根，所以 parents[0] == -1 �?
 * �?个子树的 大小 为这个子树内节点的数目�?�每个节点都有一个与之关联的 分数 �?
 * 求出某个节点分数的方法是，将这个节点和与它相连的边全�? 删除 ，剩余部分是若干�? 非空 子树，这个节点的 分数 为所有这些子�? 大小的乘积 �??
 * 请你返回�? �?高得分 节点的 数目 �?
 * 链接：https://leetcode-cn.com/problems/count-nodes-with-the-highest-score
 * @author Dreihunde
 *
 */
public class CountHighestScoreNodes {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}


//method1 模拟 O(n) O(n)
class HSSolution1 {
	class Node {
		  int id;
		  List<Node> childrens;

		  public Node(int _id) {
		      this.id = _id;
		      childrens = new ArrayList<>();
		  }

		  public int getChildNum() {
		      int sum = 0;
		      for (int i = 0; i < childrens.size(); i++) {
		          sum += childrens.get(i).getChildNum();
		      }
		      return sum + 1;
		  }


		}
  public int countHighestScoreNodes(int[] parents) {
      int n = parents.length;
      if (n <= 1) {
          return 0;
      }
      Map<Integer, Node> map = new HashMap<>();
      for (int i = 0; i < n; i++) {
          map.put(i, new Node(i));
      }
      for (int i = 1; i < n; i++) {
          Node p = map.get(parents[i]);
          p.childrens.add(map.get(i));
      }

      int[] maxScore = new int[2];
      for (int i = 0; i < n; i++) {
          Node p = map.get(i);
          int len = p.childrens.size();
          int childScore = 0;
          int resScore = n;
          int score = 1;
          for (int j = 0; j < len; j++) {
              childScore = p.childrens.get(j).getChildNum();
              resScore -= childScore;
              score *= Math.max(childScore, 1);
          }
          score *= Math.max(resScore, 1);
          if (score > maxScore[0]) {
              maxScore[0] = score;
              maxScore[1] = 1;
          }  else if (score == maxScore[0]) {
              maxScore[1]++; 
          }
          
      }
      return maxScore[1];

  }
}



//method 2 dfs O(n) O(n)
class HSSolution2 {
  long maxScore = 0;
  int cnt = 0;
  int n;
  List<Integer>[] children;

  public int countHighestScoreNodes(int[] parents) {
      n = parents.length;
      children = new List[n];
      for (int i = 0; i < n; i++) {
          children[i] = new ArrayList<Integer>();
      }
      for (int i = 0; i < n; i++) {
          int p = parents[i];
          if (p != -1) {
              children[p].add(i);
          }
      }
      dfs(0);
      return cnt;
  }

  public int dfs(int node) {
      long score = 1;
      int size = n - 1;
      for (int c : children[node]) {
          int t = dfs(c);
          score *= t;
          size -= t;
      }
      if (node != 0) {
          score *= size;
      }
      if (score == maxScore) {
          cnt++;
      } else if (score > maxScore) {
          maxScore = score;
          cnt = 1;
      }
      return n - size;
  }
}