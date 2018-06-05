package com.rock.learning.spring.zk.zkClient.zoo;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

public class ZKOperation {

	Logger logger = Logger.getLogger(ZKOperation.class);

	MyZooKeeper myZooKeeper = new MyZooKeeper();

	/**
	 * create ZooKeeper persistence node
	 * 
	 * @param path
	 * @param data
	 * @return
	 */
	public boolean createZNode(String path, String data) {
		boolean result = Boolean.FALSE;
		try {
			if (!isExists(path)) {
				String zkPath = MyZooKeeper.getConnection().create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
						CreateMode.PERSISTENT);

				logger.info("Create ZooKeeper Node Successful, Node Path is:" + zkPath);
				result = Boolean.TRUE;
			}
		} catch (KeeperException e) {
			logger.error("Create ZooKeeper Node failed." + e.getMessage() + ", path =" + path, e);
		} catch (InterruptedException e) {
			logger.error("Create ZooKeeper Node failed." + e.getMessage() + ", path =" + path, e);
		}

		return result;
	}

	/**
	 * delete ZooKeeper Node without check version number version number
	 * 
	 * @param path
	 * @return
	 */
	public boolean deleteZNode(String path) {
		boolean result = Boolean.FALSE;

		try {
			if (this.isExists(path)) {
				MyZooKeeper.getConnection().delete(path, -1);
				logger.info("Delete ZooKeeper Node Success, Node Path = " + path);
				result = Boolean.TRUE;
			}

		} catch (InterruptedException e) {
			logger.error("Delete ZooKeeper Node failed:" + e.getMessage() + ", Path = " + path, e);
		} catch (KeeperException e) {
			logger.error("Delete ZooKeeper Node failed:" + e.getMessage() + ", Path = " + path, e);
		}

		return result;
	}

	/**
	 * Update designated ZooKeeper node data
	 * 
	 * @param path
	 * @param data
	 * @return if update success, return True; otherwise, return False
	 */
	public boolean updateZNodeData(String path, String data) {
		try {
			Stat stat = MyZooKeeper.getConnection().setData(path, data.getBytes(), -1);
			logger.info("Update ZNode Data Success, Node Path = " + path + ", Stat: " + stat);
			return Boolean.TRUE;
		} catch (KeeperException e) {
			logger.error("Update ZNode Data failed:" + e.getMessage() + ", Path = " + path, e);
		} catch (InterruptedException e) {
			logger.error("Update ZNode Data failed:" + e.getMessage() + ", Path = " + path, e);
		}
		return Boolean.FALSE;
	}

	/**
	 * Read data from node
	 * 
	 * @param path
	 * @return
	 */
	public String readData(String path) {
		String data = null;
		try {
			if (this.isExists(path)) {
				data = new String(MyZooKeeper.getConnection().getData(path, false, null));
				logger.info("Read Data Success, Path = " + path + ", content: " + data);
			}
		} catch (KeeperException e) {
			logger.error("Read Data failed:" + e.getMessage() + ", Path = " + path, e);
		} catch (InterruptedException e) {
			logger.error("Read Data failed:" + e.getMessage() + ", Path = " + path, e);
		}

		return data;
	}

	public List<String> getChild(String path) {
		try {
			List<String> list = MyZooKeeper.getConnection().getChildren(path, false);
			if (list.isEmpty()) {
				logger.info("Path:" + path + " don't have child nodes");
			}
			return list;
		} catch (KeeperException e) {
			logger.error("Get child failed:" + e.getMessage() + ", Path = " + path, e);
		} catch (InterruptedException e) {
			logger.error("Get child failed:" + e.getMessage() + ", Path = " + path, e);
		}
		return null;
	}

	public boolean isExists(String path) {
		try {
			Stat stat = MyZooKeeper.getConnection().exists(path, false);
			return null != stat;
		} catch (KeeperException e) {
			logger.error("Check Path exists failed:" + e.getMessage() + ", Path = " + path, e);
		} catch (InterruptedException e) {
			logger.error("Check Path exists failed:" + e.getMessage() + ", Path = " + path, e);
		}

		return Boolean.FALSE;
	}
}
