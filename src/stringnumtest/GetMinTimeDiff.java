package stringnumtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
 * @author Dreihunde
 *
 */
public class GetMinTimeDiff {
	//method 1 部分遍历 O(C) O(C)
	public int findMinDifference(List<String> timePoints) {
        int n = timePoints.size();
        if (n > 1440) {
            return 0;
        }

        timePoints.sort((a, b) -> (getTime(a) - getTime(b)));
        int minDiff = 1440;
        timePoints.add(timeAdd(timePoints.get(0)));
        for (int i = 1; i < n + 1; i++) {
            minDiff = Math.min(minDiff, getTimeSub(timePoints.get(i - 1), timePoints.get(i)));
        }
        return minDiff;
    }

    private String timeAdd(String time) {
        StringBuffer sb = new StringBuffer();
        sb.append((char)(time.charAt(0) + 2));
        sb.append((char)(time.charAt(1) + 4));
        sb.append(time.substring(2));
        return sb.toString();
    }

    private int getTime(String time) {
        return ((time.charAt(0) - '0') * 10 + (time.charAt(1) - '0')) * 60 + (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
    }
    private int getTimeSub(String time1, String time2) {
        return Math.abs(getTime(time1) - getTime(time2));
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> timePoints = new ArrayList<>(Arrays.asList(new String[] {"23:59","00:00"}));
		GetMinTimeDiff gm = new GetMinTimeDiff();
		System.out.println(gm.findMinDifference(timePoints));

	}

}
