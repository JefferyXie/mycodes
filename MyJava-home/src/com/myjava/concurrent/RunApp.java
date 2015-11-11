package com.myjava.concurrent;

public class RunApp {

	public static void main(String[] args) {
		
		//Reentrant.run();
		//TryLock.run();
		//CallableFuture.run();
		//Interruption.runThread();
		Interruption.runPool();
		
		//RunApp simpleRun = new RunApp();
		//simpleRun.runLamdaThread();
		
		/*SynchronizedKey oList = new SynchronizedKey();
		oList.runIncrement();
		oList.runList();
		
		ThreadPool pool = new ThreadPool();
		pool.runPool();
		pool.runLatch();*/
		
		//ProducerConsumer procon = new ProducerConsumer();
		//procon.runBlockingQueue();
		//procon.runNotify();
		//procon.runNative();
	}
	
	void runThread() {
		MyThread th1 = new MyThread();
		MyThread th2 = new MyThread();
		th1.setPriority(Thread.MIN_PRIORITY);
		th2.setPriority(Thread.MAX_PRIORITY);
		th1.start();
		th2.start();

		//Thread th3 = new Thread(new MyRunner());
		//Thread th4 = new Thread(new MyRunner());
		//th3.start();
		//th4.start();

		new Thread(new Runnable() {
			public void run() { Util.execute(); }
		}).start();
	}
	static boolean stop = false;
	static long currentThread = 0;
	void runLamdaThread() {
		Runnable runLamda = () -> {
			int count = 0;
			do {
				++count;
				if (currentThread != Thread.currentThread().getId()) {
					currentThread = Thread.currentThread().getId();
					//System.out.println("Running in thread: " + currentThread);
				}
			} while (!stop && count < 100000);
			stop = true;
			System.out.println("Thread [" + Thread.currentThread().getId() + 
					"], priority [" + Thread.currentThread().getPriority() + 
					"]: " + count);
		};
		Thread th1 = new Thread(runLamda);
		Thread th2 = new Thread(runLamda);
		Thread th3 = new Thread(runLamda);
		th1.setPriority(Thread.MIN_PRIORITY);
		th2.setPriority(Thread.NORM_PRIORITY);
		th3.setPriority(Thread.MAX_PRIORITY);
		th1.start();
		th2.start();
		th3.start();
		while (th1.isAlive() || th2.isAlive() || th3.isAlive()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
