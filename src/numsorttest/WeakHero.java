package numsorttest;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class WeakHero {
	//method 先排序 O(nlogn) O(logn)
    public int numberOfWeakCharacters1(int[][] properties) {
        //按照攻击力从大到小，防御力从小到大的顺序排列
        Arrays.sort(properties, (o1, o2) -> {
            return o1[0] == o2[0] ? (o1[1] - o2[1]) : (o2[0] - o1[0]);
        });
        //目前最大防御，由于攻击力从小到大排列，所以后面的攻击力默认小于前者
        int maxDef = 0;
        int ans = 0;
        for (int[] p : properties) {
            if (p[1] < maxDef) {
                ans++;
            } else {
                maxDef = p[1];
            }
        }
        return ans;
    }

    //method 2 单调栈 O(nlogn) O(n)
    public int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, (o1, o2) -> {
            return o1[0] == o2[0] ? (o2[1] - o1[1]) : (o1[0] - o2[0]);
        });
        int ans = 0;
        Deque<Integer> st = new ArrayDeque<Integer>();
        for (int[] p : properties) {
            while (!st.isEmpty() && st.peek() < p[1]) {
                st.pop();
                ans++;
            }
            st.push(p[1]);
        }
        return ans;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
