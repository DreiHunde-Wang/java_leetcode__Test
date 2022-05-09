package src.classdesign;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  µœ÷threadpool
 * @author Dreihunde
 *
 */
public class ThreadPoolTrader implements Executor{
	private final AtomicInteger ctl = new AtomicInteger(0);
	
	private volatile int corePoolSize;
	private volatile int maximumPoolSize;
	
	private final BlockingQueue<Runnable> workQueue;
	
	public ThreadPoolTrader(int _corePoolSize, int _maximumPoolSize, BlockingQueue<Runnable> _workQueue) {
		this.corePoolSize = _corePoolSize;
		this.maximumPoolSize = _maximumPoolSize;
		this.workQueue = _workQueue;
	}
	
	@Override
	public void execute(Runnable command) {
		// TODO Auto-generated method stub
		int c = ctl.get();
		if (c < corePoolSize) {
			if (!addWorker(command)) {
				reject();
			}
			return;
		}
		
		if (!workQueue.offer(command)) {
			if (!addWorker(command)) {
				reject();
			}
		}
	}
	
	private boolean addWorker(Runnable firstTask) {
		if (ctl.get() >= maximumPoolSize)	return false;
		Worker work = new Worker(firstTask);
		work.thread.start();
		ctl.getAndIncrement();
		return true;
	}
	
	private final class Worker implements Runnable {
		final Thread thread;
		Runnable firstTask;
		
		public Worker(Runnable _firstTask) {
			this.thread = new Thread(this);
			this.firstTask = _firstTask;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Runnable task = firstTask;
			try {
				while (task != null || (task = getTask()) != null) {
					task.run();
					if (ctl.get() > maximumPoolSize) {
						break;
					}
					task = null;
				} 
			} finally {
				ctl.decrementAndGet();
			}
		}
		
		private Runnable getTask() {
			for (;;) {
				try {
					System.out.println("workQueue.size£∫" + workQueue.size());
					return workQueue.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	private void reject() {
		throw new RuntimeException("Error£°ctl.count£∫ " + ctl.get() + " workQueue.size£∫" + workQueue.size());
	}
	
	
	public static void main(String[] args) {
		ThreadPoolTrader tp = new ThreadPoolTrader(2, 2, new ArrayBlockingQueue<Runnable>(10));
		
		for (int i = 0; i < 10; i++) {
			int finalI = i;
			tp.execute(() -> {
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("task number: " + finalI);
			});
		}
		
		tp.hashCode();
	}

}
