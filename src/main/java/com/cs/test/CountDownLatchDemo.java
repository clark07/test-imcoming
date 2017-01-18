package com.cs.test;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2016/12/28.
 */
public class CountDownLatchDemo {

	//private final static Logger log = Logger.getLogger(CountDownLatchDemo.class.getName());

	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(2);

/*		for (int i = 0; i < 5; i++) {

			final int j = i;
			new Thread(() -> {
				System.out.println(String.format("thread:%s exec..", j));
				try {
					latch.await();
				} catch (Exception e) {
				}

				System.out.println(String.format("thread:%s end exec.", j));
			}).start();
		}*/

		latch.countDown();
		try {
			latch.await(1, TimeUnit.SECONDS);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		latch.countDown();
		

	}

}
