package com.cs.concurrent;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CyclicBarrierDemo {
	
	
	
	
	public static void main(String[] args) {
		CyclicBarrier cb = new CyclicBarrier(2, ()->{
			System.out.println("execute runable");
		});
		
		CyclicBarrier cb1 = new CyclicBarrier(1, ()->{
			System.out.println("execute runable cb1");
		});
		new Thread(()->{
			try {
				cb1.await();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("cb1 test");
		}).start();
		
		
		AtomicInteger ai = new AtomicInteger();
		for (int i = 0; i < 5; i++) {
			int j = i;
			new Thread(()->{
				try {
					System.out.println(String.format("runner:%s wait for begin...%s", j, cb.getNumberWaiting()));
					long st = System.currentTimeMillis();
					
					Thread.sleep(100+new Random().nextInt(900));
					long et = System.currentTimeMillis();
					
					System.out.println(String.format("runner:%s wait over cost:%sms", j, (et-st)));
					System.out.println(String.format("is broken?:%s", cb.isBroken()));
					
					int curNum = ai.getAndIncrement();
					
					if(curNum==4){
						cb.reset();
					}
					if(curNum==2){
						cb.await(5, TimeUnit.SECONDS);
					}else{
						cb.await();
					}
					System.out.println(String.format("runner:%s begin execute...", j));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();
		}
	}

}
