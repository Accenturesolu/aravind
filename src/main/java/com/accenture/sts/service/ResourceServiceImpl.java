package com.accenture.sts.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.accenture.sts.dao.ResourceDAO;
import com.accenture.sts.entity.Employee;

public class ResourceServiceImpl implements ResourceService {
	@Autowired
	private ResourceDAO resourceDAO;

	@Override
	public int createResource(String empID, Employee employee) {

		int status = 0;
		try {
			status = resourceDAO.addResource(empID, employee);
		} catch (Exception exception) {
			System.out.println("Exception e");
		}
		return status;
	}

	@Override
	public List<Object[]> resname() {

		List<Object[]> NameList = new ArrayList<Object[]>();
		try {
			NameList = resourceDAO.resname();
		} catch (Exception exception) {
			System.out.println("Exception e");
		}
		return NameList;
	}

	@Override
	public int modifyResource(Employee employee) {

		int status = 0;

		try {
			status = resourceDAO.modifyResource(employee);
		} catch (Exception exception) {
			System.out.println("Exception e");
		}
		return status;
	}
}
