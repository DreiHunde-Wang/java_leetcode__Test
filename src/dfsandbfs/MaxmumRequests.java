package src.dfsandbfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 我们有 n 栋楼，编号从 0 到 n - 1 。每栋楼有若干员工�?�由于现在是换楼的季节，部分员工想要换一栋楼居住�?
 * 给你�?个数�? requests ，其中 requests[i] = [fromi, toi] ，表示一个员工请求从编号为 fromi 的楼搬到编号为 toi 的楼�?
 * �?�?始 所有楼都是满的，所以从请求列表中�?�出的若干个请求是可行的�?要满�? 每栋楼员工净变化�? 0 �?
 * 意�?�是每栋�? 离开 的员工数�? 等于 该楼 搬入 的员工数数目�?
 * 比方说 n = 3 且两个员工要离开楼�?0 ，一个员工要离开楼�?1 ，一个员工要离开�? 2 ，如果该请求列表可行，应该要有两个员工搬入楼 0 ，一个员工搬入楼 1 ，一个员工搬入楼 2 �?
 * 请你从原请求列表中�?�出若干个请求，使得它们是一个可行的请求列表，并返回�?有可行列表中�?大请求数目�??
 * 链接：https://leetcode-cn.com/problems/maximum-number-of-achievable-transfer-requests
 * @author Dreihunde
 *
 */
public class MaxmumRequests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

//method 1 dfs + 回溯 O(2^m) O(n + m) m = requests.length
class MRSolution1 {
  int maxCount;
  //记录每栋楼的状�??
  List<Building> list;
  public int maximumRequests1(int n, int[][] requests) {
      this.maxCount = 0;
      this.list = new ArrayList<>();
      for (int i = 0; i < n; i++) {
          list.add(new Building(i));
      }
      dfs(requests, 0, 0);
      return maxCount;

  }
  private void dfs(int[][] requests, int index, int count) {
      if (index == requests.length) {
          //当每栋楼都满足出入相等时，更新最大�??
          if (isBalance(list)) {
              this.maxCount = Math.max(maxCount, count);
          }
          return;
      }
      int out = requests[index][0];
      int in = requests[index][1];
      list.get(out).out++;
      list.get(in).in++;
      //选择这条请求，count + 1
      dfs(requests, index + 1, count + 1);
      list.get(out).out--;
      list.get(in).in--;
      //不�?�择这条请求 count不变
      dfs(requests, index + 1, count);
  }

  private boolean isBalance(List<Building> builds) {
      for (int i = 0; i < builds.size(); i++) {
          if (builds.get(i).in != builds.get(i).out) {
              return false;
          }
      }
      return true;
  }
}

class Building {
  int id;
  int in;
  int out;

  public Building() {

  }

  public Building(int _id) {
      this.id = _id;
  }
}

//method 2 二进制枚�? O(n * 2^m) O(n)
/**
 * 我们可以使用�?个长度为 m 的二进制数mask 表示�?有的请求，其�? mask 从低到高的第 i 位为 1 表示选择�? i 个请求，�? 0 表示不�?�第 i 个请求�??
 * 我们可以枚举[0,2^m�?1] 范围内的�?有mask，对于每个mask，依次枚举其每一位，判断是否�?1，并使用与方法一相同的数�? delta 以及变量 cnt 进行统计，在满足要求时更新答案�??
 */
class MRSolution {
  public int maximumRequests(int n, int[][] requests) {
      int[] delta = new int[n];
      int ans = 0, m = requests.length;
      for (int mask = 0; mask < (1 << m); ++mask) {
          int cnt = Integer.bitCount(mask);
          if (cnt <= ans) {
              continue;
          }
          Arrays.fill(delta, 0);
          for (int i = 0; i < m; ++i) {
              if ((mask & (1 << i)) != 0) {
                  ++delta[requests[i][0]];
                  --delta[requests[i][1]];
              }
          }
          boolean flag = true;
          for (int x : delta) {
              if (x != 0) {
                  flag = false;
                  break;
              }
          }
          if (flag) {
              ans = cnt;
          }
      }
      return ans;
  }
}