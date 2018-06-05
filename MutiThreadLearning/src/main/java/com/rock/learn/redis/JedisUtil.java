package com.rock.learn.redis;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtil {
	protected static Logger logger = Logger.getLogger(JedisUtil.class);
	private static JedisUtil instance;

	private static Map<String, JedisPool> maps = new HashMap<>();

	private JedisUtil() {
	}

	private static JedisPool getPool(String ipAddress, int port) {
		String key = ipAddress + ":" + port;
		JedisPool pool = null;

		if (maps.containsKey(key)) {
			return maps.get(key);
		}

		synchronized (JedisUtil.class) {
			if (!maps.containsKey(key)) {
				String threadName = Thread.currentThread().getName();
				JedisPoolConfig config = new JedisPoolConfig();
				config.setMaxIdle(JedisConfig.MAX_IDLE);
				config.setMaxWaitMillis(JedisConfig.MAX_WAIT);
				config.setMaxTotal(JedisConfig.MAX_TOTAL);
				config.setTestOnBorrow(Boolean.TRUE);
				config.setTestOnReturn(Boolean.TRUE);
				try {
					pool = new JedisPool(config, ipAddress, port, JedisConfig.TIME_OUT);
					logger.info(threadName + " - Successful get redis pool.");
				} catch (Exception e) {
					logger.error(threadName + " - Failed to create Jedis Pool." + e.getMessage(), e);
				}
				maps.put(key, pool);
			} else {
				pool = maps.get(key);
			}
		}

		return pool;
	}

	private static class JedisUtilBuilder {
		private static JedisUtil instance = new JedisUtil();
	}

	public static JedisUtil getInstance2() {
		String threadName = Thread.currentThread().getName();
		logger.info(threadName + " - start to get instance.");
		synchronized (JedisUtil.class) {
			if (instance != null)
				return instance;

			logger.info(threadName + " - Successful create JedisUtil instance.");
			instance = new JedisUtil();
		}

		return instance;
	}

	public static JedisUtil getInstance() {
		return JedisUtil.JedisUtilBuilder.instance;
	}

	@SuppressWarnings("deprecation")
	public Jedis getJedis(String ipAddress, int port) {
		Jedis jedis = null;
		int count = 0;
		do {
			try {
				jedis = getPool(ipAddress, port).getResource();
				count++;
			} catch (Exception e) {
				String threadName = Thread.currentThread().getName();
				logger.error(threadName + " - Failed to get Redis." + e.getMessage(), e);
				// TODO: need to test if not destroy Jedis Pool if failed to get it, what will
				// happen.
				getPool(ipAddress, port).returnBrokenResource(jedis);
			}
		} while (null == jedis && count < JedisConfig.MAX_TRY);

		return jedis;
	}

	public Jedis getDefaultJedis() {
		return getJedis(JedisConfig.HOST, JedisConfig.PORT);
	}
	
	@SuppressWarnings("deprecation")
	public void closeJedis(Jedis jedis) {  
        if(jedis != null) {  
            getPool(JedisConfig.HOST, JedisConfig.PORT).returnResource(jedis);  
        }  
    }

}
