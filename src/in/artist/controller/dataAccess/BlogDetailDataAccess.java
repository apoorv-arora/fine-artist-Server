package in.artist.controller.dataAccess;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.artist.database.classes.BlogUpdates;
import in.artist.database.classes.User;
import in.artist.util.DBUtil;

public class BlogDetailDataAccess extends BaseAccess {

	public BlogDetailDataAccess() {
		super(BlogDetailDataAccess.class.getName());
	}

	public List<BlogUpdates> getBlogUpdates() {
		Transaction transaction = null;
		Session session = null;
		List<BlogUpdates> courses = new ArrayList<BlogUpdates>();
		try {
			session = DBUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			String sql = "SELECT * FROM " + DBUtil.TABLE_BLOG_UPDATES + " limit 20";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(BlogUpdates.class);
			java.util.List results = (java.util.List) query.list();

			if (results != null && results.size() > 0) {
				courses.addAll((ArrayList<BlogUpdates>) results);
			}

			transaction.commit();
			session.close();

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return courses;
	}
	
	public BlogUpdates addBlogUpdate(BlogUpdates user) {
		Transaction transaction = null;
		Session session = null;

		try {
			session = DBUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			session.save(user);
			transaction.commit();
			session.close();

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return user;
	}

}
