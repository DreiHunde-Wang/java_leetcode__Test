package numsorttest;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class WeakHero {
	//method ������ O(nlogn) O(logn)
    public int numberOfWeakCharacters1(int[][] properties) {
        //���չ������Ӵ�С����������С�����˳������
        Arrays.sort(properties, (o1, o2) -> {
            return o1[0] == o2[0] ? (o1[1] - o2[1]) : (o2[0] - o1[0]);
        });
        //Ŀǰ�����������ڹ�������С�������У����Ժ���Ĺ�����Ĭ��С��ǰ��
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

    //method 2 ����ջ O(nlogn) O(n)
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
