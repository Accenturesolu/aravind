package com.accenture.sts.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.accenture.sts.entity.Employee;
import com.accenture.sts.entity.Upload_ticket;

public class LoginDAOImpl implements LoginDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public Employee searchResource(String emp_Id, String pass) {
		
		Employee employee = new Employee();
		sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("employee_id", emp_Id));
		criteria.add(Restrictions.eq("password", pass));
		List<Employee> list = criteria.list();

		int n = list.size();
		System.out.println("SHIFT COUNT=" + n);

		if (!list.isEmpty()) {
			employee.setEmployee_id(list.get(0).getEmployee_id());
			employee.setEmployee_name(list.get(0).getEmployee_name());
			employee.setIs_active(list.get(0).getIs_active());
			employee.setIs_admin(list.get(0).getIs_admin());
			employee.setPassword(list.get(0).getPassword());
			employee.setReport(list.get(0).isReport());
		}
		session.getTransaction().commit();
		session.close();
		return employee;
	}

	@SuppressWarnings("deprecation")
	public int changepwd(String empID, Employee employee){
		
		SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionfactory.openSession();
		session.beginTransaction();

		Employee emp = (Employee) session.load(Employee.class, empID);
		emp.setPassword(employee.getPassword());
		session.update(emp);
		int status = 1;
		session.getTransaction().commit();
		session.close();
		return status;
	}
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Upload_ticket> showDate() {
		
		sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Upload_ticket upticket=new Upload_ticket();
		
		String hql="select max(createdOn) from upload_ticket";
		Query query = session.createSQLQuery(hql);
		/*Criteria criteria = session.createCriteria(Upload_ticket.class, "upload_ticket");

		ProjectionList projList = Projections.projectionList();

		projList.add(Projections.max("upload_ticket.createdOn"));
		criteria.setProjection(projList);*/
		List<Upload_ticket> list = query.list();
		
		System.out.println("show the date");
		System.out.println(list);
					
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Upload_ticket> showCreate() {
		
		sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
	Upload_ticket upticket=new Upload_ticket();
		
		String hql="select max(createdBy) from upload_ticket where createdOn=(select max(createdOn) from upload_ticket)";
		//String hql="select createdBy from upload_ticket where createdBy=(select  min(createdBy) from upload_ticket)";
		
		Query query = session.createSQLQuery(hql);
		/*Criteria criteria = session.createCriteria(Upload_ticket.class, "upload_ticket");

		ProjectionList projList = Projections.projectionList();

		projList.add(Projections.max("upload_ticket.createdOn"));
		criteria.setProjection(projList);*/
		List<Upload_ticket> list = query.list();
		
		System.out.println("show the creator");
		System.out.println(list);
					
		session.getTransaction().commit();
		session.close();
		return list;
	}


}
