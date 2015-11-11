package com.myjava.concurrent;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableFuture {
	static void run() {
		System.out.println("CallableFuture.run()");
		
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<?> future = executor.submit(new Callable<Integer>() {
			public Integer call() throws Exception {
				int duration = new Random().nextInt(5000);
				if (duration > 2000) throw new Exception("Duration is too long.");
				System.out.println("Starting...");
				Thread.sleep(duration);
				System.out.println("Finished.");
				return duration;
			}
		});
		executor.shutdown();
		try {
			System.out.println("Result is: " + future.get());
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("CallableFuture.run() ends.");
	}
}
