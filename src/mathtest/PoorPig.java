package src.mathtest;

/**
 * �? buckets 桶液体，其中 正好 有一桶含有毒药，其余装的都是水�?�它们从外观看起来都�?样�?�为了弄清楚哪只水桶含有毒药�?
 * 你可以喂�?些猪喝，通过观察猪是否会死进行判断�?�不幸的是，你只有 minutesToTest 分钟时间来确定哪桶液体是有毒的�??
 * 喂猪的规则如下：
 * 选择若干活猪进行喂养
 * 可以允许小猪同时饮用任意数量的桶中的水，并且该过程不�?要时间�??
 * 小猪喝完水后，必须有 minutesToDie 分钟的冷却时间�?�在这段时间里，你只能观察，而不允许继续喂猪�?
 * 过了 minutesToDie 分钟后，�?有喝到毒药的猪都会死去，其他�?有猪都会活下来�??
 * 重复这一过程，直到时间用完�??
 * 给你桶的数目 buckets ，minutesToDie �? minutesToTest ，返回在规定时间内判断哪个桶有毒�?�?�? �?�? 猪数�?
 * @author Dreihunde
 *
 */
public class PoorPig {
	/**
	 * 其实香农已经在�?�信息论》（信息熵）中给过我们结论了—�?�我们一共可以进行n轮实验（n = minutesToTest / minutesToDie）：
	 * 经过�?有实验，�?只小猪能有多少种状�?�？第一轮就死�?�第二轮死�??...、第n轮死，以及生还，�?以一共有n + 1种状�?
	 * n + 1种状态所携带的信息为log_2(n + 1)比特，这也是�?只小猪最多提供的信息�?
	 * 而�?�buckets瓶液体中哪一瓶是毒�?�这件事，也有buckets种可能�?�，�?以需要的信息量是log_2(buckets)
	 * 注：以上�?有事件�?�状态都是先验等概的，所以可以直接对2取对数得到信息熵
	 * 因此�?定存在一种�?�合理设计�?�的实验，使得我们只要有k只猪猪：满足 k * log_2(n + 1) >= log_2(buckets)时，则我们一定能得到足够的信息量去判断哪�?瓶是毒�??
	 */
	//method 1 math
	 public int poorPigs1(int buckets, int minutesToDie, int minutesToTest) {
	        int iter = minutesToTest / minutesToDie + 1;
	        return (int) Math.ceil( Math.log(buckets) / Math.log(iter));
	    }
	
	//method 2 dp
    public int poorPigs2(int buckets, int minutesToDie, int minutesToTest) {
        if (buckets == 1) {
            return 0;
        }
        int[][] combinations = new int[buckets + 1][buckets + 1];
        combinations[0][0] = 1;
        int iterations = minutesToTest / minutesToDie;
        int[][] f = new int[buckets][iterations + 1];
        for (int i = 0; i < buckets; i++) {
            f[i][0] = 1;
        }
        for (int j = 0; j <= iterations; j++) {
            f[0][j] = 1;
        }
        for (int i = 1; i < buckets; i++) {
            combinations[i][0] = 1;
            combinations[i][i] = 1;
            for (int j = 1; j < i; j++) {
                combinations[i][j] = combinations[i - 1][j - 1] + combinations[i - 1][j];
            }
            for (int j = 1; j <= iterations; j++) {
                for (int k = 0; k <= i; k++) {
                    f[i][j] += f[k][j - 1] * combinations[i][i - k];
                }
            }
            if (f[i][iterations] >= buckets) {
                return i;
            }
        }
        return 0;
    }
    
    public static void main(String[] args) {
    	int buckets = 1000;
    	int minutesToTest = 60;
    	int minutesToDie = 15;
    	
    	PoorPig pp = new PoorPig();
    	long startTime=System.nanoTime(); 
		System.out.println(pp.poorPigs1(buckets, minutesToDie, minutesToTest));
		long endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime=System.nanoTime(); 
		System.out.println(pp.poorPigs2(buckets, minutesToDie, minutesToTest));
		endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
	}

}
