package src.mathtest;

/**
 * 0,1,···,n-1这n个数字排成一个圆圈，从数�?0�?始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字�??
 * 例如�?0�?1�?2�?3�?4�?5个数字组成一个圆圈，从数�?0�?始每次删除第3个数字，则删除的�?4个数字依次是2�?0�?4�?1，因此最后剩下的数字�?3�?
 * @author Dreihunde
 *
 */
public class CircleLastRemain {
	//method 1 math dp[i]=(dp[i�?1]+m)%i
    public int lastRemaining1(int n, int m) {
        int[] dp = new int[n + 1];

        dp[1] = 0;

        for (int i = 1; i <= n; i++) {
            dp[i] = (dp[i] + m) % (i);
        }

        return dp[n];
    }
    
    //method 2 math 优化
    public int lastRemaining2(int n, int m) {

        int dp = 0;

        for (int i = 1; i < n; i++) {
            dp = (dp + m) % (i + 1);
        }

        return dp;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 10;
		int m = 3;
		
		CircleLastRemain cl = new CircleLastRemain();
		
		long startTime = System.nanoTime();
		System.out.println(cl.lastRemaining1(n, m));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
				
		startTime = System.nanoTime();
		System.out.println(cl.lastRemaining2(n, m));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}
