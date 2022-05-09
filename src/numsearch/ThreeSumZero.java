package src.numsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你�?个包�? n 个整数的数组 nums，判断 nums 中是否存在三个元�? a，b，c ，使得 a + b + c = 0 �?
 * 请你找出�?有和�? 0 且不重复的三元组�?
 * 注意：答案中不可以包含重复的三元组�??
 * @author Dreihunde
 *
 */
public class ThreeSumZero {
	
	//超时 O(n3) O(1)
    public static List<List<Integer>> threeSum1(int[] nums) {
         Arrays.sort(nums);
         int n = nums.length;
         List<List<Integer>> list = new ArrayList<>();

    	for (int i = 0; i < n - 2; i++) {
             for (int j =i + 1; j < n - 1; j++)
                 for (int k = j + 1; k < n; k++) {
                     if (nums[i] + nums[j] + nums[k] == 0) {
                         List<Integer> temp = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k]));
                         if(!list.contains(temp))
                             list.add(temp);
                     }
                 }
             if (nums[i] > 0)
                 break;
         }
            
         return list;

    }

    //method 2 双重遍历+二分查找 O(n2logn) O(1)
    public static List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> list = new ArrayList<>();

        for (int i = 0; i < n - 2; i++) {
            if (nums[i] > 0)
                break;
            else if (i - 1 >= 0 && nums[i] == nums[i - 1])
                continue;
            int border = n - 1;
            for (int j = i + 1; j < border; j++) {
            	if (nums[i] + nums[j] > 0)
            		break;
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;
                int k = binarySearch(nums, j + 1, n - 1, 0 - nums[i] - nums[j]);
                if (k != -1) {
                    list.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k])));
                    border = k;
                }

            }   
            
        }
            
        return list;

    }

    private static int binarySearch(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid - 1;
            else
                return mid;
        }
        return -1;
    }
    
    //method 3 遍历+双指�? O(n2) O(1)
    public static List<List<Integer>> threeSum3(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length <= 2) return ans;

        Arrays.sort(nums); // O(nlogn)

        for (int i = 0; i < nums.length - 2; i++) { // O(n^2)
            if (nums[i] > 0) 
            	break; // 第一个数大于 0，后面的数都比它大，肯定不成立了
            if (i > 0 && nums[i] == nums[i - 1]) 
            	continue; // 去掉重复情况
            int target = -nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] == target) {
                    ans.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));
                    
                    // 现在要增�? left，减�? right，但是不能重复，比如: [-2, -1, -1, -1, 3, 3, 3], i = 0, left = 1, right = 6, [-2, -1, 3] 的答案加入后，需要排除重复的 -1 �? 3
                    left++; right--; // 首先无论如何先要进行加减操作
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                } else if (nums[left] + nums[right] < target) {
                    left++;
                } else {  // nums[left] + nums[right] > target
                    right--;
                }
            }
        }
        return ans;
    }
    
    //offical 遍历+双指�? O(n2) O(1)
    public static List<List<Integer>> threeSum4(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // �?要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的�?右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // �?要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // �?要保�? b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随�? b 后续的增�?
                // 就不会有满足 a+b+c=0 并且 b<c �? c 了，可以�?出循�?
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] nums = new int[] {-1,0,1,2,-1,-4};
		long startTime = System.nanoTime();
		System.out.println(threeSum1(nums));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		nums = new int[] {-1,0,1,2,-1,-4};
		startTime = System.nanoTime();
		System.out.println(threeSum2(nums));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		nums = new int[] {-1,0,1,2,-1,-4};
		startTime = System.nanoTime();
		System.out.println(threeSum3(nums));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		nums = new int[] {-1,0,1,2,-1,-4};
		startTime = System.nanoTime();
		System.out.println(threeSum4(nums));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}
