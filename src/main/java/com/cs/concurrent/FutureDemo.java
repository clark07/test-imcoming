package com.cs.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class FutureDemo {

	
	public static void main(String[] args) throws Exception {
		
		ExecutorService es = Executors.newFixedThreadPool(10);
		
		AtomicInteger ai = new AtomicInteger();
		
		Future<AtomicInteger> f = es.submit(()->{
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
			}
			ai.incrementAndGet();
		}, ai);
		System.out.println(f.get().get());
		System.out.println(ai.get());
		
		Future<String> f2 = es.submit(()->{
			try {
				Thread.sleep(10000);
			} catch (Exception e) {
			}
			ai.incrementAndGet();
			return "100";
		});
		
		System.out.println(f2.get());
		System.out.println(ai.get());
		
		
	}
}
