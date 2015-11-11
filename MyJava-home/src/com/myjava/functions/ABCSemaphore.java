package com.myjava.functions;

import java.util.concurrent.Semaphore;  

// http://blog.csdn.net/niluchen/article/details/8066316
// 1. 最佳的方法 用java.util.concurrent.Semaphore 信号量
public class ABCSemaphore {  
      
    static class T extends Thread{  
          
        Semaphore me;  
        Semaphore next;  
          
        public T(String name,Semaphore me,Semaphore next){  
            super(name);  
            this.me = me;  
            this.next = next;  
        }  
          
        @Override  
        public void run(){  
            for(int i = 0 ; i < 10 ; i ++){  
                try {  
                    me.acquire();  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
                System.out.println(this.getName());  
                next.release();  
            }  
        }  
    }  
      
    public static void main(String[] args) {  
        Semaphore aSem = new Semaphore(1);  
        Semaphore bSem = new Semaphore(0);  
        Semaphore cSem = new Semaphore(0);  
          
        T a = new T("A",aSem,bSem);  
        T b = new T("B",bSem,cSem);  
        T c = new T("C",cSem,aSem);  
          
        a.start();  
        b.start();  
        c.start();  
    }  
}  