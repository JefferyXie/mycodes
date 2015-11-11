package com.myjava.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SynchronizedKey {

	private Random random = new Random();
	
	// to only lock member rather whole object, use separate lock object
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	private List<Integer> list1 = new ArrayList<Integer>();
	private List<Integer> list2 = new ArrayList<Integer>();

	private int count = 0;
	// @synchronized method is object level so whole object is locked
	private synchronized void increment() { ++count; }
	
	public void runIncrement()
	{
		Thread th1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; ++i)
					increment();
			}
		});
		Thread th2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; ++i)
					increment();
			}
		});
		th1.start();
		th2.start();
		
		try {
			th1.join();
			th2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Count: " + count);
	}
	
	public void runList()
	{
		System.out.println("Starting...");
		long start = System.currentTimeMillis();
		
		Thread th1 = new Thread(new Runnable() {
			@Override
			public void run() {
				process();
			}
		});
		Thread th2 = new Thread(new Runnable() {
			@Override
			public void run() {
				process();
			}
		});
		th1.start();
		th2.start();
		try
		{
			th1.join();
			th2.join();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("Time used: " + (end-start));
		System.out.println("List1: " + list1.size() + "; List2: " + list2.size());
	}

	private void stageOne()
	{
		synchronized (lock1) {
			list1.add(random.nextInt(100));
		}
	}
	private void stageTwo()
	{
		synchronized (lock2) {
			list2.add(random.nextInt(100));
		}
	}
	private void process()
	{
		for (int i = 0; i < 100; ++i)
		{
			stageOne();
			stageTwo();
		}
	}
}
