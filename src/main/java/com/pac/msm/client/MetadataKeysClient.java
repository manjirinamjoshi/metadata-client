package com.pac.msm.client;

import java.util.Map;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.pac.lib.core.common.PacException;

public interface MetadataKeysClient {
	
	public Map<String, String> getMetadataKeys(JsonRpcHttpClient client, String type) throws PacException;

	public void saveMetadataKey(JsonRpcHttpClient client, String type, String key, String value) throws PacException;
	
}
