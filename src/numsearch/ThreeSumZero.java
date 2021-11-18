package numsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSumZero {
	
	//��ʱ
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

    //�Ż�
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
    
    public static List<List<Integer>> threeSum3(int[] nums) {// ��ʱ�临�Ӷȣ�O(n^2)
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length <= 2) return ans;

        Arrays.sort(nums); // O(nlogn)

        for (int i = 0; i < nums.length - 2; i++) { // O(n^2)
            if (nums[i] > 0) 
            	break; // ��һ�������� 0����������������󣬿϶���������
            if (i > 0 && nums[i] == nums[i - 1]) 
            	continue; // ȥ���ظ����
            int target = -nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] == target) {
                    ans.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));
                    
                    // ����Ҫ���� left����С right�����ǲ����ظ�������: [-2, -1, -1, -1, 3, 3, 3], i = 0, left = 1, right = 6, [-2, -1, 3] �Ĵ𰸼������Ҫ�ų��ظ��� -1 �� 3
                    left++; right--; // �������������Ҫ���мӼ�����
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
    
    //offical
    public static List<List<Integer>> threeSum4(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // ö�� a
        for (int first = 0; first < n; ++first) {
            // ��Ҫ����һ��ö�ٵ�������ͬ
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c ��Ӧ��ָ���ʼָ����������Ҷ�
            int third = n - 1;
            int target = -nums[first];
            // ö�� b
            for (int second = first + 1; second < n; ++second) {
                // ��Ҫ����һ��ö�ٵ�������ͬ
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // ��Ҫ��֤ b ��ָ���� c ��ָ������
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // ���ָ���غϣ����� b ����������
                // �Ͳ��������� a+b+c=0 ���� b<c �� c �ˣ������˳�ѭ��
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
