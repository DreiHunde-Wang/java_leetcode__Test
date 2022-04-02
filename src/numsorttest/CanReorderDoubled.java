package numsorttest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个长度为偶数的整数数组 arr，只有对 arr 进行重组后可以满足 “对于每个 0 <= i < len(arr) / 2，
 * 都有 arr[2 * i + 1] = 2 * arr[2 * i]” 时，返回 true；否则，返回 false。
 * 链接：https://leetcode-cn.com/problems/array-of-doubled-pairs
 * @author Dreihunde
 *
 */
public class CanReorderDoubled {
	//method 1 map统计 + list排序 O(nlogn) O(n)
    public boolean canReorderDoubled(int[] arr) {
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        for (int x : arr) {
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
        }
        if (cnt.getOrDefault(0, 0) % 2 != 0) {
            return false;
        }

        List<Integer> vals = new ArrayList<Integer>();
        for (int x : cnt.keySet()) {
            vals.add(x);
        }
        Collections.sort(vals, (a, b) -> Math.abs(a) - Math.abs(b));

        for (int x : vals) {
            if (cnt.getOrDefault(2 * x, 0) < cnt.get(x)) { // 无法找到足够的 2x 与 x 配对
                return false;
            }
            cnt.put(2 * x, cnt.getOrDefault(2 * x, 0) - cnt.get(x));
        }
        return true;
    }

}
