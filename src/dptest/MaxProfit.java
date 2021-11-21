package dptest;

/**
 * �����ĳ��Ʊ�ļ۸���ʱ���Ⱥ�˳��洢�������У����������ù�Ʊһ�ο��ܻ�õ���������Ƕ��٣�
 * @author Dreihunde
 *
 */
public class MaxProfit {
	//method 1 ��̬�滮 O(n)
	public static int maxProfit1(int[] prices) {
        if (prices.length == 0)
            return 0;
        int cost = prices[0];
        int profit = 0;
        for (int price : prices) {
            cost = Math.min(cost, price);
            profit = Math.max(profit, price - cost);
        }
        return profit;
    }
	
    //method 2 ����O(n2)
    public static int maxProfit2(int[] prices) {
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                profit = Math.max(profit, prices[j] - prices[i]);
            }
        }
        return profit;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] prices = new int[] {7,1,5,3,6,4};
		
		
		long startTime = System.nanoTime();
		System.out.println(maxProfit1(prices));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(maxProfit2(prices));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
	}

}
