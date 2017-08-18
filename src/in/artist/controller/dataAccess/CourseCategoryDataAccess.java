package in.artist.controller.dataAccess;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.artist.database.classes.Course;
import in.artist.database.classes.CourseCategory;
import in.artist.util.DBUtil;

public class CourseCategoryDataAccess extends BaseAccess {

	public CourseCategoryDataAccess() {
		super(CourseCategoryDataAccess.class.getName());
	}

	public List<CourseCategory> getCourseCategories() {
		Transaction transaction = null;
		Session session = null;
		List<CourseCategory> courses = new ArrayList<CourseCategory>();
		try {
			session = DBUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			String sql = "SELECT * FROM " + DBUtil.TABLE_COURSE_CATEGORY + " limit 20";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(CourseCategory.class);
			java.util.List results = (java.util.List) query.list();

			if (results != null && results.size() > 0) {
				courses.addAll((ArrayList<CourseCategory>) results);
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

	public List<Course> getCoursesForCategoryId(int categoryId) {
		Transaction transaction = null;
		Session session = null;
		List<Course> courses = new ArrayList<Course>();
		try {
			session = DBUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			String sql = "SELECT * FROM " + DBUtil.TABLE_COURSE + " where " + DBUtil.COLUMN_COURSE_CATEGORY_ID
					+ " =:categoryId";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Course.class);
			query.setParameter("categoryId", categoryId);

			java.util.List results = (java.util.List) query.list();

			if (results != null && results.size() > 0) {
				courses.addAll((ArrayList<Course>) results);
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
	
	public List<Course> getCoursesForQueryId(String requestQuery) {
		Transaction transaction = null;
		Session session = null;
		List<Course> courses = new ArrayList<Course>();
		try {
			session = DBUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			String sql = "SELECT * FROM " + DBUtil.TABLE_COURSE + " where " + DBUtil.COLUMN_COURSE_TITLE
					+ " like :requestQuery" + "%";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Course.class);
			query.setParameter("requestQuery", requestQuery);

			java.util.List results = (java.util.List) query.list();

			if (results != null && results.size() > 0) {
				courses.addAll((ArrayList<Course>) results);
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
}
