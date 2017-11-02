package com.custTODO.JDO;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class TodoListJDO {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String adminEmail;
	
	@Persistent
	private String CustKey;
	
	@Persistent
	private String ListId;

	@Persistent
	private String task;

	@Persistent
	private boolean checked;

	@Persistent
	private String deleted;

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getCustKey() {
		return CustKey;
	}

	public void setCustKey(String CustKey) {
		this.CustKey = CustKey;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean listChecked) {
		this.checked = listChecked;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getListId() {
		return ListId;
	}

	public void setListId(String ListId) {
		this.ListId = ListId;
	}



	
}