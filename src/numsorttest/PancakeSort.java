package src.numsorttest;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你�?个整数数�? arr ，请使用 煎饼翻转 完成对数组的排序�?
 * �?次煎饼翻转的执行过程如下�?
 * 选择�?个整�? k �?1 <= k <= arr.length
 * 反转子数�? arr[0...k-1]（下标从 0 �?始）
 * 例如，arr = [3,2,1,4] ，�?�择 k = 3 进行�?次煎饼翻转，反转子数�? [3,2,1] ，得�? arr = [1,2,3,4] �?
 * 以数组形式返回能�? arr 有序的煎饼翻转操作所对应�? k 值序列�?�任何将数组排序且翻转次数在 10 * arr.length 范围内的有效答案都将被判断为正确�?
 * 链接：https://leetcode-cn.com/problems/pancake-sorting
 * @author Dreihunde
 *
 */
public class PancakeSort {
	//method 1 冒泡排序 O(n^2) O(1)
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> ret = new ArrayList<Integer>();
        for (int n = arr.length; n > 1; n--) {
            int index = 0;
            for (int i = 1; i < n; i++) {
                if (arr[i] >= arr[index]) {
                    index = i;
                }
            }
            if (index == n - 1) {
                continue;
            }
            reverse(arr, index);
            reverse(arr, n - 1);
            ret.add(index + 1);
            ret.add(n);
        }
        return ret;
    }

    public void reverse(int[] arr, int end) {
        for (int i = 0, j = end; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
