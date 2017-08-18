package in.artist.controller.dataAccess;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.artist.database.classes.UserCourseSubscription;
import in.artist.util.DBUtil;

public class SubcriptionDataAccess extends BaseAccess {

	public SubcriptionDataAccess() {
		super(SubcriptionDataAccess.class.getName());
	}

	public List<UserCourseSubscription> getSubscribedCoursesForUserId(int userId) {
		Transaction transaction = null;
		Session session = null;
		List<UserCourseSubscription> courses = new ArrayList<UserCourseSubscription>();
		try {
			session = DBUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			String sql = "SELECT * FROM " + DBUtil.TABLE_COURSE_SUBSCRIPTION + " where "
					+ DBUtil.COLUMN_SUBSCRIPTION_USER_ID + " =:userId";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(UserCourseSubscription.class);
			query.setParameter("userId", userId);
			java.util.List results = (java.util.List) query.list();

			if (results != null && results.size() > 0) {
				courses.addAll((ArrayList<UserCourseSubscription>) results);
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

	public int subscribedCourseForUserId(UserCourseSubscription subscription) {
		if (subscription == null || subscription.getUserCourseId() > 0)
			return 0;

		Transaction transaction = null;
		Session session = null;

		try {
			session = DBUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			String sql = "SELECT * FROM " + DBUtil.TABLE_COURSE_SUBSCRIPTION + " WHERE "
					+ DBUtil.COLUMN_SUBSCRIPTION_USER_ID + " = :userId and " + DBUtil.COLUMN_SUBSCRIPTION_COURSE_ID
					+ " =:courseId";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(UserCourseSubscription.class);
			query.setParameter("userId", subscription.getUserId());
			query.setParameter("courseId", subscription.getCourseId());
			java.util.List results = (java.util.List) query.list();

			if (results != null && results.size() > 0 && results.get(0) instanceof UserCourseSubscription) {
				subscription.setModified(System.currentTimeMillis());
				subscription.setUserCourseId(((UserCourseSubscription) results.get(0)).getUserCourseId());
			} else {
				session.save(subscription);
			}

			transaction.commit();
			session.close();

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return subscription.getUserCourseId();
	}
}
