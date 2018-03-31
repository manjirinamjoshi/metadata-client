package com.pac.msm.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.datastax.driver.core.Metadata;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;



public class MainClass {
	
	
	public static void main(String args[]) {
		try {
			JsonRpcHttpClient client = new JsonRpcHttpClient(new URL("http://localhost:8080/msm/metadata"));
			//List<Metadata> invoke = client.invoke("getMetadataKeys", new Object[] { null, "FACILITY_METADATAKEYS", 100 }, List.class);
			//System.out.println(invoke);
			MetadataClient metadataClient = new MetadataClientImpl();
			metadataClient.saveMetadata(client, "431", "EVENT_METADATA", "F15:F01", "test event F01", null);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}
	
}
