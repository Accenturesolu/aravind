package com.accenture.sts.entity;

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

@Entity
@Table(name = "Documentation")
public class Documentation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Documentation_ID")
	private int documentation_id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Ticket_ID")
	private Ticket ticket;

	@Column(name = "Doc_Comment")
	private String doc_comment;

	@Column(name = "Remedy")
	private String remedy;

	@Column(name = "Document_Desc")
	private String document_desc;

	public int getDocumentation_id() {
		return documentation_id;
	}

	public void setDocumentation_id(int documentation_id) {
		this.documentation_id = documentation_id;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public String getDoc_comment() {
		return doc_comment;
	}

	public void setDoc_comment(String doc_comment) {
		this.doc_comment = doc_comment;
	}

	public String getRemedy() {
		return remedy;
	}

	public void setRemedy(String remedy) {
		this.remedy = remedy;
	}

	public String getDocument_desc() {
		return document_desc;
	}

	public void setDocument_desc(String document_desc) {
		this.document_desc = document_desc;
	}

}
