package src.newcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 最近小强主办了一场国际交流会，大家在会上以一个圆桌围坐在一起。由于大会的目的就是让不同国家的人感受一下不同的异域气息，为了更好地达到这个目的，小强希望最大化邻座两人之间的差异程度和。
 * 为此，他找到了你，希望你能给他安排一下座位，达到邻座之间的差异之和最大。
 * @author Dreihunde
 *
 */
public class MaxAbsBetweenNear {
	//要想相邻元素的绝对差之和最大，就要保证所有数对的绝对差尽可能均匀分布。进行如下操作就可以达到这个效果：
	//先将数组进行排序，然后将其分为前后两个子数组，按顺序取出两个子数组的元素组成数对。
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        String[] strArr = br.readLine().trim().split(" ");
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = Integer.parseInt(strArr[i]);
        Arrays.sort(arr);
        int midIdx = n % 2 == 0? n / 2: n / 2 + 1;
        int ptr1 = 0, ptr2 = midIdx;
        ArrayList<Integer> list = new ArrayList<>();
        while(ptr1 < midIdx && ptr2 < n){
            list.add(arr[ptr1 ++]);
            list.add(arr[ptr2 ++]);
        }
        if(ptr1 < midIdx) list.add(arr[ptr1]);
        if(ptr2 < n) list.add(arr[ptr2]);
        long sum = 0;
        for(int i = 0; i < n - 1; i++) sum += Math.abs(list.get(i + 1) - list.get(i));
        System.out.println(sum + Math.abs(list.get(0) - list.get(n - 1)));
        for(int i = 0; i < n; i++) System.out.print(list.get(i) + " ");
    }

}
