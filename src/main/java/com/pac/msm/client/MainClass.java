package com.pac.msm.client;

import java.net.URL;
import java.util.List;
import java.util.Map;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;

public class MainClass {

	public static void main(String[] args) {
		
		MetadataClient metadataClient = MetadataClientImpl.getInstance();
		
		try {
			JsonRpcHttpClient client = new JsonRpcHttpClient(new URL("http://devjam-manjiri.us-west-2.elasticbeanstalk.com/msm/metadata"));
			//Map<String, Object> metadata = metadataClient.getMetadata(client, "112", "EVENT_METADATA", "F15:F03");
			List<Map<String, Object>> allMetadatas = metadataClient.getAllMetadatas(client, "431", "SEASON_METADATA");
			System.out.println(allMetadatas);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
