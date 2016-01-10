package com.myjava.concurrent;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer {
	
	void runBlockingQueue() {
		VersionBlockingQueue o = new VersionBlockingQueue();
		o.run();
	}
	void runNotify() {
		VersionNotify o = new VersionNotify();
		o.run();
	}
	void runNative() {
		VersionNative o = new VersionNative();
		o.run();
	}
	
	class VersionBlockingQueue {
		private BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
		
		void run() {
			Thread th1 = new Thread(new Runnable() {
				public void run() {
					try {
						producer();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			Thread th2 = new Thread(new Runnable() {
				public void run() {
					try {
						consumer();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			th1.start();
			th2.start();
			try {
				th1.join(10000);
				th2.join(10000);
				System.out.println("Stopped waiting.");
				if (th1.isAlive() || th2.isAlive()) {
					System.out.println("threads are alive.");
					th1.interrupt(); // will raise InterruptedException because of above join 
					th2.interrupt();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("th1.isAlive(): " + th1.isAlive() + "; th2.isAlive(): " + th2.isAlive());
		}
		
		private void producer() throws InterruptedException {
			Random random = new Random();
			while (true && !Thread.interrupted()) {
				queue.put(random.nextInt(100));
			}
		}
		private void consumer() throws InterruptedException {
			Random random = new Random();
			while (true && !Thread.interrupted()) {
				Thread.sleep(100);
				if (random.nextInt(10) == 0) {
					Integer v = queue.take();
					System.out.println("Taken value: " + v + "; Queue size: " + queue.size());
				}
			}
		}
	}
	
	class VersionNotify {
		void run() {
			Thread th1 = new Thread(new Runnable() {
				public void run() {
					try {
						producer();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			Thread th2 = new Thread(new Runnable() {
				public void run() {
					try {
						consumer();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			th1.start();
			th2.start();
			try {
				th1.join(20000);
				th2.join(20000);
				System.out.println("Stopped waiting.");
				if (th1.isAlive() || th2.isAlive()) {
					System.out.println("threads are alive.");
					th1.interrupt(); // will raise InterruptedException because of above join 
					th2.interrupt();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("th1.isAlive(): " + th1.isAlive() + "; th2.isAlive(): " + th2.isAlive());
		}
		private void producer() throws InterruptedException {
			synchronized (this) {
				System.out.println("Producer: start running...");
				while (true) {
					System.out.println("Producer: wait...");
					wait();
					System.out.println("Producer: resumed.");
				}
			}
		}
		private void consumer() throws InterruptedException {
			Scanner scanner = new Scanner(System.in);
			Thread.sleep(1000);
			synchronized (this) {
				// below while loop tells the producer cannot resume until notify block is completed!
				while (true) {
					System.out.println("Consumer: wait for input...");
					scanner.nextLine();
					System.out.println("Consumer: a key is input.");
					notify();
					Thread.sleep(3000);
				}
			}
		}
	}

	class VersionNative {
		private LinkedList<Integer> list = new LinkedList<>();
		private final int LIMIT = 10;
		private Object lock = new Object();
		
		void run() {
			Thread th1 = new Thread(new Runnable() {
				public void run() {
					try {
						producer();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			Thread th2 = new Thread(new Runnable() {
				public void run() {
					try {
						consumer();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			th1.start();
			th2.start();
			try {
				th1.join(20000);
				th2.join(20000);
				System.out.println("Stopped waiting.");
				if (th1.isAlive() || th2.isAlive()) {
					System.out.println("threads are alive.");
					th1.interrupt(); // will raise InterruptedException because of above join 
					th2.interrupt();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("th1.isAlive(): " + th1.isAlive() + "; th2.isAlive(): " + th2.isAlive());
		}
		private void producer() throws InterruptedException {
			Integer value = 0;
			while (true) {
				synchronized (lock) {
					while (list.size() == LIMIT) {
						System.out.println("Wait starts.");
						lock.wait();
						System.out.println("Wait ends.");
					}
					
					list.add(++value);
					lock.notify();
					System.out.println("Added value: " + value);
				}
			}
		}
		private void consumer() throws InterruptedException {
			while (true) {
				synchronized (lock) {
					while (list.size() == 0) {
						lock.wait();
					}
					System.out.print("List size is: " + list.size());
					int value = list.removeFirst();
					System.out.println("; removed value is: " + value);
					lock.notify();
				}
				//Thread.sleep(5000);
			}
		}
	}

	class VersionReentrantLock {
		private int count = 0;
		private Lock lock = new ReentrantLock();
		private Condition cond = lock.newCondition();
		
		void run() {
			Thread th1 = new Thread(new Runnable() {
				public void run() {
					try {
						producer();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			Thread th2 = new Thread(new Runnable() {
				public void run() {
					try {
						consumer();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			th1.start();
			th2.start();
			try {
				th1.join(20000);
				th2.join(20000);
				System.out.println("Stopped waiting.");
				if (th1.isAlive() || th2.isAlive()) {
					System.out.println("threads are alive.");
					th1.interrupt(); // will raise InterruptedException because of above join 
					th2.interrupt();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("th1.isAlive(): " + th1.isAlive() + "; th2.isAlive(): " + th2.isAlive());
		}
		private void increment() {
			for (int i = 0; i < 100; ++i) count++;
		}
		private void producer() throws InterruptedException {
			lock.lock();
			System.out.println("Consumer condition wait...");
			cond.await();
			System.out.println("Consumer wake up!");
			try {
				increment();
			} finally {
				lock.unlock();
			}
		}
		private void consumer() throws InterruptedException {
			Thread.sleep(1000);
			lock.lock();
			System.out.println("Consumer press return key...");
			new Scanner(System.in).nextLine();
			System.out.println("Consumer got the key!");
			cond.signal();
			try {
				increment();
			} finally {
				lock.unlock();
			}
		}
	}
}
