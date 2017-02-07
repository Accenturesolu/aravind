package com.accenture.sts.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import com.accenture.sts.entity.Employee;


public class ResourceDAOImpl implements ResourceDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings({ "deprecation", "unchecked" })
	public int addResource(String empID, Employee employee) {

		sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Employee emp = new Employee();
		emp.setEmployee_name(employee.getEmployee_name());
		emp.setEmployee_id(employee.getEmployee_id());
		emp.setIs_admin(employee.getIs_admin());
		emp.setEmail(employee.getEmail());
		emp.setPassword("pass");
		emp.setIs_active("Yes");
		emp.setIs_include("Yes");
		int status = -1;
		try {
			Criteria cr = session.createCriteria(Employee.class);
			cr.add(Restrictions.eq("employee_id", employee.getEmployee_id()));
			List<Employee> results = cr.list();

			if (results.size() == 0) {
				System.out.println("save");
				session.save(emp);
				status = 1;
			} else {
				System.out.println("duplicate");
				status = -1;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		session.getTransaction().commit();
		
		System.out.println("save successfully");
		return status;
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	public int modifyResource(Employee employee) {
		SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
		System.out.println("resDAOImpl");
		Session session = sessionfactory.getCurrentSession();
		session.beginTransaction();
		int status = 0;
		try {
			Criteria criteria = session.createCriteria(Employee.class);
			criteria.add(Restrictions.eq("employee_name", employee.getEmployee_name()));
			criteria.setProjection(Projections.property("employee_id"));
			List emplist = criteria.list();

			String empid = (String) emplist.get(0);

			Employee emp = (Employee) session.load(Employee.class, empid);
			emp.setIs_active(employee.getIs_active());
			emp.setIs_include(employee.getIs_include());
			emp.setIs_admin(employee.getIs_admin());
			emp.setReport(employee.isReport());
			session.update(emp);
			status = 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		session.getTransaction().commit();
		
		System.out.println("saved successfully");
		return status;

	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Object[]> resname() {
		SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
		System.out.println("resDAOImplname...name");
		Session session = sessionfactory.getCurrentSession();
		session.beginTransaction();
		List<Object[]> result = null;
		try {
			Criteria cr = session.createCriteria(Employee.class);
			cr.setProjection(Projections.property("employee_name"));
			 cr.setProjection((Projections.groupProperty("employee_name")));

			result = cr.list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		session.getTransaction().commit();
		
		return result;

	}

}
