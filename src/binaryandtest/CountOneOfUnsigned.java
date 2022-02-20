package binaryandtest;

/**
 * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为 汉明重量).）。
 * https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/
 * @author Dreihunde
 *
 */
public class CountOneOfUnsigned {
	//method 1 逐位匹配计数 O(C) O(1) C = 32
    public int hammingWeight1(int n) {
        int sum = 0;
        for(int i = 0; i < 32; i++){
            if((n & (1<<i) )!= 0)
                sum++;
        }
        return sum;



        
    }
    //method 2 统计尾数1 O(C) O(1) C = 32
    public int hammingWeight2(int n) {
		int sum = 0;
        while(n != 0){
            if((n & 1) == 1) sum++;
            n >>>= 1;
        }
        return sum;        
    }
    
  //method 3 自身相与计数 O(C) O(1) C为1的个数，最大为32
   public int hammingWeight3(int n) {
	   int sum = 0;
       while(n != 0){
           n &= n-1;
           sum++;
       }
       return sum;        
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//无符号数相当于31个1
		int n = -3;
		
		CountOneOfUnsigned co = new CountOneOfUnsigned();
		long startTime = System.nanoTime();
		System.out.println(co.hammingWeight1(n));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
				
		startTime = System.nanoTime();
		System.out.println(co.hammingWeight2(n));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(co.hammingWeight3(n));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");


	}

}
