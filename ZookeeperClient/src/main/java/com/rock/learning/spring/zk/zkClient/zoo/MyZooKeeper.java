package com.rock.learning.spring.zk.zkClient.zoo;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

/**
 * ZooKeeper Monitor
 * 
 * @author Rock ChenShugui
 *
 */
public class MyZooKeeper implements Watcher {

	Logger logger = Logger.getLogger(MyZooKeeper.class);

	protected CountDownLatch countDownLatch = new CountDownLatch(1);

	// cache time
	private static final int SESSION_TIME = 2000;

	private static ZooKeeper zooKeeper = null;

	public void process(WatchedEvent event) {
		logger.info("received event:" + event.getState());
		if (event.getState() == KeeperState.SyncConnected) {
			countDownLatch.countDown();
		}
	}

	/*
	 * connect to ZooKeeper server
	 */
	public void connect(String hosts) {
		if (null == zooKeeper) {
			try {
				zooKeeper = new ZooKeeper(hosts, SESSION_TIME, this);
				countDownLatch.await();
			} catch (IOException e) {
				logger.error("connection failed, encounter IOException, e " + e.getMessage(), e);
			} catch (InterruptedException e) {
				logger.error("connection failed, encounter InterruptedException, e " + e.getMessage(), e);
			}
		}
	}
	
	public synchronized static ZooKeeper getConnection() {
		return zooKeeper;
	}
	
	/**
	 * close ZooKeeper Connection
	 */
	public void close() {
		if(null != zooKeeper) {
			try {
				zooKeeper.close();
			} catch (InterruptedException e) {
				logger.error("release connection error ," + e.getMessage(),e);
			}
		}
	}

}
