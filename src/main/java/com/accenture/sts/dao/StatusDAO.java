package com.accenture.sts.dao;

import java.util.List;
import com.accenture.sts.entity.Employee;
import com.accenture.sts.entity.Ticket;

public interface StatusDAO {

	public List<Employee> showAllResource();

	public List<Object[]> showAllReport(Employee employee, Ticket ticket);

	public List<Object[]> showticketstatus(String ticketid, String resource);

	public List<Object[]> showdaystatus(String ticketid, String resource, String days);

	public List<Object[]> resname();

	public List<Object[]> applist();

	public List<Object[]> showAllResource(String resname, String appname, String activity, String curname,
			String release, String startDateFrom, String startDateTo, String endDateFrom, String endDateTo);

	public List<Object[]> getDailystatus();

	public List<Object[]> getInprogressTicket();

	public List<Object[]> getNotreportedList();

	public List<Object[]> showclarify();

	public int clarify(String ticketID, String supervisorcomment);

	public int responseclarification(String ticketID, String employee_response, String supervisorComment);

	public List<Object[]> assignedClarification(String empID);

	public int deleteclarification(String ticketID, String supervisor_comment);

	public List<Object[]> toclarify(String ticketID);

}
