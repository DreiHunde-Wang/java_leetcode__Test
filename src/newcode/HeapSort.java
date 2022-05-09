package src.newcode;

public class HeapSort {
	public int[] MySort (int[] arr) {
        // write code here
//         Arrays.sort(arr);
        heapSort(arr);
        return arr;
    }
    
    private void heapSort(int[] arr) {
        int len = arr.length;
        if (len == 0) {
            return;
        }
        heapify(arr);
        for (int i = len - 1; i >= 1;) {
            swap(arr, 0, i);
            i--;
            siftDown(arr, 0, i);
        }
    }
    private void heapify(int[] arr) {
        int len = arr.length;
        for (int i = (len - 1) / 2; i >= 0; i--) {
            siftDown(arr, i, len - 1);
        }
    }
    
    private void siftDown(int[] arr, int k, int end) {
        while (2*k + 1 <= end) {
            int j = 2*k + 1;
            if (j + 1 <= end && arr[j + 1] > arr[j]) {
                j++;
            }
            if (arr[j] > arr[k]) {
                swap(arr, j, k);
            } else {
                break;
            }
            k = j;
        }
    }
    
    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[1001];
		for (int i = 1000; i >= 0; i--) {
			nums[i] = i * 2;
		}
		nums = new HeapSort().MySort(nums);
		for (int i = 0; i <= 1000; i++) {
			if (nums[i] != i * 2) {
				System.out.println(i + " false");
				return;
			}
		}
		System.out.println("true");

	}

}
