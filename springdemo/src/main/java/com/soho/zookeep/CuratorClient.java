package com.soho.zookeep;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/*
 * 使用curatorframework实现zk client
 */

public class CuratorClient {
	
	private CuratorFramework zkClient = null;
	
	protected static Configuration conf;
	
	private static final String host = "10.100.21.201:2181";
	
	public CuratorFramework getZkClient() {
		return zkClient;
	}

	public void setZkClient(CuratorFramework zkClient) {
		this.zkClient = zkClient;
	}

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
	
	public CuratorClient()
	{
		// retry strategy(重试策略)
				RetryPolicy retryPolicy = new ExponentialBackoffRetry(
						Integer.parseInt(conf.getString("zookeeper.retry.sleep")),
						Integer.parseInt(conf.getString("zookeeper.retry.maxtime")));

				try{
					// crate zookeeper client
					zkClient = CuratorFrameworkFactory.builder()
								.connectString(host)
								.retryPolicy(retryPolicy)
								.sessionTimeoutMs(1000 * Integer.parseInt(conf.getString("zookeeper.session.timeout")))
								.connectionTimeoutMs(1000 * Integer.parseInt(conf.getString("zookeeper.connection.timeout")))
								.build();

					zkClient.start();
					
				}catch(Exception e){
					System.exit(-1);
				}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		CuratorFramework zkClient = null;
		
		CuratorClient client = new CuratorClient();
		
		zkClient = client.getZkClient();
		
		//create a znode
		
		//创建一个初始内容为空的节点
		if( zkClient.checkExists().forPath("/China") == null )
		   zkClient.create().forPath("/China");
		
        //创建一个初始内容不为空的节点
		if( zkClient.checkExists().forPath("/Korea") == null )
		    zkClient.create().forPath("/Korea","jinzhengen".getBytes());
		
        //创建一个初始内容为空的临时节点
		if( zkClient.checkExists().forPath("/America") == null )
		    zkClient.create().withMode(CreateMode.EPHEMERAL).forPath("/America");
		
		
        //创建一个初始内容不为空的临时节点，可以实现递归创建
		if( zkClient.checkExists().forPath("/Japan") == null )
		{
		    zkClient.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                .forPath("/Japan","xiaoriben".getBytes());
		}
		
		/**
         * 获取节点内容
         * */
        byte[] data = zkClient.getData().forPath("/Korea");
        System.out.println(new String(data));
        //传入一个旧的stat变量,来存储服务端返回的最新的节点状态信息
        byte[] data2 = zkClient.getData().storingStatIn(new Stat()).forPath("/Korea");
        System.out.println(new String(data2));

		
		zkClient.delete().forPath("/China");
		
		zkClient.delete().forPath("/Korea");
		
		zkClient.delete().forPath("/Japan");
		

		
	}

}
