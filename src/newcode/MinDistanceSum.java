package newcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 在一张2D地图上小强有n座房子,因为地理位置的原因没有办法给每座房子提供水源,所以小强打算修建一条平行y轴的水渠.因为这条水渠无限长.
 * 所以能够看做是一条平行于y轴的直线. 现在小强想确定修建水渠的位置,能够使得这n座房子到水渠的垂直距离和最小,请你输出最小的距离和.
 * @author Dreihunde
 *
 */
public class MinDistanceSum {
	
	 //method 中位数即为最佳位置 O(nlogn) O(1)
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] x = new int[n];
        for(int i = 0; i < n; i++)
            x[i] = Integer.parseInt(br.readLine().trim().split(" ")[0]);
        Arrays.sort(x);
        int median = x[n / 2];
        long res = 0;
        for(int i = 0; i < n; i++) res += Math.abs(median - x[i]);
        System.out.println(res);
    }

}
