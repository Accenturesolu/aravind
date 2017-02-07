package com.accenture.sts.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Application")
public class Application {
	@Id
	@Column(name = "Application_ID")
	private String application_id;

	@Column(name = "Application_Name")
	private String application_name;

	@Column(name = "Application_Shortname")
	private String application_shortname;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "application", fetch = FetchType.LAZY)
	private Set<Ticket> ticket;

	public Set<Ticket> getTicket() {
		return ticket;
	}

	public void setTicket(Set<Ticket> ticket) {
		this.ticket = ticket;
	}

	public String getApplication_id() {
		return application_id;
	}

	public void setApplication_id(String application_id) {
		this.application_id = application_id;
	}

	public String getApplication_name() {
		return application_name;
	}

	public void setApplication_name(String application_name) {
		this.application_name = application_name;
	}

	public String getApplication_shortname() {
		return application_shortname;
	}

	public void setApplication_shortname(String application_shortname) {
		this.application_shortname = application_shortname;
	}

}
