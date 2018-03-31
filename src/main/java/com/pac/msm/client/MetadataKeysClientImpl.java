package com.pac.msm.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.pac.lib.core.common.PacErrorCodes;
import com.pac.lib.core.common.PacException;
import com.pac.lib.core.common.PacExceptionTypes;
import com.pac.msm.component.domain.Key;
import com.pac.msm.component.domain.Metadata;

public class MetadataKeysClientImpl implements MetadataKeysClient {

	@Override
	public Map<String, String> getMetadataKeys(JsonRpcHttpClient client, String type) throws PacException {
		try {
			List<Metadata> list = client.invoke("getMetadataKeys", new Object[] { null, type, 100 }, List.class);
			Map<String, String> result = null;
			if(list!=null && !list.isEmpty()) {
				result = new HashMap<String, String>();
				for (Metadata metadata : list) {
					result.put(metadata.getKey().getId(), metadata.getName().get("en_US"));
				}
			}
			return result;
		} catch (Throwable e) {
			throw new PacException(PacExceptionTypes.APPLICATION, PacErrorCodes.UNKNOWN, e.getMessage(), e);
		}
	}

	@Override
	public void saveMetadataKey(JsonRpcHttpClient client, String type, String key, String name) throws PacException {
		Metadata metadata = new Metadata();
		Map<String, String> nameMap = new HashMap<String, String>();
		nameMap.put("en_US", name);
		metadata.setName(nameMap);
		Key metadataKey = new Key();
		metadataKey.setId(key);
		metadataKey.setType(type);
		try {
			client.invoke("saveMetadataKey", new Object[] { null, metadata });
		} catch (Throwable e) {
			throw new PacException(PacExceptionTypes.APPLICATION, PacErrorCodes.UNKNOWN, e.getMessage(), e);
		}
	}
	
	

}
