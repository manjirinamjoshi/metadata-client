package com.pac.msm.client;

import java.util.Map;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;

public interface MetadataClient {

	public Map<String, Object> saveMetadata(JsonRpcHttpClient client, String dataaccountId, String type, String id,
			String name, Map<String, String> mappings) throws MetadataException;

	public Map<String, Object> getMetadata(JsonRpcHttpClient client,
			String dataaccountId, String type, String id) throws MetadataException;
	
	public Map<String, Map<String, Object>> searchByName(
			JsonRpcHttpClient client, String dataaccountId, String type, String name)
			throws MetadataException;

	public void deleteMetadata(JsonRpcHttpClient client,
			String dataaccountId, String type, String id) throws MetadataException;
}
