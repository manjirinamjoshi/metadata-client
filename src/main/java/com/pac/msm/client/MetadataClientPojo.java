package com.pac.msm.client;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MetadataClientPojo {
	@JsonProperty("key")
	private MetadataClientKeyPojo key;
	
	private Map<String, String> name;
	
	private Map<String, String> parameters;

	public MetadataClientKeyPojo getKey() {
		return key;
	}

	public void setKey(MetadataClientKeyPojo key) {
		this.key = key;
	}

	public Map<String, String> getName() {
		return name;
	}

	public void setName(Map<String, String> name) {
		this.name = name;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}
}
