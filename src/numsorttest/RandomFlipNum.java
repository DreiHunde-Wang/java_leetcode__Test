package numsorttest;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 给你一个 m x n 的二元矩阵 matrix ，且所有值被初始化为 0 。
 * 请你设计一个算法，随机选取一个满足 matrix[i][j] == 0 的下标 (i, j) ，并将它的值变为 1 。
 * 所有满足 matrix[i][j] == 0 的下标 (i, j) 被选取的概率应当均等。
 * @author Dreihunde
 *
 */
public class RandomFlipNum {
	//method 1 数组映射
    Map<Integer, Integer> map = new HashMap<>();
    int m;
    int n;
    int size;
    Random rand = new Random();

    public RandomFlipNum(int m, int n) {
        this.m = m;
        this.n = n;
        size = m * n;
    }
    
    public int[] flip() {
        int x = rand.nextInt(size);
        size--;
        int idx = map.getOrDefault(x, x);
        map.put(x, map.getOrDefault(size, size));
        return new int[]{idx / n, idx % n};
    }
    
    public void reset() {
        size = m * n;
        map.clear();
    }
    
    
    public static void main(String[] args) {
    	int m = 10;
    	int n = 5;
    	RandomFlipNum obj = new RandomFlipNum(m, n);
    	int[] param_1 = obj.flip();
    	CommonTest.printNum(param_1);
    	obj.reset();
	}

}
