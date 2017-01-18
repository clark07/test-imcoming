package com.cs.concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
	
	public static void main(String[] args) {
		
		/*Semaphore semaphore = new Semaphore(5);
		AtomicInteger ai = new AtomicInteger();
		for (int i = 0; i < 10; i++) {
			new Thread(
				()->{
					try {
						int num = ai.incrementAndGet();
						long t1 = System.currentTimeMillis();
						semaphore.acquire();
						long t2 = System.currentTimeMillis();
						System.out.println(String.format("people%s 占到坑 等待时间:%sms, 开始享受...", num,(t2-t1)));
						Thread.sleep(500+new Random().nextInt(500));
						long t3 = System.currentTimeMillis();
						System.out.println(String.format("people%s 使用完毕 总共耗时:%sms, 离开...", num,  (t3-t2)));
						semaphore.release();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				).start();
		}
		*/
		
		//Semaphore s = new Semaphore(10, true);
		Semaphore s = new Semaphore(10);
		
		/*try {
			System.out.println("s.isFair()-->"+s.isFair());
			System.out.println("s.availablePermits()-->"+s.availablePermits());
			s.acquire(5);
			System.out.println("s.availablePermits()-->"+s.availablePermits());
			System.out.println("s.drainPermits()-->"+s.drainPermits());
			System.out.println("s.availablePermits()-->"+s.availablePermits());
			s.release(10);
			System.out.println("s.availablePermits()-->"+s.availablePermits());
			new Thread(()->{
				s.acquireUninterruptibly(11);
				System.out.println("thread-->s.availablePermits()-->"+s.availablePermits());
			}).start();
			
			Thread.sleep(500);
			System.out.println("s.availablePermits()-->"+s.availablePermits());
			s.release(10);
			System.out.println("s.availablePermits()-->"+s.availablePermits());
			System.out.println("s.drainPermits()-->"+s.drainPermits());
			s.release(10);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
/*		try {
			s.acquire();
			s.acquire(1);
			s.acquireUninterruptibly();
			s.acquireUninterruptibly(1);
			s.availablePermits();
			s.drainPermits();
			s.getQueueLength();
			s.hasQueuedThreads();
			s.isFair();
			s.release();
			s.release(1);
			s.tryAcquire();
			s.tryAcquire(1);
			s.tryAcquire(1, TimeUnit.SECONDS);
			s.tryAcquire(1, 1, TimeUnit.SECONDS);
		} catch (Exception e2) {
			// TODO: handle exception
		}*/
		
		List<Integer> list = Arrays.asList(3,4,7,2);
		list.forEach(i->{
			new Thread(()->{
				System.out.println(String.format("runner:%s wait for begin...", i));
				
				try {
					s.acquire(i);
					
					Thread.sleep(2000);
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					s.release(i);
				}
				System.out.println(String.format("runner:%s wait over...", i));
			}).start();
			
			try {
				Thread.sleep(50);
			} catch (Exception e2) {
			}
		});
		
	}
	

}
