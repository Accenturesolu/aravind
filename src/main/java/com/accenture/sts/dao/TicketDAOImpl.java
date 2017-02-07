package com.accenture.sts.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import com.accenture.sts.entity.Application;
import com.accenture.sts.entity.Dev_comment;
import com.accenture.sts.entity.Documentation;
import com.accenture.sts.entity.Employee;
import com.accenture.sts.entity.Tester_comment;
import com.accenture.sts.entity.Ticket;
import com.accenture.sts.entity.Upload_ticket;

public class TicketDAOImpl implements TicketDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Application> showAllApp() {

		sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(Application.class);
		criteria.addOrder(Order.asc("application_name"));
		criteria.setProjection(Projections.property("application_name"));
		List<Application> app = criteria.list();

		session.getTransaction().commit();

		return app;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public boolean addticket(String empID, String ticketid, String ticket_desc, Employee employee, Application appl) {

		String app_name = appl.getApplication_name();
		sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(Application.class);
		criteria.add(Restrictions.eq("application_name", app_name));
		List<Application> list2 = criteria.list();

		Criteria crit = session.createCriteria(Employee.class);
		crit.add(Restrictions.eq("employee_id", empID));
		List<Employee> list = crit.list();

		Ticket ticket = new Ticket();
		ticket.setTicket_id(ticketid);
		ticket.setTicket_desc(ticket_desc);
		ticket.setApplication(list2.get(0));
		ticket.setEmployee(list.get(0));
		ticket.setRelease_ticket("No");
		session.save(ticket);

		session.getTransaction().commit();

		return true;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Object[]> showDeleteTickets() {

		List<Object[]> TicketList = null;
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = sessionFactory.getCurrentSession();
			
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Ticket.class, "Ticket");
			criteria.setFetchMode("Employee", FetchMode.JOIN);
			criteria.createAlias("application","application", JoinType.LEFT_OUTER_JOIN);

			ProjectionList projList = Projections.projectionList();

			projList.add(Projections.property("Ticket.ticket_id"));
			projList.add(Projections.property("Ticket.ticket_desc"));
			projList.add(Projections.property("application.application_name"));

			criteria.setProjection(projList);
			
			criteria.add(Subqueries.propertyNotIn("ticket_id", DetachedCriteria.forClass(Dev_comment.class)
					 .setProjection(Property.forName("ticket")).add(Restrictions.eq("status", "Closed"))));
			TicketList = criteria.list();

			session.getTransaction().commit();
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return TicketList;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public void deleteTicket(String ticket_id) {

		sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Ticket ticket = new Ticket();

		Criteria criteria = session.createCriteria(Ticket.class);
		criteria.add(Restrictions.eq("ticket_id", ticket_id));

		List<Ticket> list = criteria.list();
		ticket = list.get(0);

		System.out.println("after ticket_id");
		session.delete(ticket);

		System.out.println("after tic del");
		session.getTransaction().commit();

	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Object[]> showclosedtickets(String empID) {
		SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionfactory.openSession();
		session.beginTransaction();

		Criteria cr = session.createCriteria(Ticket.class, "Ticket");
		cr.setFetchMode("dev_comment", FetchMode.JOIN);
		cr.createAlias("dev_comment", "dev_comment");
		cr.setFetchMode("documentation", FetchMode.JOIN);
		cr.createAlias("documentation", "documentation");

		cr.add(Restrictions.eq("dev_comment.status", "closed"));// condition for
																// dev_status
		cr.add(Restrictions.eq("documentation.remedy", "close"));// condition
																	// for
																	// documentation
		cr.add(Restrictions.eq("employee.employee_id", empID));
		
		ProjectionList projList = Projections.projectionList();
	/*	projList.add(Projections.property("Ticket.ticket_id"));*/ projList.add(Projections.distinct(Projections.property("Ticket.ticket_id"))); 
		projList.add(Projections.property("Ticket.ticket_type"));
		projList.add(Projections.property("Ticket.ticket_desc"));
		projList.add(Projections.property("Ticket.application"));
	/*	projList.add(Projections.property("Ticket.priority"));
		projList.add(Projections.property("dev_comment.activity"));*/
		projList.add(Projections.property("dev_comment.status"));
	/*	projList.add(Projections.property("dev_comment.comment_desc"));*/
		projList.add(Projections.property("Ticket.start_date"));
		projList.add(Projections.property("Ticket.end_date"));
		projList.add(Projections.property("documentation.remedy"));
    	/*projList.add(Projections.property("documentation.document_desc"));*/
		projList.add(Projections.property("documentation.doc_comment"));

		cr.setProjection(projList);


		List<Object[]> closedtickets = cr.list();
		session.getTransaction().commit();
		session.close();
		return closedtickets;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public int updateDocument(String Ticketid, Documentation document, Ticket ticket1) {

		int status = 0;
		sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(Ticket.class);
		criteria.add(Restrictions.eq("ticket_id", Ticketid));
		List<Ticket> tick = criteria.list();

		Criteria crit = session.createCriteria(Documentation.class);
		crit.createAlias("ticket", "ticket");
		crit.add(Restrictions.eq("ticket.ticket_id", Ticketid));
		List<Documentation> doc = crit.list();

		int id = doc.get(0).getDocumentation_id();

		Documentation d = (Documentation) session.load(Documentation.class, id);
		d.setTicket(tick.get(0));
		d.setRemedy(document.getRemedy());
		d.setDocument_desc(document.getDocument_desc());
		d.setDoc_comment(document.getDoc_comment());
		session.update(d);
		status = 1;

		session.getTransaction().commit();

		return status;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Object[]> ticketList(String emp_Id) {

		sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		// For existing ticket
		// FIXME
		String hql = "select "
				+ "this_.ticket_id, "
				+ "this_.Ticket_type, "
				+ "this_.ticket_desc, "
				+ "app.Application_Name, "
				+ "this_.Priority, "
				+ "inner_.activity, "
				+ "inner_.status, "
				+ "inner_.comment_desc, "
				+ "test_.Tester, "
				+ "this_.Start_date "
				+ "from "
				+ "Ticket_Details this_ " 
				+ "left outer join "
				+ "	( "
				+ "			select "
				+ "				a.ticket_id, "
				+ "				status, "
				+ "				activity, "
				+ "				Comment_Desc " 
				+ "			from "
				+ "				Dev_Comment a " 
				+ "			inner join "
				+ "				( "
				+ "						select "
				+ "							ticket_id, "
				+ "							max(date) as date "        
				+ "						from "
				+ "							Dev_Comment "       
				+ "						group by "
				+ "							ticket_id " 
				+ "				) b "
				+ "					on a.ticket_id=b.ticket_id " 
				+ "					and a.date = b.date "
				+ "	) inner_ "
				+ "		on inner_.ticket_id=this_.ticket_id " 
				+ "	join "
				+ "		application app " 
				+ "		on this_.Application_ID=app.Application_ID " 
				+ "	left outer join "
				+ "		( "
				+ "				select "
				+ "					t.ticket_id, "
				+ "					Tester "
				+ "				from "
				+ "					tester_comment t" 
				+ "				inner join "
				+ "					 ( "
				+ "							select "
				+ "							ticket_id, "
				+ "							max(date) as date "        
				+ "						from "
				+ "							Dev_Comment "       
				+ "						group by "
				+ "							ticket_id " 
				+ "				) c "
				+ "					on t.ticket_id=c.ticket_id " 
				+ "					and t.date = c.date "
				+ "		) test_ "
				+ "			on test_.Ticket_ID=this_.Ticket_ID " 
				+ "		where "
				+ "			( "
				+ "					coalesce(inner_.status,'') = '' " 
				+ "					or inner_.status<>'Closed' "
				+ "					or coalesce(this_.Start_date, '')='' "
				+ "			) "
				+ "			and this_.Employee_ID=?";

		Query query = session.createSQLQuery(hql);
		query.setParameter(0, emp_Id);
		List<Object[]> list = query.list();

		session.getTransaction().commit();

		return list;
	}

	@SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
	@Override
	public List<Object[]> showClosedTickets(String emp_Id) {

		List results = null;
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();

			Criteria criteria = session.createCriteria(Ticket.class, "ticket");
			criteria.setFetchMode("dev_comment", FetchMode.JOIN);
			criteria.setFetchMode("documentation", FetchMode.JOIN);
			criteria.setFetchMode("employee", FetchMode.JOIN);
			criteria.setFetchMode("Application", FetchMode.JOIN);
			criteria.createAlias("dev_comment", "dev_comment");
			criteria.createAlias("documentation", "documentation");
			criteria.createAlias("employee", "employee");

			ProjectionList projList = Projections.projectionList();
			projList.add(Projections.property("ticket.ticket_id"));
			projList.add(Projections.property("ticket.ticket_type"));
			projList.add(Projections.property("ticket.ticket_desc"));
			projList.add(Projections.property("ticket.application"));
			projList.add(Projections.property("dev_comment.status"));
			projList.add(Projections.property("dev_comment.comment_desc"));
			projList.add(Projections.property("documentation.remedy"));
			projList.add(Projections.property("documentation.document_desc"));
			criteria.setProjection(projList);

			criteria.add(Restrictions.eq("employee.employee_id", emp_Id));
			criteria.add(Restrictions.eq("dev_comment.status", "closed"));
			Criterion c1 = Restrictions.ne("documentation.remedy", "close");
			Criterion c2 = Restrictions.isNull("documentation.remedy");
			criteria.add(Restrictions.or(c1, c2));

			results = criteria.list();

			session.getTransaction().commit();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return results;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public int assignTicket(Employee emp, Ticket ticket, String ticket_id, String application_name) {

		SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionfactory.getCurrentSession();
		session.beginTransaction();
		Ticket tick = new Ticket();
		Upload_ticket upticket = new Upload_ticket();

		Criteria crit = session.createCriteria(Employee.class);
		crit.add(Restrictions.eq("employee_name", emp.getEmployee_name()));
		List<Employee> empid = crit.list();
		Criteria crite = session.createCriteria(Application.class);
		//crite.add(Restrictions.eq("application_name", application_name));
		List<Application> app_id = crite.list();

		int status = 0;

		if (status == 0) {
			System.out.println("status:" + status);
			tick.setTicket_id(ticket.getTicket_id());
			tick.setTicket_desc(ticket.getTicket_desc());
			tick.setApplication(app_id.get(0));
			tick.setEmployee(empid.get(0));
			tick.setFlag(1);
			tick.setRelease_ticket("No");

			session.saveOrUpdate(tick);

			upticket.setTicket_id(ticket.getTicket_id());
			upticket.setFlag(1);

			session.update(upticket);

			status = 1;
		} else {
			System.out.println("not updated");
		}

		session.getTransaction().commit();


		return status;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Object[]> showToassign() {

		SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionfactory.getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Upload_ticket.class, "upload_ticket");

		ProjectionList projList = Projections.projectionList();

		projList.add(Projections.property("upload_ticket.ticket_id"));
		projList.add(Projections.property("upload_ticket.ticket_desc"));
		projList.add(Projections.property("upload_ticket.application_name"));

		criteria.setProjection(projList);
		criteria.add(Restrictions.eq("upload_ticket.flag", 0));

		List<Object[]> List = criteria.list();

		session.getTransaction().commit();

		return List;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Employee> showEmployee() {

		SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionfactory.getCurrentSession();
		session.beginTransaction();

		Criteria crit = session.createCriteria(Employee.class);
		crit.setProjection(Projections.property("employee_name"));
		 crit.setProjection((Projections.groupProperty("employee_name")));

		List<Employee> empResults = crit.list();

		session.getTransaction().commit();


		return empResults;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Object[]> showToreassign() {

		SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionfactory.getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Ticket.class, "Ticket");
		criteria.setFetchMode("Employee", FetchMode.JOIN);
		criteria.setFetchMode("Application", FetchMode.JOIN);

		ProjectionList projList = Projections.projectionList();

		projList.add(Projections.property("Ticket.ticket_id"));
		projList.add(Projections.property("Ticket.ticket_desc"));
		projList.add(Projections.property("Ticket.employee"));

		criteria.setProjection(projList);
		
		criteria.add(Subqueries.propertyNotIn("ticket_id", DetachedCriteria.forClass(Dev_comment.class)
				 .setProjection(Property.forName("ticket")).add(Restrictions.eq("status", "Closed"))));
		List<Object[]> List = criteria.list();

		session.getTransaction().commit();

		return List;
	}

	@SuppressWarnings({ "unchecked", "deprecation", "unused" })
	@Override
	public int reassignTicket(String oldemp, String newemp, Ticket ticket) {

		SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionfactory.getCurrentSession();
		session.beginTransaction();

		Criteria crit = session.createCriteria(Employee.class);
		crit.add(Restrictions.eq("employee_name", oldemp));
		crit.setProjection(Projections.property("employee_id"));
		List<Employee> oldemp_id = crit.list();

		Criteria criter = session.createCriteria(Employee.class);
		criter.add(Restrictions.eq("employee_name", newemp));
		criter.setProjection(Projections.property("employee_id"));
		List<Employee> newemp_id = criter.list();

		Criteria crite = session.createCriteria(Ticket.class);
		crite.add(Restrictions.eq("ticket_id", ticket.getTicket_id()));
		crite.setProjection(Projections.property("ticket_id"));
		List<Ticket> list = crite.list();

		Criteria c = session.createCriteria(Employee.class);
		c.add(Restrictions.eq("employee_name", newemp));
		List<Employee> emplist = c.list();

		int status = 0;

		if (status == 0) {
			Ticket tick = (Ticket) session.load(Ticket.class, ticket.getTicket_id());
			tick.setTicket_id(ticket.getTicket_id());
			tick.setTicket_desc(ticket.getTicket_desc());
			tick.setEmployee(emplist.get(0));
			tick.setFlag(1);
			session.update(tick);
			status = 1;

		} else {
			System.out.println("not updated");
		}

		session.getTransaction().commit();


		return status;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public int updateTicket(Ticket ticket, Dev_comment dev, Tester_comment test, String app) {

		sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Date date = new Date();

		Dev_comment deve = new Dev_comment();
		Tester_comment tester = new Tester_comment();
		Documentation doc = new Documentation();
		int status = 0;

		Criteria crit = session.createCriteria(Application.class);
		crit.add(Restrictions.eq("application_name", app));
		List<Application> list = crit.list();

		Criteria crite = session.createCriteria(Ticket.class, "ticket");
		crite.add(Restrictions.eq("ticket_id", ticket.getTicket_id()));
		List<Ticket> List = crite.list();

		Criteria cr = session.createCriteria(Ticket.class, "ticket");
		cr.add(Restrictions.eq("ticket_id", ticket.getTicket_id()));
		cr.add(Restrictions.isNull("ticket.start_date"));
		List<Object[]> Lists = cr.list();

		if (!Lists.isEmpty()) {
			Ticket t = (Ticket) session.load(Ticket.class, ticket.getTicket_id());
			t.setTicket_desc(ticket.getTicket_desc());
			t.setTicket_type(ticket.getTicket_type());
			t.setApplication(list.get(0));
			t.setPriority(ticket.getPriority());
			t.setStart_date(date);
			t.setUpdated_on(date);
			t.setWorked_on_today(ticket.getWorked_on_today());
			session.saveOrUpdate(t);

			status = 1;
		}
		System.out.println("after ticket update");
		Criteria CRIT = session.createCriteria(Ticket.class, "ticket");
		CRIT.add(Restrictions.eq("ticket_id", ticket.getTicket_id()));
		CRIT.add(Restrictions.isNotNull("ticket.start_date"));
		List<Object[]> lists = CRIT.list();

		if (!lists.isEmpty()) {

			if (dev.getStatus().equals("Closed")) {
				Ticket t = (Ticket) session.load(Ticket.class, ticket.getTicket_id());
				t.setTicket_desc(ticket.getTicket_desc());
				t.setTicket_type(ticket.getTicket_type());
				t.setApplication(list.get(0));
				t.setPriority(ticket.getPriority());
				t.setUpdated_on(date);
				t.setEnd_date(date);
				t.setWorked_on_today(ticket.getWorked_on_today());
				session.saveOrUpdate(t);

				doc.setTicket(List.get(0));
				session.save(doc);

				status = 1;

			}

			else{

				Ticket t = (Ticket) session.load(Ticket.class, ticket.getTicket_id());
				t.setTicket_desc(ticket.getTicket_desc());
				t.setTicket_type(ticket.getTicket_type());
				t.setApplication(list.get(0));
				t.setPriority(ticket.getPriority());
				t.setUpdated_on(date);
				t.setWorked_on_today(ticket.getWorked_on_today());
				session.saveOrUpdate(t);

				status = 1;
			}

		}
		Criteria criteria = session.createCriteria(Dev_comment.class);
		criteria.createAlias("ticket", "ticket");
		criteria.add(Restrictions.eq("date", date));
		criteria.add(Restrictions.eq("ticket.ticket_id", ticket.getTicket_id()));
		//criteria.setProjection(Projections.property("comment_id"));
		List<Dev_comment> commentid = criteria.list();

		Criteria criteria1 = session.createCriteria(Tester_comment.class);
		criteria1.createAlias("ticket", "ticket");
		criteria1.add(Restrictions.eq("date", date));
		criteria1.add(Restrictions.eq("ticket.ticket_id", ticket.getTicket_id()));
		//	criteria1.setProjection(Projections.property("comment_id"));
		List<Tester_comment> commentid1 = criteria1.list();

		System.out.println(commentid);
		if(!commentid.isEmpty()){
			deve = (Dev_comment) session.load(Dev_comment.class, commentid.get(0).getComment_id());
			deve.setActivity(dev.getActivity());
			deve.setComment_desc(dev.getComment_desc());
			deve.setDate(date);
			deve.setStatus(dev.getStatus());
			deve.setTicket(List.get(0));
			session.update(deve);

			tester = (Tester_comment) session.load(Tester_comment.class, commentid1.get(0).getComment_id());
			tester.setTicket(List.get(0));
			tester.setTester(test.getTester());
			tester.setDate(date);
			session.update(tester);
		}
		else{
			deve.setActivity(dev.getActivity());
			deve.setComment_desc(dev.getComment_desc());
			deve.setDate(date);
			deve.setStatus(dev.getStatus());
			deve.setTicket(List.get(0));
			session.save(deve);

			tester.setTicket(List.get(0));
			tester.setTester(test.getTester());
			tester.setDate(date);
			session.save(tester);
		}
		session.getTransaction().commit();

		return status;

	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public Employee getEmployeeDetails(String empid) {

		sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Employee employee = new Employee();
		
		Criteria criteria = session.createCriteria(Employee.class);
		
		criteria.add(Restrictions.eq("employee_id", empid));
		
		
		List<Employee> list = criteria.list();

		int n = list.size();
		System.out.println("SHIFT COUNT= " + n);

		if (!list.isEmpty()) {
			employee.setEmployee_id(list.get(0).getEmployee_id());
			employee.setEmployee_name(list.get(0).getEmployee_name());
			employee.setIs_active(list.get(0).getIs_active());
			employee.setIs_admin(list.get(0).getIs_admin());
			employee.setPassword(list.get(0).getPassword());
			employee.setReport(list.get(0).isReport());
			
		}
		session.getTransaction().commit();

		return employee;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public Application getApplicationDetails(String application_name) {

		sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Application application = new Application();
		Criteria criteria = session.createCriteria(Application.class);
		criteria.add(Restrictions.eq("application_name", application_name));

		List<Application> list = criteria.list();
		if (!list.isEmpty()) {
			application.setApplication_id(list.get(0).getApplication_id());
			application.setApplication_name(list.get(0).getApplication_name());
			application.setApplication_shortname(list.get(0).getApplication_shortname());
		}
		session.getTransaction().commit();

		return application;
	}

	@SuppressWarnings("deprecation")
	public int releaseTicket(String ticketId, String release) {

		SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionfactory.getCurrentSession();
		session.beginTransaction();
		int status = 1;
		if (release.equalsIgnoreCase("Yes")) {
			Ticket tick = (Ticket) session.load(Ticket.class, ticketId);
			tick.setRelease_ticket("No");
			session.update(tick);
			status = 1;
		} else {
			Ticket tick = (Ticket) session.load(Ticket.class, ticketId);
			tick.setRelease_ticket("Yes");
			session.update(tick);
			status = 1;
			System.out.println("ticket..No"+release);
		}
		session.getTransaction().commit();

		return status;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Object[]> showRelease() {

		List<Object[]> TicketList = null;
		try {
			SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
			Session session = sessionfactory.getCurrentSession();
			session.beginTransaction();

			Criteria criteria = session.createCriteria(Ticket.class, "Ticket");
			ProjectionList projList = Projections.projectionList();
			projList.add(Projections.property("Ticket.ticket_id"));
			projList.add(Projections.property("Ticket.ticket_desc"));
			projList.add(Projections.property("Ticket.application"));
			projList.add(Projections.property("Ticket.employee"));
			projList.add(Projections.property("Ticket.release_ticket"));
			criteria.setProjection(projList);
			
			criteria.add(Subqueries.propertyNotIn("ticket_id", DetachedCriteria.forClass(Dev_comment.class)
					 .setProjection(Property.forName("ticket")).add(Restrictions.eq("status", "Closed"))));
			
			TicketList = criteria.list();
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return TicketList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Upload_ticket> showDate() {

		@SuppressWarnings("deprecation")
		SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionfactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Upload_ticket.class, "upload_ticket");

		ProjectionList projList = Projections.projectionList();

		projList.add(Projections.max("upload_ticket.createdOn"));
	
		criteria.setProjection(projList);
		

		List<Upload_ticket> List = criteria.list();

		session.getTransaction().commit();
		session.close();
		return List;
	}

}
