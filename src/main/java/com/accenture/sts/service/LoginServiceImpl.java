package com.accenture.sts.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import com.accenture.sts.dao.LoginDAO;
import com.accenture.sts.entity.Employee;
import com.accenture.sts.entity.Upload_ticket;

public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginDAO loginDAO;

	public Employee searchResource(String emp_Id, String pass) {

		Employee employee = new Employee();
		try {
			employee = loginDAO.searchResource(emp_Id, pass);
		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println("Exception e");
		}
		return employee;
	}

	@Override
	public int changepwd(String empID, Employee employee)  {

		int status = 0;
		try {
			status = loginDAO.changepwd(empID, employee);
		} catch (Exception exception) {
			System.out.println("Exception e");
		}
		return status;
	}
	public List<Upload_ticket> showDate() {

		List<Upload_ticket> showDate = null;
		try {
			showDate = loginDAO.showDate();
		} catch (Exception exception) {
			System.out.println("Exception e");
		}
		return showDate;
	}
	
	public List<Upload_ticket> showCreate() {

		List<Upload_ticket> showCreate = null;
		try {
			showCreate = loginDAO.showCreate();
		} catch (Exception exception) {
			System.out.println("Exception e");
		}
		return showCreate;
	}
}

