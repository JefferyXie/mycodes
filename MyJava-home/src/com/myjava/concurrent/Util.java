package com.myjava.concurrent;

public final class Util {
	private Util() {}
	
	static void execute()
	{
		for (int i = 0; i < 10; ++i)
		{
			System.out.println("Thread [" + Thread.currentThread().getId() + "]: " + i);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
