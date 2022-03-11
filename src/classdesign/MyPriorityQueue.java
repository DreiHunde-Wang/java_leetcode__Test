package classdesign;

import java.util.ArrayList;

public class MyPriorityQueue<T extends Comparable<T>> {
    //用来存储元素的数组
    private T[] items;
    //记录元素个数
    private int N;

    //创建容量为capacity的MaxPriorityQueue对象
    @SuppressWarnings("unchecked")
	public MyPriorityQueue(int capacity) {
        this.N = 0;
        this.items = (T[]) new Comparable[capacity + 1];
    }

    //判断堆中索引i处的元素是否小于索引j处的元素
    private boolean less(int i, int j) {
        return items[i].compareTo(items[j]) < 0;
    }

    //交换堆中i索引和j索引处的值
    private void exchange(int i, int j) {
        T temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    //获取队列中元素的个数
    public int size() {
        return N;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return N == 0;
    }

    //：往队列中插入一个元素
    public void insert(T t) {
        items[++N] = t;
        swim(N);
    }

    //使用上浮算法，使索引k处的元素能在堆中处于一个正确的位置
    private void swim(int k) {
        //如果已经到了根结点，就不需要循环
        while (k > 1) {
            if (less(k / 2, k)) {
                exchange(k / 2, k);
            }
            k = k / 2;
        }
    }

    //删除堆中最大的元素,并返回这个最大元素
    public T delMax() {
        T max = items[1];
        //交换索引1处和索引N处的值
        exchange(1, N);
        items[N] = null;
        N--;
        sink(1);
        return max;
    }

    //使用下沉算法，使索引k处的元素能在堆中处于一个正确的位置
    private void sink(int k) {
        //如果当前已经是最底层了，就不需要循环了
        while (2 * k <= N) {
            int max = 2 * k;
            if (max + 1 <= N) {//表示有右子节点
                if (less(max, max + 1)) {
                    max = max + 1;
                }
            }
            //比较当前结点和子结点中的较大者，如果当前结点不小，则结束循环
            if (!less(k,max)){
                break;
            }
            //当前结点小，则交换，
            exchange(k,max);
            k = max;
        }
    }
}

