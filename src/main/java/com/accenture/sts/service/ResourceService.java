package com.accenture.sts.service;

import java.util.List;
import com.accenture.sts.entity.Employee;

public interface ResourceService {
	
	public int createResource(String empID, Employee employee);

	public List<Object[]> resname();

	public int modifyResource(Employee employee);

}
