package src.dptest;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 这里�? n 门不同的在线课程，按�? 1 �? n 编号。给你一个数�? courses �?
 * 其中 courses[i] = [durationi, lastDayi] 表示�? i 门课将会 持续 �? durationi 天课�?
 * 并且必须在不晚于 lastDayi 的时候完成�??
 * 你的学期从第 1 天开始�?�且不能同时修读两门及两门以上的课程。返回你�?多可以修读的课程数目�?
 * @author Dreihunde
 *
 */
public class ScheduleCourse3 {
	//method 1 贪心算法+优先队列 O(nlogn) O(n)
    public int scheduleCourse(int[][] courses) {
        //按lastDay递增排序
        Arrays.sort(courses, (a, b) -> (a[1] - b[1]));
        //大根堆，保存�?选择课程的�?�时情况
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> (b - a));

        int totalTime = 0;
        for (int[] course: courses) {
            int dr = course[0];
            int ld = course[1];
            //如果当前花费的时�?+课程时间小于截至日期，则添加该课�?
            if (totalTime + dr <= ld) {
                totalTime += dr;
                heap.offer(dr);
            //否则，当该课程�?�时小于之前选择课程中最耗时的课程时，用该课程替换掉当前�?耗时的课
            } else if(!heap.isEmpty() && heap.peek() > dr) {
                totalTime -= heap.poll() - dr;
                heap.offer(dr);
            }
        }
        return heap.size();
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] courses = new int[][] {{100,200},{200,1300},{1000,1250},{2000,3200}};
		
		ScheduleCourse3 sc3 = new ScheduleCourse3();
		System.out.println(sc3.scheduleCourse(courses));
	}

}
