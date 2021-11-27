package slidewindowtest;

import java.util.ArrayList;
import java.util.List;

import numsorttest.CommonTest;
import printtreenode.ComnTest;

/**
 * ����һ�������� target ��������к�Ϊ target ���������������У����ٺ�������������
 * �����ڵ�������С�������У���ͬ���а����׸����ִ�С�������С�
 * @author Dreihunde
 *
 */
public class SumIsTSeq {
	//method 1 ���� O(n2) O(1)
    public int[][] findContinuousSequence1(int target) {
        int mid = (target + 1) >>> 1;
        int slow = 1;
        int fast = 1;
        int sum = 0;
        List<int[]> list = new ArrayList<>();
        while (fast <= mid) {
            while (fast <= mid && sum < target) {
                sum += fast;
                fast++;
            }
            if (sum == target) {
                list.add(serialNum(slow, fast));  
            } 
            // while (slow <= mid && sum > target) {
            //     sum -= slow;
            //     slow++;
            // }
            slow++;
            fast = slow;
            sum = 0;
        }
        int[][] revInt = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            revInt[i] = list.get(i);
        }
        return revInt;
        // return list.toArray(new int[list.size()][]);

    }

    private static int[] serialNum(int start, int end) {
        int[] nums = new int[end - start];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = start + i;
        }

        return nums;
    }

    //method 2 ����ָ���Ż� O(target) O(1)
    public int[][] findContinuousSequence2(int target) {
        List<int[]> vec = new ArrayList<int[]>();
        for (int l = 1, r = 2; l < r;) {
            int sum = (l + r) * (r - l + 1) / 2;
            if (sum == target) {
                int[] res = new int[r - l + 1];
                for (int i = l; i <= r; ++i) {
                    res[i - l] = i;
                }
                vec.add(res);
                l++;
            } else if (sum < target) {
                r++;
            } else {
                l++;
            }
        }
        return vec.toArray(new int[vec.size()][]);
    }

    //method 3 ��ѧ O(target) O(1)
    public int[][] findContinuousSequence3(int target) {
        List<int[]> vec = new ArrayList<int[]>();
        int sum = 0, limit = (target - 1) / 2; // (target - 1) / 2 ��Ч�� target / 2 ��ȡ��
        for (int x = 1; x <= limit; ++x) {
            long delta = 1 - 4 * (x - (long) x * x - 2 * target);
            if (delta < 0) {
                continue;
            }
            int delta_sqrt = (int) Math.sqrt(delta + 0.5);
            if ((long) delta_sqrt * delta_sqrt == delta && (delta_sqrt - 1) % 2 == 0) {
                int y = (-1 + delta_sqrt) / 2; // ��һ����(-1-delta_sqrt)/2��ȻС��0�����ÿ���
                if (x < y) {
                    int[] res = new int[y - x + 1];
                    for (int i = x; i <= y; ++i) {
                        res[i - x] = i;
                    }
                    vec.add(res);
                }
            }
        }
        return vec.toArray(new int[vec.size()][]);
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SumIsTSeq si = new SumIsTSeq();
		int target = 9;
		
		long startTime = System.nanoTime();
		CommonTest.printNum(si.findContinuousSequence1(target));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		CommonTest.printNum(si.findContinuousSequence2(target));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		CommonTest.printNum(si.findContinuousSequence3(target));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}
