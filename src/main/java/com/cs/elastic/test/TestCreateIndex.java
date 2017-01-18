package com.cs.elastic.test;

import com.cs.elastic.util.ESClientUtil;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCreateIndex {
	
	private static Client client = null;
	private static String indexName = "testIndex".toLowerCase();
	
	@BeforeClass
	public static void before(){
		client = ESClientUtil.getClient();
	}
	

	@Test
	public void testCreateIndex() {
		IndicesAdminClient indices = client.admin().indices();
		CreateIndexResponse createIndexResponse = indices.create(new CreateIndexRequest(indexName)).actionGet();
		if(createIndexResponse.isAcknowledged()){
			System.out.println(String.format("index:%s create succ!", indexName));
		}
		
		DeleteIndexResponse deleteIndexResponse = indices.delete(new DeleteIndexRequest(indexName)).actionGet();
		if(deleteIndexResponse.isAcknowledged()){
			System.out.println(String.format("index:%s delete succ!", indexName));
		}
	}
	
	
	@AfterClass
	public static void after(){
		if(client != null){
			client.close();
		}
	}

}
