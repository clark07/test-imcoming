package com.cs.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountLatchDownDemo {

	
	
	public static void main(String[] args) {
		CountDownLatch begin = new CountDownLatch(1);
		CountDownLatch end = new CountDownLatch(10);
		
		for (int i = 0; i < 10; i++) {
			int j = i;
			new Thread(()->{
				try {
					System.out.println(String.format("runner:%s wait for begin...", j));
					begin.await();
					System.out.println(String.format("runner:%s begin run...", j));
					
					long st = System.currentTimeMillis();
					
					Thread.sleep(100+new Random().nextInt(900));
					long et = System.currentTimeMillis();
					
					System.out.println(String.format("runner:%s run over cost:%sms", j, (et-st)));
					end.countDown();
				} catch (Exception e) {
				}
			}).start();
		}
		
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		
		System.out.println("game begin ...");
		begin.countDown();
		
		try {
			//end.await(500, TimeUnit.MILLISECONDS);
			end.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("game end.");
	}
	
}
