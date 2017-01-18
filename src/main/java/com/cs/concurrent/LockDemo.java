package com.cs.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockDemo {

	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		
		for (int i = 0; i < 3; i++) {
			int j = i;
			new Thread(()->{
				
				try {
					if(j==1)
					Thread.sleep(50);
				} catch (Exception e) {
				}
				
				lock.lock();
				System.out.println(j+"-->getLock");
				
				try {
					Thread.sleep(500);
				} catch (Exception e) {
				}
				
				
				lock.unlock();
				System.out.println(j+"-->unLock");
			}).start();
			
			try {
				if(i==0)
				Thread.sleep(50);
			} catch (Exception e) {
			}
		}
		
		ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();

		
		for (int i = 0; i < 3; i++) {
			int j = i;
			new Thread(()->{
				
				Lock k = null;
				if(j==2){
					k = rwlock.writeLock();
				}else{
					k = rwlock.readLock();
				}
				k.lock();
				System.out.println(j+"-->getLock");
				
				
				System.out.println(j+"-->unLock");
				k.unlock();
			}).start();
		}
	}
	
}
