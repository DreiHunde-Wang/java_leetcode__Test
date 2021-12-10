package listtest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，
 * 要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 * @author Dreihunde
 *
 */
class MaxQueue {
    //队列维护基本值
    Queue<Integer> valueQ;
    //双端队列维护最大值（递减数列的首端即为最大值）
    Deque<Integer> maxQ;

    public MaxQueue() {
        valueQ = new LinkedList<>();
        maxQ = new ArrayDeque<>();
//        maxQ = new LinkedList<>();
    }
    
    public int max_value() {
        if (maxQ.isEmpty()) {
            return -1;
        }
        return maxQ.peekFirst();
    }
    
    public void push_back(int value) {
        //若新入的值大于队尾，则去除队尾元素直到新值插入
        while (!maxQ.isEmpty() && value >= maxQ.peekLast()) {
            maxQ.pollLast();
        }
        valueQ.offer(value);
        maxQ.offerLast(value);
    }
    
    public int pop_front() {
        if (valueQ.isEmpty())
            return -1;
        int rev = valueQ.poll();
        //当取出的值为当前最大值时，最大值队列去除队首
        if (rev == maxQ.peekFirst()) {
            maxQ.pollFirst();
        } 
        return rev;
    }
    
    public static void main(String[] args) {
		MaxQueue mq = new MaxQueue();
		mq.push_back(1);
		mq.push_back(1);
		mq.push_back(2);
		int param_1 = mq.max_value();
		mq.push_back(1);
		int param_2 = mq.pop_front();
		System.out.println(param_1 + " " + param_2);
	}
}
