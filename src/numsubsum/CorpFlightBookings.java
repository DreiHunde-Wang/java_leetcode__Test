package numsubsum;

/**
 * 这里有 n 个航班，它们分别从 1 到 n 进行编号。
 * 有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
 * 请你返回一个长度为 n 的数组 answer，里面的元素是每个航班预定的座位总数。
 * 链接：https://leetcode-cn.com/problems/corporate-flight-bookings
 * @author Dreihunde
 *
 */
public class CorpFlightBookings {
	//method 1 遍历 O(nm) O(1)
    public int[] corpFlightBookings1(int[][] bookings, int n) {
        int[] ans = new int[n];
        for (int[] booking : bookings) {
            int start = booking[0];
            int end = booking[1];
            int seat = booking[2];
            for (int i = start - 1; i < end; i++) {
                ans[i] += seat;
            }
        }
        return ans;
    }

    //method 2 差分数组 O(n + m) O(1)
    public int[] corpFlightBookings2(int[][] bookings, int n) {
        int[] ans = new int[n];
        for (int[] booking : bookings) {
            int start = booking[0];
            int end = booking[1];
            int seat = booking[2];
            ans[start - 1] += seat;
            if (end < n) {
                ans[end] -= seat;
            }
        }

        for (int i = 1; i < n; i++) {
            ans[i] += ans[i - 1];
        }
        return ans;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
