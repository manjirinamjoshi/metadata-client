package com.pac.msm.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;

public class MetadataClientImpl implements MetadataClient {
	
	private static final MetadataClient m_thisInstance = new MetadataClientImpl();
	
	private MetadataClientImpl()
	{
		
	}
	
	/**
	 * Method getInstance return reference to MetadataClient <br>
	 * by implementing singleton pattern.
	 * @return MetadataClient
	 */
	public static MetadataClient getInstance()
	{
		return m_thisInstance;
	}

	public Map<String, Object> saveMetadata(JsonRpcHttpClient client, String dataaccountId, String type, String id,
			String name, Map<String, String> mappings) throws MetadataException {
		MetadataClientPojo metadata = new MetadataClientPojo();
		Map<String, String> nameMap = new HashMap<String, String>();
		nameMap.put("en_US", name);
		metadata.setName(nameMap);
		MetadataClientKeyPojo metadataKey = new MetadataClientKeyPojo();
		metadataKey.setDbid(dataaccountId);
		metadataKey.setId(id);
		metadataKey.setType(type);
		metadata.setKey(metadataKey);
		metadata.setParameters(mappings);
		try {
			Map<String, Object> result = null;
			Map o = client.invoke("saveMetadata", new Object[] { null, metadata }, Map.class);
			if(o!=null && o.get("body")!=null) {
				Map body = (Map) o.get("body");
				result = new HashMap<String, Object>();
				result.put("name", ((Map)body.get("name")).get("en_US"));
				result.put("mappings", body.get("parameters"));
			}
			return result;
		} catch (Throwable e) {
			throw new MetadataException(e);
		}
	}
	
	public Map<String, Object> getMetadata(JsonRpcHttpClient client,
			String dataaccountId, String type, String id) throws MetadataException {
		try {
			MetadataClientKeyPojo key = new MetadataClientKeyPojo();
			key.setDbid(dataaccountId);
			key.setType(type);
			key.setId(id);
			Map<String, Object> result = null;
			Map o = client.invoke("getMetadata", new Object[] { null, key }, Map.class);
			if(o!=null && o.get("body")!=null) {
				Map body = (Map) o.get("body");
				result = new HashMap<String, Object>();
				result.put("name", ((Map)body.get("name")).get("en_US"));
				result.put("mappings", body.get("parameters"));
			}
			
			return result;
		} catch (Throwable e) {
			throw new MetadataException(e);
		}
	}
	
	public Map<String, Map<String, Object>> searchByName(JsonRpcHttpClient client, String dataaccountId, String type, String name) throws MetadataException {
		try {
			if(!name.contains("*")) {
				name = name + "*";
			}
			List<MetadataClientPojo> list = client.invoke("searchByName", new Object[] { null, dataaccountId, type, name }, List.class);
			Map<String, Map<String, Object>> result = null;
			if(list!=null && !list.isEmpty()) {
				result = new HashMap<String, Map<String, Object>>();
				for (MetadataClientPojo metadata : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("name", metadata.getName().get("en_US"));
					map.put("mappings", metadata.getParameters());
					result.put(metadata.getKey().getDbid()+"::"+metadata.getKey().getId(), map);
				}
			}
			return result;
		} catch (Throwable e) {
			throw new MetadataException(e);
		}
	}

	@Override
	public void deleteMetadata(JsonRpcHttpClient client,
			String dataaccountId, String type, String id) throws MetadataException {
		try {
			MetadataClientKeyPojo key = new MetadataClientKeyPojo();
			key.setDbid(dataaccountId);
			key.setType(type);
			key.setId(id);
			Map<String, Object> result = null;
			client.invoke("deleteMetadata", new Object[] { null, key }, Map.class);
		} catch (Throwable e) {
			throw new MetadataException(e);
		}
	}

}
