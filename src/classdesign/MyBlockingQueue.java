package src.classdesign;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 阻塞队列的实现原理
 * 1、生产者向队尾添加元素
 * 2、消费者向队头消费元素
 * 3、添加和消费过程是线程安全的
 * @author Dreihunde
 *
 * @param <T>
 */
public class MyBlockingQueue<T> {
	private Queue<T> queue = new LinkedList<>();
	private final int MAX;
	
	public MyBlockingQueue(int limit) {
		this.MAX = limit;
	}
	
	public void put(T t) throws InterruptedException {
		synchronized (this) {
			while (queue.size() == MAX) {
				this.wait();
			}
			queue.offer(t);
			this.notifyAll();
		}
	}
	
	public T get() throws InterruptedException {
		T t;
		synchronized (this) {
			while (queue.isEmpty()) {
				this.wait();
			}
			t = queue.poll();
			this.notifyAll();
		}
		return t;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}

class MyBlockingQueueForLock<T> {
	private Queue<T> queue = new LinkedList<>();
	private final int MAX;
	private ReentrantLock lock = new ReentrantLock();
	private Condition consumer = lock.newCondition();
	private Condition producer = lock.newCondition();
	
	public MyBlockingQueueForLock(int limit){
		this.MAX = limit;
	}
	
	public void put(T t) throws InterruptedException {
		final ReentrantLock lock = this.lock;
		lock.lockInterruptibly();
		try {
			while (queue.size() == MAX) {
				producer.await();
			}
			queue.offer(t);
			consumer.signalAll();
		} finally {
			lock.unlock();
		}
		
	}
	
	public T get() throws InterruptedException {
		final ReentrantLock lock = this.lock;
		T t;
		lock.lockInterruptibly();
		try {
			while (queue.isEmpty()) {
				consumer.await();
			}
			t = queue.poll();
			producer.signalAll();
		} finally {
			lock.unlock();
		}
		return t;
	}
	
}
