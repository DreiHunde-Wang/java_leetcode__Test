package classdesign;

import java.util.ArrayList;

public class MyPriorityQueue<T extends Comparable<T>> {
    //�����洢Ԫ�ص�����
    private T[] items;
    //��¼Ԫ�ظ���
    private int N;

    //��������Ϊcapacity��MaxPriorityQueue����
    @SuppressWarnings("unchecked")
	public MyPriorityQueue(int capacity) {
        this.N = 0;
        this.items = (T[]) new Comparable[capacity + 1];
    }

    //�ж϶�������i����Ԫ���Ƿ�С������j����Ԫ��
    private boolean less(int i, int j) {
        return items[i].compareTo(items[j]) < 0;
    }

    //��������i������j��������ֵ
    private void exchange(int i, int j) {
        T temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    //��ȡ������Ԫ�صĸ���
    public int size() {
        return N;
    }

    //�ж϶����Ƿ�Ϊ��
    public boolean isEmpty() {
        return N == 0;
    }

    //���������в���һ��Ԫ��
    public void insert(T t) {
        items[++N] = t;
        swim(N);
    }

    //ʹ���ϸ��㷨��ʹ����k����Ԫ�����ڶ��д���һ����ȷ��λ��
    private void swim(int k) {
        //����Ѿ����˸���㣬�Ͳ���Ҫѭ��
        while (k > 1) {
            if (less(k / 2, k)) {
                exchange(k / 2, k);
            }
            k = k / 2;
        }
    }

    //ɾ����������Ԫ��,������������Ԫ��
    public T delMax() {
        T max = items[1];
        //��������1��������N����ֵ
        exchange(1, N);
        items[N] = null;
        N--;
        sink(1);
        return max;
    }

    //ʹ���³��㷨��ʹ����k����Ԫ�����ڶ��д���һ����ȷ��λ��
    private void sink(int k) {
        //�����ǰ�Ѿ�����ײ��ˣ��Ͳ���Ҫѭ����
        while (2 * k <= N) {
            int max = 2 * k;
            if (max + 1 <= N) {//��ʾ�����ӽڵ�
                if (less(max, max + 1)) {
                    max = max + 1;
                }
            }
            //�Ƚϵ�ǰ�����ӽ���еĽϴ��ߣ������ǰ��㲻С�������ѭ��
            if (!less(k,max)){
                break;
            }
            //��ǰ���С���򽻻���
            exchange(k,max);
            k = max;
        }
    }
}

