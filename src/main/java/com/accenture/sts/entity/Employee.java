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
@Table(name = "Employee_details")
public class Employee {
	@Id
	@Column(name = "Employee_ID")
	private String employee_id;

	@Column(name = "Employee_Name")
	private String employee_name;

	@Column(name = "Designation")
	private String designation;

	@Column(name = "Password")
	private String password;

	@Column(name = "Email")
	private String email;

	@Column(name = "is_admin")
	private String is_admin;

	@Column(name = "is_active", columnDefinition = "varchar(255) default 'Yes'")
	private String is_active;

	@Column(name = "Report", columnDefinition = "int default 0")
	private boolean report;

	@Column(name = "is_include", columnDefinition = "varchar(255) default 'Yes'")
	private String is_include;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee", fetch = FetchType.LAZY)
	private Set<Ticket> ticket;

	public Set<Ticket> getTicket() {
		return ticket;
	}

	public void setTicket(Set<Ticket> ticket) {
		this.ticket = ticket;
	}

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIs_admin() {
		return is_admin;
	}

	public void setIs_admin(String is_admin) {
		this.is_admin = is_admin;
	}

	public String getIs_active() {
		return is_active;
	}

	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}

	public boolean isReport() {
		return report;
	}

	public void setReport(boolean report) {
		this.report = report;
	}

	public String getIs_include() {
		return is_include;
	}

	public void setIs_include(String is_include) {
		this.is_include = is_include;
	}

}
