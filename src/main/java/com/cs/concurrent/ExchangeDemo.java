package com.cs.concurrent;

import java.util.concurrent.Exchanger;
import java.util.concurrent.atomic.AtomicInteger;

public class ExchangeDemo {
	private String var;
	
	public ExchangeDemo(String var) {
		super();
		this.var = var;
	}

	@Override
	public String toString() {
		return "ExchangeDemo [var=" + var + "]";
	}

	public static void main(String[] args) {
		Exchanger<ExchangeDemo> exchanger = new Exchanger<>();
		AtomicInteger ai = new AtomicInteger();
		for (int i = 0; i < 2; i++) {
			new Thread(()->{
				int num = ai.incrementAndGet();
				ExchangeDemo exchangeDemo = new ExchangeDemo(String.valueOf(num));
				
				try {
					System.out.println(String.format("thread:%s begin exchange from %s", num, exchangeDemo.toString()));
					ExchangeDemo temp = exchanger.exchange(exchangeDemo);
					System.out.println(String.format("thread:%s end exchange got %s", num, temp.toString()));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}).start();
		}
	}
}
