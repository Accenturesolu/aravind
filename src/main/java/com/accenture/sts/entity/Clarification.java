package com.accenture.sts.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "clarification")
public class Clarification {
	@Id
	@Column(name = "Clarification_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int clarification_id;

	@Column(name = "Clarification_Desc")
	private String clarification_desc;

	@Column(name = "Employee_Res")
	private String employee_res;

	@Column(name = "Flag", nullable = false, columnDefinition = "int default 0")
	private int flag;

	@Temporal(TemporalType.DATE)
	@Column(name = "Date")
	private Date date;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Ticket_ID")
	private Ticket ticket;

	public int getClarification_id() {
		return clarification_id;
	}

	public void setClarification_id(int clarification_id) {
		this.clarification_id = clarification_id;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public String getClarification_desc() {
		return clarification_desc;
	}

	public void setClarification_desc(String clarification_desc) {
		this.clarification_desc = clarification_desc;
	}

	public String getEmployee_res() {
		return employee_res;
	}

	public void setEmployee_res(String employee_res) {
		this.employee_res = employee_res;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
