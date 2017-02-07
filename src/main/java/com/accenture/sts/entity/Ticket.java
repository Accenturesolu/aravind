package com.accenture.sts.entity;

import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Ticket_Details")
public class Ticket {
	@Id
	@Column(name = "Ticket_ID")
	private String ticket_id;

	@Column(name = "Ticket_Desc")
	private String ticket_desc;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "Employee_ID")
	private Employee employee;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "Application_ID")
	private Application application;

	@Column(name = "Ticket_type")
	private String ticket_type;

	@Column(name = "Priority", nullable = false, columnDefinition = "int default 0")
	private int priority;

	@Column(name = "Worked_on_today")
	private String worked_on_today;

	@Column(name = "Created_by")
	private String created_by;

	@Temporal(TemporalType.DATE)
	@Column(name = "Created_On")
	private Date created_on;
	
	@Column(name = "CreatedBy")
	private String createdby;

	@Temporal(TemporalType.DATE)
	@Column(name = "CreatedOn")
	private Date createdon;

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public Date getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Updated_On")
	private Date updated_on;

	@Temporal(TemporalType.DATE)
	@Column(name = "Start_date")
	private Date start_date;

	@Temporal(TemporalType.DATE)
	@Column(name = "End_date")
	private Date end_date;
	
	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	@Column(name = "Release_ticket", nullable = false, columnDefinition = "varchar(255) default 'No'")
	private String release_ticket;

	@Column(name = "Flag", nullable = false, columnDefinition = "int default 0")
	private int flag;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ticket", fetch = FetchType.LAZY)
	private Set<Dev_comment> dev_comment;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ticket", fetch = FetchType.LAZY)
	private Set<Documentation> documentation;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ticket", fetch = FetchType.LAZY)
	private Set<Tester_comment> tester_comment;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ticket", fetch = FetchType.LAZY)
	private Set<Clarification> clarification;

	public Set<Clarification> getClarification() {
		return clarification;
	}

	public void setClarification(Set<Clarification> clarification) {
		this.clarification = clarification;
	}

	public Set<Documentation> getDocumentation() {
		return documentation;
	}

	public void setDocumentation(Set<Documentation> documentation) {
		this.documentation = documentation;
	}

	public Set<Dev_comment> getDev_comment() {
		return dev_comment;
	}

	public void setDev_comment(Set<Dev_comment> dev_comment) {
		this.dev_comment = dev_comment;
	}

	public Set<Tester_comment> getTester_comment() {
		return tester_comment;
	}

	public void setTester_comment(Set<Tester_comment> tester_comment) {
		this.tester_comment = tester_comment;
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public String getTicket_type() {
		return ticket_type;
	}

	public void setTicket_type(String ticket_type) {
		this.ticket_type = ticket_type;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getWorked_on_today() {
		return worked_on_today;
	}

	public void setWorked_on_today(String worked_on_today) {
		this.worked_on_today = worked_on_today;
	}

	public Date getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(Date updated_on) {
		this.updated_on = updated_on;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getRelease_ticket() {
		return release_ticket;
	}

	public void setRelease_ticket(String release_ticket) {
		this.release_ticket = release_ticket;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

}
