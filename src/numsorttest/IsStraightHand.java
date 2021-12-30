package numsorttest;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Alice 手中有一把牌，她想要重新排列这些牌，分成若干组，使每一组的牌数都是 groupSize ，并且由 groupSize 张连续的牌组成。
 * 给你一个整数数组 hand 其中 hand[i] 是写在第 i 张牌，和一个整数 groupSize 。
 * 如果她可能重新排列这些牌，返回 true ；否则，返回 false 。
 * @author Dreihunde
 *
 */
public class IsStraightHand {
	//method 1 hashmap计数去重 O(nlogn) O(n)
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if (n % groupSize != 0) {
            return false;
        }
        Arrays.sort(hand);
        
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int x : hand) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        for (int x : hand) {
            if (!map.containsKey(x)) {
                continue;
            }
            for (int i = 0; i < groupSize; i++) {
                int t = x + i;
                if (!map.containsKey(t)) {
                    return false;
                }
                map.put(t, map.get(t) - 1);
                if (map.get(t) == 0) {
                    map.remove(t);
                }
            }
        }
        return true;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] hand = new int[] {1,2,3,6,2,3,4,7,8};
		int groupSize = 3;
		
		IsStraightHand is = new IsStraightHand();
		System.out.println(is.isNStraightHand(hand, groupSize));

	}

}
