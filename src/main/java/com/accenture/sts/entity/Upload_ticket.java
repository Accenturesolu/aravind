package com.accenture.sts.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Upload_Ticket")
public class Upload_ticket {
	@Id
	@Column(name = "Ticket_ID")
	private String ticket_id;

	@Column(name = "Ticket_Desc")
	private String ticket_desc;

	@Column(name = "Application_Name")
	private String application_name;

	@Column(name = "Flag", nullable = false, columnDefinition = "int default 0")
	private int flag;
	
	

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(String ticket_id) {
		this.ticket_id = ticket_id;
	}

	public String getTicket_desc() {
		return ticket_desc;
	}

	public void setTicket_desc(String ticket_desc) {
		this.ticket_desc = ticket_desc;
	}

	public String getApplication_name() {
		return application_name;
	}

	public void setApplication_name(String application_name) {
		this.application_name = application_name;
	}
	
	@Column(name = "CreatedBy")
	private String createdby;
	
	@Column(name = "CreatedOn")
	private String createdon;

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getCreatedon() {
		return createdon;
	}

	public void setCreatedon(String createdon) {
		this.createdon = createdon;
	}

	public String toString(){
		return application_name+" "+ticket_id+" "+ticket_desc;
		
	}



}
