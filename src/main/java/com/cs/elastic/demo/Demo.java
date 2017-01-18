package com.cs.elastic.demo;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetSocketAddress;

public class Demo {

	public static void main(String[] args) throws Exception {
		
		Settings settings = Settings.settingsBuilder().put("cluster.name", "cs").build();
		
		TransportClient tc = TransportClient.builder().settings(settings).build();
		
		
		tc.addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress("10.0.12.26", 9300)));
		
		String indexName = "test";
		boolean existsERP = tc.admin().indices().exists(new IndicesExistsRequest(indexName)).actionGet().isExists();
		
		if(!existsERP){
			tc.admin().indices().create(new CreateIndexRequest(indexName)).actionGet();
		}
		
		existsERP = tc.admin().indices().exists(new IndicesExistsRequest(indexName)).actionGet().isExists();
		
		System.out.println(existsERP);
		if(existsERP){
			tc.admin().indices().delete(new DeleteIndexRequest(indexName)).actionGet();
		}
		
		
	}
	
}
