package com.pac.msm.client;

import java.io.Serializable;

public class MetadataClientKeyPojo implements Serializable {
	
	private String dbid;
	
	private String type;
	
	private String subtype;
	
	private String id;

	public String getDbid() {
		return dbid;
	}

	public void setDbid(String dbid) {
		this.dbid = dbid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}
