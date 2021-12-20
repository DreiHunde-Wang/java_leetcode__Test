package numsearch;

import java.util.Arrays;

/**
 * 冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
 * 在加热器的加热半径范围内的每个房屋都可以获得供暖。
 * 现在，给出位于一条水平线上的房屋 houses 和供暖器 heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。
 * 说明：所有供暖器都遵循你的半径标准，加热的半径也一样。
 * @author Dreihunde
 *
 */
public class FindRadius {
	//method 1 双指针 O(nlogn + mlogm) O(logn + logm)
    public int findRadius(int[] houses, int[] heaters) {
        int n = houses.length;
        int m = heaters.length;
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int ans = 0;
        int curdis = 0;
        int heatIdx = 0;
        for (int i = 0; i < n; i++) {
            curdis = Math.abs(houses[i] - heaters[heatIdx]);
            while (heatIdx < m - 1 && Math.abs(houses[i] - heaters[heatIdx]) >= Math.abs(houses[i] - heaters[heatIdx + 1])) {
                heatIdx++;
                curdis = Math.min(curdis, Math.abs(houses[i] - heaters[heatIdx]));
            }
            ans = Math.max(ans, curdis);
        }
        return ans;

    }
}
