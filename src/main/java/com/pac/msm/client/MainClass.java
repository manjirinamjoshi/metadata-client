package com.pac.msm.client;

import java.net.URL;
import java.util.Map;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;

public class MainClass {

	public static void main(String[] args) {
		
		MetadataClient metadataClient = MetadataClientImpl.getInstance();
		
		try {
			JsonRpcHttpClient client = new JsonRpcHttpClient(new URL("http://localhost:8090/msm/metadata"));
			Map<String, Object> metadata = metadataClient.getMetadata(client, "112", "EVENT_METADATA", "F15:F03");
			System.out.println(metadata);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
