package com.pac.msm.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;

public class MetadataKeysClientImpl implements MetadataKeysClient {
	
	private static final MetadataKeysClient m_thisInstance = new MetadataKeysClientImpl();
	
	private MetadataKeysClientImpl()
	{
		
	}
	
	/**
	 * Method getInstance return reference to MetadataKeysClient <br>
	 * by implementing singleton pattern.
	 * @return MetadataKeysClient
	 */
	public static MetadataKeysClient getInstance()
	{
		return m_thisInstance;
	}

	public Map<String, String> getMetadataKeys(JsonRpcHttpClient client, String type) throws MetadataException {
		try {
			List<MetadataClientPojo> list = client.invoke("getMetadataKeys", new Object[] { null, type, 100 }, List.class);
			Map<String, String> result = null;
			if(list!=null && !list.isEmpty()) {
				result = new HashMap<String, String>();
				for (MetadataClientPojo metadata : list) {
					result.put(metadata.getKey().getId(), metadata.getName().get("en_US"));
				}
			}
			return result;
		} catch (Throwable e) {
			throw new MetadataException(e);
		}
	}

	public void saveMetadataKey(JsonRpcHttpClient client, String type, String key, String name) throws MetadataException {
		MetadataClientPojo metadata = new MetadataClientPojo();
		Map<String, String> nameMap = new HashMap<String, String>();
		nameMap.put("en_US", name);
		metadata.setName(nameMap);
		MetadataClientKeyPojo metadataKey = new MetadataClientKeyPojo();
		metadataKey.setId(key);
		metadataKey.setType(type);
		try {
			client.invoke("saveMetadataKey", new Object[] { null, metadata });
		} catch (Throwable e) {
			throw new MetadataException(e);
		}
	}
	
	

}
