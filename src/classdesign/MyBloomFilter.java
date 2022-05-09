package src.classdesign;

import java.util.BitSet;
/**
 * ��¡������
 * �жϸ��������Ƿ���ڣ������ж�һ�������Ƿ�����ڰ����������ֵ����ּ��У����ּ��ܴ�5 �����ϣ����� 
 * ��ֹ���洩͸���ж�����������Ƿ���Ч����ֱ���ƹ������������ݿ⣩�ȵȡ�����������ʼ����ˡ����������ܵȵȡ� 
 * ȥ�أ�������������ַ��ʱ����Ѿ���ȡ���� URL ȥ�ء�
 * @author Dreihunde
 *
 */
public class MyBloomFilter {
	
	/**
	 * λ�����С
	 */
	private static final int DEFAULT_SIZE = 2 << 24;
	/**
	 * Hash�ĳ�ʼ������
	 */
	private static final int[] SEEDS = new int[] {3, 13, 46, 71, 91, 134};
	private BitSet bits = new BitSet(DEFAULT_SIZE);
	private SimpleHash[] func = new SimpleHash[SEEDS.length];
	
	public MyBloomFilter() {
		for (int i = 0; i < SEEDS.length; i++) {
			func[i] = new SimpleHash(DEFAULT_SIZE, SEEDS[i]);
		}
	}
	
	public void add(Object value) {
		for (SimpleHash f : func) {
			bits.set(f.hash(value), true);
		}
	}
	
	public boolean contains(Object value) {
		boolean ret = true;
		for (SimpleHash f : func) {
			ret = ret && bits.get(f.hash(value));
		}
		return ret;
	}
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String value1 = "test1";
		String value2 = "test2";
		MyBloomFilter mb = new MyBloomFilter();
		System.out.println(mb.contains(value2));
		mb.add(value2);
		System.out.println(mb.contains(value2));
		System.out.println(mb.contains(value1));
		mb.add(value1);
		System.out.println(mb.contains(value2 + value1));
		System.out.println(mb.contains(value1));

	}

}

class SimpleHash {
	private int cap;
	private int seed;
	
	public SimpleHash() {
		
	}
	public SimpleHash(int _cap, int _seed) {
		this.cap = _cap;
		this.seed = _seed;
	}
	
	public int hash(Object value) {
		int h;
		return (value == null) ? 0 : Math.abs(seed * (cap - 1) & ((h = value.hashCode()) ^ (h >>> 16)));
	}
}
