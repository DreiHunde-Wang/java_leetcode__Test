package numsorttest;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Alice ������һ���ƣ�����Ҫ����������Щ�ƣ��ֳ������飬ʹÿһ����������� groupSize �������� groupSize ������������ɡ�
 * ����һ���������� hand ���� hand[i] ��д�ڵ� i ���ƣ���һ������ groupSize ��
 * �������������������Щ�ƣ����� true �����򣬷��� false ��
 * @author Dreihunde
 *
 */
public class IsStraightHand {
	//method 1 hashmap����ȥ�� O(nlogn) O(n)
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
