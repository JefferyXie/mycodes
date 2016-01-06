package com.myjava.functions;

// 2. ��wait �� notify ʵ�� ͬ������� �ź�����˼�룬�Լ�������������¸ĵ�
/** 
 *  
 * <p> 
 * Title: Ѹ�ױ��ԵĲ��� 
 * </p> 
 * <p> 
 * Description: ��abc ��������յ�ƣ�ѭ�������������ؿ��������Ƶģ������˴��������߳� ÿ���ȿ�ǰһյ�Ƶ����Ƶ���� 
 * �ڿ��Լ�������������Ƿ����ƣ�Ȼ�����Ƶ���Ϣ֪ͨ���¸��� 
 * </p> 
 * <p> 
 * Copyright: Copyright (c) 2007-2010 
 * </p> 
 * <p> 
 * Company: 
 * </p> 
 *  
 * @author 
 * @version 1.0 2010-9-17 
 */  
public class ABCWaitNotify1 implements java.lang.Runnable {  
    // �ڲ������� isLighting=true ��ʾ�ƿ�  
    private class Switch {  
        public Switch(boolean printedSingal) {  
            this.isLighting = printedSingal;  
        }  
  
        public boolean isLighting;  
    }  
  
    // a�ƵĿ���  
    Switch aSwitch = new Switch(false);  
  
    // b�ƵĿ���  
    Switch bSwitch = new Switch(false);  
  
    // c�ƵĿ��� ��ʼ�� Ĭ��Ϊ���ţ������´�a�Ʋ�����  
    Switch cSwitch = new Switch(true);  
  
    public void run() {  
        // TODO Auto-generated method stub  
  
        for (int i = 0; i < 10; i++) {  
            // �����A�߳�  
            if (Thread.currentThread().getName().equals("A")) {  
                // ������ȥ��ǰһյ�Ƶ�״̬���ھ����Ƿ��ֵ��Լ�����  
                // Aȥ������Ƶ�c�ƵĿ��أ������ʱc�Ŀ��ؿ��У�A��ס���أ���ֹ���˰�Ū  
                synchronized (cSwitch) {  
                    // ��ѯ��һյ��c�����cһֱ���ţ���ȴ������������Ѿ�Ĭ�ϳ�ʼ��c����  
                    // �ʲ�������  
                    while (!cSwitch.isLighting) {  
                        try {  
                            // ����c���ĺ���Ϣ  
                            cSwitch.wait();  
                        } catch (InterruptedException e) {  
                            // TODO Auto-generated catch block  
                            e.printStackTrace();  
                        }  
                    }  
                    // c�������ˣ��ø�a���� �ȴ�ӡ���Լ�  
                    System.out.print(Thread.currentThread().getName());  
                    // �ص�c��  
                    cSwitch.isLighting = false;  
                    // Aȥ������Ƶ�a�ƵĿ��أ������ʱa�Ŀ��ؿ��У�A��ס���أ���ֹ���˰�Ū  
                    synchronized (aSwitch) {  
                        // a������  
                        aSwitch.isLighting = true;  
                        // ��a��������Ϣ֪ͨ��B  
                        aSwitch.notify();  
                    }  
  
                }  
            } // B�߳��ϳ��ˣ�ͬ��  
            else if (Thread.currentThread().getName().equals("B")) {  
                synchronized (aSwitch) {  
                    while (!aSwitch.isLighting) {  
  
                        try {  
                            aSwitch.wait();  
                        } catch (InterruptedException e) {  
                            // TODO Auto-generated catch block  
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
                            // TODO Auto-generated catch block  
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
        ABCWaitNotify1 rt = new ABCWaitNotify1();  
        Thread aThread = new Thread(rt, "A");  
        Thread bThread = new Thread(rt, "B");  
        Thread cThread = new Thread(rt, "C");  
        aThread.start();  
        bThread.start();  
        cThread.start();  
    }  
}  