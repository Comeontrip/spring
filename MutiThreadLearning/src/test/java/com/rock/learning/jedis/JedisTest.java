package com.rock.learning.jedis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.rock.learn.redis.JedisUtil;

public class JedisTest {
	protected static Logger logger = Logger.getLogger(JedisTest.class);

	public static void main(String[] args) {
		// public void testSingleton() {
		System.out.print("why cannot print");
		ExecutorService executor = Executors.newFixedThreadPool(10);

		try {
			for (int i = 0; i < 10; i++) {
				executor.submit(() -> {
					JedisUtil jedisUtil = JedisUtil.getInstance2();
					System.out.println(jedisUtil.hashCode());
				});
			}
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		} finally {
			executor.shutdown();
		}

	}
}
