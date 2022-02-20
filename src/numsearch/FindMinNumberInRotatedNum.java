package numsearch;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 给你一个可能存在 重复 元素值的数组 numbers ，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。请返回旋转数组的最小元素。
 * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，该数组的最小值为1。 
 * 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
 * @author Dreihunde
 *
 */
public class FindMinNumberInRotatedNum {
	//method 1 遍历 O(n) O(1)
    public int minArray1(int[] numbers) {
        int minNum = Integer.MAX_VALUE;
        for (int num : numbers) {
            minNum = Math.min(minNum, num);
        }
        return minNum;
    }

    //method 2 二分查找 O(logn) O(1)
    public int minArray(int[] numbers) {
        int n = numbers.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return numbers[0];
        }
        int left = 0;
        int right = n - 1;
        if (numbers[left] < numbers[right]) {
            return numbers[left];
        } else {
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (numbers[mid] > numbers[right]) {
                    left = mid + 1;
                } else if (numbers[mid] < numbers[right]) {
                    right = mid;
                } else {
                    right--;
                }
            }
        }
        return numbers[left];
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
