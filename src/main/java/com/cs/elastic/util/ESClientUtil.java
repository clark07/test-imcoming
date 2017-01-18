package com.cs.elastic.util;

import org.elasticsearch.action.admin.cluster.stats.ClusterStatsRequest;
import org.elasticsearch.action.admin.cluster.stats.ClusterStatsResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetSocketAddress;

public class ESClientUtil {

	private static Client client = null;
	
	private static Object lock = new Object();
	
	public static Client getClient(){
		if(client == null){
			synchronized (lock) {
				Settings settings = Settings.builder().put("client.transport.sniff", true).put("cluster.name", "cs").build();
				client = TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress("10.0.12.26", 9300)));
			}
		}
		
		return client;
	}

	public static void main(String[] args) {
		Client client = getClient();
		ClusterStatsResponse  response = client.admin().cluster().clusterStats(new ClusterStatsRequest()).actionGet();
		System.out.println(response.toString());
	}
	
	
}
