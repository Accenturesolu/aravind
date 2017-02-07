package com.accenture.sts.service;

import java.sql.SQLException;
import java.util.List;
import com.accenture.sts.entity.Employee;
import com.accenture.sts.entity.Ticket;

public interface StatusService {

	public List<Employee> getAllResource();

	public List<Object[]> getAllReport(Employee employee, Ticket ticket);

	public List<Object[]> getTicketstatus(String ticketid, String resource);

	public List<Object[]> getDaystatus(String ticketid, String resource, String days);

	public List<Object[]> namelist();

	public List<Object[]> applist();

	public List<Object[]> getAllResource(String resname, String appname, String activity, String curname,
			String release, String startDateFrom, String startDateTo, String endDateFrom, String endDateTo);

	public List<Object[]> getDailystatus() throws ClassNotFoundException, SQLException;

	public List<Object[]> getInprogressTicket() throws ClassNotFoundException, SQLException;

	public List<Object[]> getNotreportedList() throws ClassNotFoundException, SQLException;

	public List<Object[]> toClarify(String ticketID);

	public int calrify(String ticketID, String supervisorcomment);

	public int responseclarification(String ticketID, String employee_response, String supervisorComment);

	public List<Object[]> assignedClarification(String empID);

	public int deleteclarification(String ticketID, String supervisor_comment);

	public List<Object[]> allclarify();

}
