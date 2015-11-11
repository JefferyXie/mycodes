package com.myjava.concurrent;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Interruption {
	static void runThread() {
		System.out.println("Interruption.runThread() starting...");
		Thread th = new Thread(() -> {
			int i = 0;
			do {
				if (Thread.currentThread().isInterrupted()) {
					System.out.println("thread is interrupted!");
					break;
				}
				Math.sin(new Random().nextDouble());
			} while (i++ < 1E7);
			System.out.println("thread completes.");
		});
		th.start();
		try {
			Thread.sleep(500);
			th.interrupt();
			th.join();
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("Interruption.runThread() finished.");
	}
	static void runPool() {
		System.out.println("Interruption.runPool() starting...");

		ExecutorService executor = Executors.newCachedThreadPool();
		Future<?> future = executor.submit(new Callable<Void>() {
			public Void call() throws Exception {
				int i = 0;
				do {
					if (Thread.currentThread().isInterrupted()) {
						System.out.println("thread is interrupted!");
						break;
					}
					Math.sin(new Random().nextDouble());
				} while (i++ < 1E7);
				System.out.println("thread completes.");
				return null;
			}
		});
		
		executor.shutdown();
		try {
			Thread.sleep(500);
			executor.shutdownNow();
			//future.cancel(true);
			executor.awaitTermination(1, TimeUnit.MINUTES);
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("Interruption.runPool() ends.");
	}
}
