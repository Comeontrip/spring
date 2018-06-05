package com.rock.learning.jedis;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;
import com.rock.learn.redis.JedisUtil;

import redis.clients.jedis.Jedis;

@RunWith(ConcurrentTestRunner.class)
public class JedisTestSuit {
	protected Logger logger = Logger.getLogger(JedisTestSuit.class);
	private AtomicInteger counter = new AtomicInteger(100);

//	@Before
	public void testFlushDB() {
		Jedis jedis = JedisUtil.getInstance().getDefaultJedis();
		try {
			jedis.flushDB();
		}catch(Exception e) {
			logger.error("failed to flush db." + e.getMessage(),e);
		}finally {
			JedisUtil.getInstance().closeJedis(jedis);
		}
	}

	@Test
	@ThreadCount(5)
	public void testRedisWithMutipleThreads() {
		Jedis jedis = JedisUtil.getInstance().getDefaultJedis();
		try {
			int count = counter.incrementAndGet();
			jedis.set("AtomicName" + count, "Robin" + count);
			String name = jedis.get("AtomicName" + count);
			String threadName = Thread.currentThread().getName();
			System.out.println(threadName + "- AtomicName" + count + " = " + name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			JedisUtil.getInstance().closeJedis(jedis);
		}
	}


}
