package com.rock.learning.thread.MutiThreadLearning;

import java.util.Calendar;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MutiThreadTest {

	public static void main(String[] args) {

		// Runnable
		Runnable task = () -> {
			try {
				String name = Thread.currentThread().getName();

				System.out.println(name + " Foo ");
				TimeUnit.SECONDS.sleep(1);

				System.out.println(name + " Bar ");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

		// Thread
		Thread thread = new Thread(task);
		thread.start();

		System.out.println("Main Thread Done!");

		// Executor Framework
		ExecutorService executor = Executors.newSingleThreadExecutor();
		ExecutorService mutipleExecutor = Executors.newFixedThreadPool(3);
		executor.submit((Runnable) () -> {
			try {
				String threadName = Thread.currentThread().getName();
				System.out.println(threadName + " Hello ");

				TimeUnit.SECONDS.sleep(30);
				System.out.println("Executor runnable executed !");
			} catch (InterruptedException e) {
				System.err.println("task interrupted");
			}
		});

		try {
			System.out.println("attemp to shutdown executor");
			// executor.shutdown();
			executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.err.println("task interrupted");
		} finally {
			if (!executor.isTerminated()) {
				System.err.println("Cancel non-finished tasks");
			}

			// executor.shutdownNow();
			// System.out.println("shutdown finished");
		}

		// Executor with Callable
		Future<String> future = executor.submit((Callable<String>) () -> {
			String threadName = Thread.currentThread().getName();
			System.out.println(threadName + " at callable method.");
			TimeUnit.SECONDS.sleep(4);
			return "123";
		});

		try {
			System.out.println(Calendar.getInstance().getTime() + " future done ?" + future.isDone());
			String result = future.get();
			System.out.println(Calendar.getInstance().getTime() + "future done ?" + future.isDone());
			System.out.println("result: " + result);
		} catch (InterruptedException e) {
			System.err.println("task interrupted" + e.getMessage());
		} catch (ExecutionException e) {
			System.err.println("Execution exception" + e.getMessage());
		} finally {
			if (!executor.isTerminated()) {
				System.err.println("Callable - Cancel non-finished tasks");
			}

			executor.shutdownNow();
			System.out.println("Callable - shutdown finished");
		}
	}

}
