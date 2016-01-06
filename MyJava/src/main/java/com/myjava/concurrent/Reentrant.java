package com.myjava.concurrent;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Reentrant {
	private Lock locker = new ReentrantLock();
	private Condition condi = locker.newCondition();
	private int count = 0;
	private boolean started = false;
	
	static void run() {
		Reentrant entr = new Reentrant();
		entr.execute();
		System.out.println(entr.count);
	}
	
	final void execute() {
		Thread th1 = new Thread(() -> run1());
		Thread th2 = new Thread(() -> run2());
		th1.start();
		th2.start();
		try {
			th1.join();
			th2.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void increment() {
		for (int i = 0; i < 10000; ++i) {
			count++;
		}
	}
	void run1() {
		System.out.println("Enter run1()...");
		locker.lock();
		started = true;
		try {
			System.out.println("run1() before condition.await()");
			condi.await();
			System.out.println("run1() after condition.await()");
			increment();
			System.out.println("run1() after increment()");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			locker.unlock();
			System.out.println("run1() after locker.unlock().");
		}
	}
	void run2() {
		System.out.println("Enter run2()...");
		try {
			while (!started) {
				Thread.sleep(10);
			}
			locker.lock();

			System.out.println("run2() before condition.signal(). Please input: ");
			new Scanner(System.in).nextLine();
			System.out.println("run2() before condition.signal()");
			condi.signal();
			System.out.println("run2() after condition.signal()");
			increment();
			System.out.println("run2() after increment()");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			locker.unlock();
			System.out.println("run2() after locker.unlock()");
		}
	}
}
