package dptest;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 这里有 n 门不同的在线课程，按从 1 到 n 编号。给你一个数组 courses ，
 * 其中 courses[i] = [durationi, lastDayi] 表示第 i 门课将会 持续 上 durationi 天课，
 * 并且必须在不晚于 lastDayi 的时候完成。
 * 你的学期从第 1 天开始。且不能同时修读两门及两门以上的课程。返回你最多可以修读的课程数目。
 * @author Dreihunde
 *
 */
public class ScheduleCourse3 {
	//method 1 贪心算法+优先队列 O(nlogn) O(n)
    public int scheduleCourse(int[][] courses) {
        //按lastDay递增排序
        Arrays.sort(courses, (a, b) -> (a[1] - b[1]));
        //大根堆，保存所选择课程的耗时情况
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> (b - a));

        int totalTime = 0;
        for (int[] course: courses) {
            int dr = course[0];
            int ld = course[1];
            //如果当前花费的时间+课程时间小于截至日期，则添加该课程
            if (totalTime + dr <= ld) {
                totalTime += dr;
                heap.offer(dr);
            //否则，当该课程耗时小于之前选择课程中最耗时的课程时，用该课程替换掉当前最耗时的课
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
