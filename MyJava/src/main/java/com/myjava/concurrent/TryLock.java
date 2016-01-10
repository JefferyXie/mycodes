package com.myjava.concurrent;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLock extends Reentrant {

	class Account {
		private int balance = 10000;

		void transfer(Account acctTo, int amount) {
			this.balance -= amount;
			acctTo.balance += amount;
		}

		int GetBalance() {
			return balance;
		}
	}

	private Account account1 = new Account();
	private Account account2 = new Account();
	private Lock locker1 = new ReentrantLock();
	private Lock locker2 = new ReentrantLock();

	static void run() {
		TryLock tryLock = new TryLock();
		tryLock.execute();
		System.out.println("account1's balance: " + tryLock.account1.GetBalance() + "\naccount2's balance: "
				+ tryLock.account2.GetBalance() + "\nTotal balance: "
				+ (tryLock.account1.GetBalance() + tryLock.account2.GetBalance()));
	}

	void acquireLockers(Lock lock1, Lock lock2) throws InterruptedException {
		boolean bLock1 = false;
		boolean bLock2 = false;
		while (true) {
			try {
				bLock1 = lock1.tryLock();
				bLock2 = lock2.tryLock();
			} finally {
				if (bLock1 && bLock2)
					break;
				if (bLock1) lock1.unlock();
				if (bLock2) lock2.unlock();
			}
			Thread.sleep(10);
		}
	}

	void releaseLockers(Lock lock1, Lock lock2) {
		try {
			lock1.unlock();
			lock2.unlock();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void run1() {
		try {
			acquireLockers(locker1, locker2);
			account1.transfer(account2, new Random().nextInt(100));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			releaseLockers(locker1, locker2);
		}
	}

	void run2() {
		try {
			acquireLockers(locker2, locker1);
			account2.transfer(account1, new Random().nextInt(100));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			releaseLockers(locker2, locker1);
		}
	}
}
