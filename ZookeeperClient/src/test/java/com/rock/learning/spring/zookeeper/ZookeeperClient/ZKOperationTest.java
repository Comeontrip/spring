package com.rock.learning.spring.zookeeper.ZookeeperClient;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.rock.learning.spring.zk.zkClient.zoo.MyZooKeeper;
import com.rock.learning.spring.zk.zkClient.zoo.ZKOperation;

import junit.framework.Assert;

public class ZKOperationTest {

	private String hosts = "localhost:2181,localhost:2182,localhost:2183";
	String rootPath = "/TestZookeeper";
	String child1Path = rootPath + "/hello1";
	String child2Path = rootPath + "/word1";
	private ZKOperation zkOperation;
	
	@Before
	public void init() {
		zkOperation = new ZKOperation();
		MyZooKeeper zooKeeper = new MyZooKeeper();
		zooKeeper.connect(hosts);
	}

	@Test
	public void testCreateZNode() {
		zkOperation.createZNode(rootPath, "Parent Node Data");
		Assert.assertEquals("Parent Node Data", zkOperation.readData(rootPath));
	}
	
	@Test
	public void testCreateChildrenZNode() {
		zkOperation.createZNode(child1Path, "First Child Node Data");
		Assert.assertEquals("First Child Node Data", zkOperation.readData(child1Path));
		
		zkOperation.createZNode(child2Path, "Second Child Node Data");
		Assert.assertEquals("Update 2nd Child Data", zkOperation.readData(child2Path));
	}

	@Test
	public void testDeleteZNode() {
		if(zkOperation.isExists(child1Path))
			zkOperation.deleteZNode(child1Path);
		Assert.assertEquals(null, zkOperation.readData(child1Path));
	}

	@Test
	public void testUpdateZNodeData() {
		if(zkOperation.isExists(child2Path))
			zkOperation.updateZNodeData(child2Path, "Update 2nd Child Data");
		Assert.assertEquals("Update 2nd Child Data", zkOperation.readData(child2Path));
	}

	@Test
	public void testGetChild() {
		List<String> childPaths = zkOperation.getChild(rootPath);
		childPaths.forEach(item -> System.out.println(item));
		childPaths.forEach(System.out::println);
	}

}
