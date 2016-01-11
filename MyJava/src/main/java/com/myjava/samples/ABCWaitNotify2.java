package com.myjava.samples;

// 3. 同样用用wait 和 notify 实现 ，别人成果，自己加入了自己的理解
public class ABCWaitNotify2 implements java.lang.Runnable {  
	  
    private class Switch {  
        public Switch(boolean printedSingal) {  
            this.isLighting = printedSingal;  
        }  
        public boolean isLighting;  
    }  
  
    Switch aSwitch = new Switch(false);  
  
    Switch bSwitch = new Switch(false);  
  
    Switch cSwitch = new Switch(false);  
  
    public void run() {  
        for (int i = 0; i < 10; i++) {  
            if (Thread.currentThread().getName().equals("A")) {  
                synchronized (cSwitch) {  
  
                    System.out.print(Thread.currentThread().getName());  
                    synchronized (aSwitch) {  
                        aSwitch.isLighting = true;  
                        aSwitch.notify();  
                    }  
  
                    while (!cSwitch.isLighting) {  
                        try {  
                            cSwitch.wait();  
                        } catch (InterruptedException e) {  
                            e.printStackTrace();  
                        }  
                    }  
  
                    cSwitch.isLighting = false;  
                }  
            } else if (Thread.currentThread().getName().equals("B")) {  
                synchronized (aSwitch) {  
                    while (!aSwitch.isLighting) {  
                        try {  
                            aSwitch.wait();  
                        } catch (InterruptedException e) {  
                            e.printStackTrace();  
                        }  
                    }  
                    System.out.print(Thread.currentThread().getName());  
                    aSwitch.isLighting = false;  
                    synchronized (bSwitch) {  
                        bSwitch.isLighting = true;  
                        bSwitch.notify();  
                    }  
                }  
            } else if (Thread.currentThread().getName().equals("C")) {  
                synchronized (bSwitch) {  
                    while (!bSwitch.isLighting) {  
                        try {  
                            bSwitch.wait();  
                        } catch (InterruptedException e) {  
                            e.printStackTrace();  
                        }  
                    }  
                    System.out.print(Thread.currentThread().getName());  
                    bSwitch.isLighting = false;  
                    synchronized (cSwitch) {  
                        cSwitch.isLighting = true;  
                        cSwitch.notify();  
                    } 
                } 
            }  
        } 
    } 
  
    public static void main(String[] args) {  
        ABCWaitNotify2 rt = new ABCWaitNotify2();  
        Thread aThread = new Thread(rt, "A");  
        Thread bThread = new Thread(rt, "B");  
        Thread cThread = new Thread(rt, "C");  
        aThread.start();  
        bThread.start();  
        cThread.start();  
    }  
}  