package com.pac.msm.client;

import java.util.Map;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.pac.lib.core.common.PacException;

public interface MetadataClient {

	public void saveMetadata(JsonRpcHttpClient client, String dataaccountId, String type, String id,
			String name, Map<String, String> mappings) throws PacException;

	public Map<String, Object> getMetadata(JsonRpcHttpClient client,
			String dataaccountId, String type, String id) throws PacException;
	
	public Map<String, Map<String, Object>> searchByName(JsonRpcHttpClient client, String type, String name) throws PacException;

}
