package com.accenture.sts.dao;

import java.util.List;

import com.accenture.sts.entity.Employee;
import com.accenture.sts.entity.Upload_ticket;

public interface LoginDAO {
	
	public Employee searchResource(String emp_Id, String pass);

	public int changepwd(String empID, Employee employee);

	public List<Upload_ticket> showDate();

	public List<Upload_ticket> showCreate();
}
