package classdesign;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 时间复杂度 O(1) O(1) O(n) 空间复杂度 O(1) O(1) O(1)
 * @author Dreihunde
 *
 */
public class DetectSquares {
	/**
	 * 先考虑如何实现 int count(int[] point)，记输入的 point 的横纵坐标分别为 x 和 y。
	 * 则形成的正方形的上下两条边中，其中一条边的纵坐标为 y， 我们枚举另一条边的纵坐标为 col，
	 * 则正方形的边长 d 为 |y - col 且大于 0。
	 * 有了其中一个点的坐标 (x,y) 和一条横边的纵坐标 col，
	 * 我们可以得到正方形的四个点的坐标分别为(x,y)，(x,col)，(x+d,y)，(x+d,col) 
	 * 或 (x,y)，(x,col)，(x−d,y)，(x−d,col)。
	 * 据此，我们可以用一个哈希表来存储 void add(int[] point) 函数中加入的点。
	 * 先把点按照行来划分，键为行的纵坐标，值为另一个哈希表，其中键为该行中的点的横坐标，值为这样的点的个数。
	 * 因为点会重复出现，所以计算正方形的个数时需要把另外三个坐标出现的次数相乘。
	 */
	//点(x, y)的点y的点x的个数
    Map<Integer, Map<Integer, Integer>> cnt;

    public DetectSquares() {
        cnt = new HashMap<Integer, Map<Integer, Integer>>();
    }

    public void add(int[] point) {
        int x = point[0], y = point[1];
        //点y为索引
        cnt.putIfAbsent(y, new HashMap<Integer, Integer>());
        //记录点y对应的hashmap中的点x的个数
        Map<Integer, Integer> yCnt = cnt.get(y);
        yCnt.put(x, yCnt.getOrDefault(x, 0) + 1);
    }

    public int count(int[] point) {
        int res = 0;
        int x = point[0], y = point[1];
        if (!cnt.containsKey(y)) {
            return 0;
        }
        Map<Integer, Integer> yCnt = cnt.get(y);
        Set<Map.Entry<Integer, Map<Integer, Integer>>> entries = cnt.entrySet();
        for (Map.Entry<Integer, Map<Integer, Integer>> entry : entries) {
            int col = entry.getKey();
            Map<Integer, Integer> colCnt = entry.getValue();
            if (col != y) {
                // 根据对称性，这里可以不用取绝对值
                int d = col - y;
                res += colCnt.getOrDefault(x, 0) * yCnt.getOrDefault(x + d, 0) * colCnt.getOrDefault(x + d, 0);
                res += colCnt.getOrDefault(x, 0) * yCnt.getOrDefault(x - d, 0) * colCnt.getOrDefault(x - d, 0);
            }
        }
        return res;
    }
}