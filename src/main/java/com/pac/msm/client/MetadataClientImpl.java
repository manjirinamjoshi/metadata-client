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

public class MetadataClientImpl implements MetadataClient {

	//@Override
	public void saveMetadata(JsonRpcHttpClient client, String dataaccountId, String type, String id,
			String name, Map<String, String> mappings) throws PacException {
		Metadata metadata = new Metadata();
		Map<String, String> nameMap = new HashMap<String, String>();
		nameMap.put("en_US", name);
		metadata.setName(nameMap);
		Key metadataKey = new Key();
		metadataKey.setDbid(dataaccountId);
		metadataKey.setId(id);
		metadataKey.setType(type);
		metadata.setKey(metadataKey);
		metadata.setParameters(mappings);
		try {
			client.invoke("saveMetadata", new Object[] { null, metadata });
		} catch (Throwable e) {
			throw new PacException(PacExceptionTypes.APPLICATION, PacErrorCodes.UNKNOWN, e.getMessage(), e);
		}
	}
	
	//@Override
	public Map<String, Object> getMetadata(JsonRpcHttpClient client,
			String dataaccountId, String type, String id) throws PacException {
		try {
			Key key = new Key();
			key.setDbid(dataaccountId);
			key.setType(type);
			key.setId(id);
			Metadata metadata = client.invoke("getMetadata", new Object[] { null, key }, Metadata.class);
			Map<String, Object> result = null;
			if(metadata!=null) {
				result = new HashMap<String, Object>();
				result.put("name", metadata.getName().get("en_US"));
				result.put("mappings", metadata.getParameters());
			}
			return result;
		} catch (Throwable e) {
			throw new PacException(PacExceptionTypes.APPLICATION, PacErrorCodes.UNKNOWN, e.getMessage(), e);
		}
	}
	
	public Map<String, Map<String, Object>> searchByName(JsonRpcHttpClient client, String type, String name) throws PacException {
		try {
			List<Metadata> list = client.invoke("searchByName", new Object[] { null, type, name }, List.class);
			Map<String, Map<String, Object>> result = null;
			if(list!=null && !list.isEmpty()) {
				result = new HashMap<String, Map<String, Object>>();
				for (Metadata metadata : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("name", metadata.getName().get("en_US"));
					map.put("mappings", metadata.getParameters());
					result.put(metadata.getKey().getDbid()+"::"+metadata.getKey().getId(), map);
				}
			}
			return result;
		} catch (Throwable e) {
			throw new PacException(PacExceptionTypes.APPLICATION, PacErrorCodes.UNKNOWN, e.getMessage(), e);
		}
	}

}
