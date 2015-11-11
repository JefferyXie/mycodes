package com.myjava.basic;

public enum Transport {
	CAR(60), TRUCK(50), AIRPLANE(600), TRAIN(70), BOAT(25), SUBWAY("45");
	
	private int speed;
	Transport(int s) {
		System.out.println("Transport(" + s + ")");
		speed = s;
	}
	Transport(String s) {
		System.out.println("Transport(\"" + s + "\")");
		try {
			speed = Integer.parseInt(s);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
	int getSpeed() {
		return speed;
	}
	
	static void runEnum() {
		for (Transport tp : Transport.values()) {
			System.out.println(tp.ordinal() + ": " +
							tp + " speed is " + tp.getSpeed() + ".");
		}
		Transport t = Transport.valueOf("TRAIN");
		switch (t) {
		case CAR:
			System.out.println("[" + t + "] is CAR.");
			break;
		case TRUCK:
			System.out.println("[" + t + "] is TRUCK.");
			break;
		case AIRPLANE:
			System.out.println("[" + t + "] is AIRPLANE.");
			break;
		case TRAIN:
			System.out.println("[" + t + "] is TRAIN.");
			break;
		case BOAT:
			System.out.println("[" + t + "] is BOAT.");
			break;
		case SUBWAY:
			System.out.println("[" + t + "] is SUBWAY.");
			break;
		default:
			break;
		}
	}
}
