package com.custTODO.JDO;


import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class LoginJDO {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private String custName;

	@Persistent
	private String custEmail;

	@Persistent
	private String custPassword;

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getCustPassword() {
		return custPassword;
	}

	public void setCustPassword(String custPassword) {
		this.custPassword = custPassword;
	}
}
