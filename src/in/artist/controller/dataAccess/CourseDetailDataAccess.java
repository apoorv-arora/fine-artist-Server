package in.artist.controller.dataAccess;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.artist.database.classes.Course;
import in.artist.util.DBUtil;

public class CourseDetailDataAccess extends BaseAccess {

	public CourseDetailDataAccess() {
		super(CourseDetailDataAccess.class.getName());
	}

	public List<Course> getRecommendedCourses() {
		Transaction transaction = null;
		Session session = null;
		List<Course> courses = new ArrayList<Course>();
		try {
			session = DBUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			String sql = "SELECT * FROM " + DBUtil.TABLE_COURSE + " limit 20";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Course.class);
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
