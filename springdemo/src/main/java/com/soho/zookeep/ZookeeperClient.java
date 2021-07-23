package com.soho.zookeep;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;


import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/*
 * 原生的zookeeper客户端
 */
public class ZookeeperClient implements Watcher 
{
	private static final String host = "10.100.21.201:2181";
	
	private static CountDownLatch countDownLatch = new CountDownLatch(1);
	
	protected static Configuration conf;
	
	static 
	{
		try 
		{
			//ZooKeeper的配置文件.
			conf = new PropertiesConfiguration("zookeeper.properties");
		}catch (ConfigurationException e)
		{
			System.out.println("system exit.");
			System.exit(1);
		}
	}

	@Override
	public void process(WatchedEvent watchedEvent) {
		// TODO Auto-generated method stub
		 countDownLatch.countDown();
	     System.out.println(watchedEvent.getType());
	}
	
	public static void main(String[] args) throws IOException {
		
		String hosts = getZookeeperQuorum();
		
		ZooKeeper zooKeeper = new ZooKeeper(host, 3000, new ZookeeperClient()) ;
		
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			zooKeeper.exists("/test1", true);
			zooKeeper.create("/test1", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		    
		    zooKeeper.exists("/test1", true);
		    zooKeeper.setData("/test1", "123".getBytes(), -1);
		    
		    zooKeeper.exists("/test1", true);
		    zooKeeper.delete("/test1", -1);
		
		
		
		
		} catch (KeeperException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	public static String getZookeeperQuorum()
	{
		StringBuilder sb = new StringBuilder();
		
		String[] zookeeperParamslist = conf.getStringArray("zookeeper.quorum");
		for (String param : zookeeperParamslist) {
			sb.append(param).append(",");
		}

		if(sb.length() > 0){
			sb.deleteCharAt(sb.length() - 1);
		}

		return sb.toString();
	}

}
