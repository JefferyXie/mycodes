package com.myjava.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
	void runPool()
	{
		ExecutorService executor = Executors.newFixedThreadPool(2);
		for (int i = 0; i < 5; ++i) {
			executor.submit(new MyRunner());
		}
		executor.shutdown();
		System.out.println("All tasks submitted.");
		shutdownAndAwaitTermination(executor);
		System.out.println("All tasks completed.");
	}
	
	void runLatch()
	{
		CountDownLatch latch = new CountDownLatch(2);
		ExecutorService executor = Executors.newFixedThreadPool(2);
		for (int i = 0; i < 5; ++i) {
			executor.submit(new Runnable() {
				public void run() {
					System.out.println("task started...");
					Util.execute();
					latch.countDown();
				}
			});
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("All tasks completed.");
	}

	private void shutdownAndAwaitTermination(ExecutorService pool) {
		pool.shutdown(); // Disable new tasks from being submitted
		try {
			// Wait a while for existing tasks to terminate
			if (!pool.awaitTermination(2, TimeUnit.SECONDS)) {
				pool.shutdownNow(); // Cancel currently executing tasks
				// Wait a while for tasks to respond to being cancelled
				if (!pool.awaitTermination(2, TimeUnit.SECONDS))
					System.err.println("Pool did not terminate");
			}
		} catch (InterruptedException ie) {
			// (Re-)Cancel if current thread also interrupted
			pool.shutdownNow();
			// Preserve interrupt status
			Thread.currentThread().interrupt();
		}
	}
}
