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
@Table(name = "Dev_Comment")
public class Dev_comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Comment_ID")
	private int comment_id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Ticket_ID")
	private Ticket ticket;

	@Column(name = "Comment_Desc")
	private String comment_desc;

	@Column(name = "Status")
	private String status;

	@Column(name = "Activity")
	private String activity;

	@Temporal(TemporalType.DATE)
	@Column(name = "Date")
	private Date date;

	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public String getComment_desc() {
		return comment_desc;
	}

	public void setComment_desc(String comment_desc) {
		this.comment_desc = comment_desc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
