package src.mathtest;

/**
 * 列表 arr 由在范围 [1, n] 中的所有整数组成，并按严格递增排序。请你对 arr 应用下述算法：
 * 从左到右，删除第一个数字，然后每隔一个数字删除一个，直到到达列表末尾。
 * 重复上面的步骤，但这次是从右到左。也就是，删除最右侧的数字，然后剩下的数字每隔一个删除一个。
 * 不断重复这两步，从左到右和从右到左交替进行，直到只剩下一个数字。
 * 给你整数 n ，返回 arr 最后剩下的数字。
 * @author Dreihunde
 *
 */
public class DeleteNum {
	//method 1 约瑟夫环 O(logn) O(1)
    //约瑟夫环
    //f(i)是序列[1,i]从左往右隔个消除一次后最终左侧的数字，f'(i)是从右往左隔个消除一次后最终右侧的数字，则有f(i)+f'(i)=i+1
    //如果将序列从左向右消除一次，则剩下的序列长度为[i/2]（[]代表向下取整）
    //最开始的序列是1，2，...，i，消除完后，剩下的序列为：2,4,...,x(x取值与i有关，如果是奇数则为i-1，偶数则为i）
    //然后考虑对剩下的序列进行有序重排，则变成1,2,3,...,[i/2]，然后对其进行从右向左的消除，也即f'([i/2])
    //将得到的新序列映射回之前的序列，则有f(i)=f'([i/2]) * 2
    //带入之前的公式，有f(i) = 2 * ([i/2] + 1 - f([i/2])
    //注意考虑题意后的特殊条件f(1)=1
    public int lastRemaining1(int n) {
        return n == 1 ? 1 : 2 * (n / 2 + 1 - lastRemaining1(n / 2));
    }

    //method 2 等差数列 O(logn) O(1)
    public int lastRemaining2(int n) {
        //a1 剩余数列的第一个数字
        int a1 = 1;
        //k 轮数， cnt 总长， step 步进 
        int k = 0, cnt = n, step = 1;
        while (cnt > 1) {
            if (k % 2 == 0) { // 正向
                a1 = a1 + step;
            } else { // 反向
                a1 = (cnt % 2 == 0) ? a1 : a1 + step;
            }
            k++;
            cnt = cnt >> 1;
            step = step << 1;
        }
        return a1;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 9;
		
		DeleteNum dn = new DeleteNum();
		
		System.out.println(dn.lastRemaining1(n));
		System.out.println(dn.lastRemaining2(n));

	}

}
