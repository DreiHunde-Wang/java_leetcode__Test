package src.numsorttest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 * @author Dreihunde
 *
 */
public class MergeIntervals {
	private static Random random = new Random();
    //method 1 先sort再合并
    public int[][] merge(int[][] intervals) {
        //使用comparator构造排序
        // Arrays.sort(intervals, new Comparator<int[]>() {
        //     public int compare(int[] o1, int[] o2) {
        //         return o1[0] - o2[0];
        //     }
        // });
        //自己实现快速排序
        quickSort(intervals, 0, intervals.length - 1);
        int left = intervals[0][0];
        int right = intervals[0][1];
        List<int[]> rev = new ArrayList<>();
        for (int[] interval : intervals) {
            if (interval[0] > right && interval[1] > right) {
                rev.add (new int[]{left, right});
                left = interval[0];
                right = interval[1];
            } else {
                right = Math.max(right, interval[1]);
            }
        }
        rev.add(new int[]{left, right});
        int[][] revInt = new int[rev.size()][2];
        // for (int i = 0; i < rev.size(); i++) {
        //     revInt[i] = rev.get(i);
        // }
        return rev.toArray(revInt);
    }

    //method 2 自己实现快速排序
    private static void quickSort(int[][] intervals, int left, int right) {
        if (left > right) 
            return;
        int pIndex = partition(intervals, left, right);
        quickSort(intervals, left, pIndex - 1);
        quickSort(intervals, pIndex + 1, right);
    }

    private static int partition(int[][] intervals, int left, int right) {
        int pIndex = left + random.nextInt(right - left + 1);
        swap(intervals, left, pIndex);
        int pivot = intervals[left][0];
        int lt = left + 1;
        int gt = right;

        while (true) {
            while (lt <= right && intervals[lt][0] < pivot) {
                lt++;
            }

            while (left < gt && intervals[gt][0] > pivot) {
                gt--;
            }
            if (lt >= gt)
            break;
            swap(intervals, lt, gt);
            lt++;
            gt--;
        }
        swap(intervals, left, gt);
        return gt;
    }

    private static void swap(int[][] intervals, int i, int j) {
        int[] temp = intervals[i];
        intervals[i] = intervals[j];
        intervals[j] = temp;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
