package src.printtreenode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 给你�?个数组 pairs ，其中 pairs[i] = [xi, yi] ，并且满足：
 * pairs 中没有重复元�?
 * xi < yi
 * 令 ways 为满足下面条件的有根树的方案数：
 * 树所包含的所有节点�?�都�? pairs 中�??
 * �?个数对 [xi, yi] 出现在 pairs 中 当且仅当 xi 是 yi 的祖先或者 yi 是 xi 的祖先�??
 * 注意：构造出来的树不�?定是二叉树�??
 * 两棵树被视为不同的方案当存在至少�?个节点在两棵树中有不同的父节点�??
 * 请你返回�?
 * 如果 ways == 0 ，返回�?0 �?
 * 如果 ways == 1 ，返�? 1 �?
 * 如果 ways > 1 ，返回�?2 �?
 * �?�? 有根树 指的是只有�?个根节点的树，所有边都是从根�?外的方向�?
 * 我们称从根到�?个节点路径上的任意一个节点（除去节点本身）都是该节点�? 祖先 。根节点没有祖先�?
 * 链接：https://leetcode-cn.com/problems/number-of-ways-to-reconstruct-a-tree
 * @author Dreihunde
 *
 */
public class CheckRestructTreeWays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

//method 1 构�??+验证 O(n^2) O(n^2) 令n�? pairs 的长度，统计度数和点集复杂度为O(n)；最多有 2 * n个点，将点根据数进行排序复杂度为O(nlogn)
class Solution1 {
  int N = 510;
  int[] cnts = new int[N], fa = new int[N];
  boolean[][] g = new boolean[N][N];
  public int checkWays(int[][] pairs) {
      int m = pairs.length;
      Set<Integer> set = new HashSet<>();
      for (int[] p : pairs) {
          int a = p[0], b = p[1];
          g[a][b] = g[b][a] = true;
          cnts[a]++; cnts[b]++;
          set.add(a); set.add(b);
      }
      List<Integer> list = new ArrayList<>(set);
      Collections.sort(list, (a,b)->cnts[b]-cnts[a]);
      int n = list.size(), root = list.get(0);
      if (m < n - 1) return 0; // 森林
      fa[root] = -1;
      for (int i = 1; i < n; i++) {
          int a = list.get(i);
          boolean ok = false;
          for (int j = i - 1; j >= 0 && !ok; j--) {
              int b = list.get(j);
              if (g[a][b]) {
                  fa[a] = b;
                  ok = true;
              }
          }
          if (!ok) return 0;
      }
      int c = 0, ans = 1;
      for (int i : set) {
          int j = i;
          while (fa[j] != -1) {
              if (!g[i][fa[j]]) return 0;
              if (cnts[i] == cnts[fa[j]]) ans = 2;
              c++;
              j = fa[j];
          }
      }
      return c < m ? 0 : ans;
  }
}

//method 2 直接模拟 O(m + n^2) O(m) 其中n为树中节点的数目，m 表示数组 pairs 的长度�?�需要遍历pairs ，时间复杂度为O(m)
class Solution {
  public int checkWays(int[][] pairs) {
      Map<Integer, Set<Integer>> adj = new HashMap<Integer, Set<Integer>>();
      for (int[] p : pairs) {
          adj.putIfAbsent(p[0], new HashSet<Integer>());
          adj.putIfAbsent(p[1], new HashSet<Integer>());
          adj.get(p[0]).add(p[1]);
          adj.get(p[1]).add(p[0]);
      }
      /* �?测是否存在根节点*/
      int root = -1;
      Set<Map.Entry<Integer, Set<Integer>>> entries = adj.entrySet();
      for (Map.Entry<Integer, Set<Integer>> entry : entries) {
          int node = entry.getKey();
          Set<Integer> neighbours = entry.getValue();
          if (neighbours.size() == adj.size() - 1) {
              root = node;
          }
      }
      if (root == -1) {
          return 0;
      }

      int res = 1;
      for (Map.Entry<Integer, Set<Integer>> entry : entries) {
          int node = entry.getKey();
          Set<Integer> neighbours = entry.getValue();
          if (node == root) {
              continue;
          }
          int currDegree = neighbours.size();
          int parent = -1;
          int parentDegree = Integer.MAX_VALUE;

          /* 根据 degree 的大小找�? node 的父节点 parent */
          for (int neighbour : neighbours) {
              if (adj.get(neighbour).size() < parentDegree && adj.get(neighbour).size() >= currDegree) {
                  parent = neighbour;
                  parentDegree = adj.get(neighbour).size();
              }
          }
          if (parent == -1) {
              return 0;
          }

          /* �?�? neighbours 是否�? adj[parent] 的子�? */
          for (int neighbour : neighbours) {
              if (neighbour == parent) {
                  continue;
              }
              if (!adj.get(parent).contains(neighbour)) {
                  return 0;
              }
          }
          if (parentDegree == currDegree) {
              res = 2;
          }
      }
      return res;
  }
}
