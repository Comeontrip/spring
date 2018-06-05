package com.rock.learning.java8;

public class RunnableTest {

	public static void main(String[] args) {
		System.out.println("===== Runnable Test =====");
		
		Runnable r1 = new Runnable() {
			public void run() {
				System.out.println("Hello World One!");
			}
		};
		
		Runnable r2 = () -> System.out.println("Hellow World Two!");
		
		r1.run();
		r2.run();
	}
}
