package com.accenture.sts.dao;


import java.util.List;
import com.accenture.sts.entity.Employee;

public interface ResourceDAO {
	
	public int addResource(String empID, Employee employee);

	public List<Object[]> resname();

	public int modifyResource(Employee employee);

}
