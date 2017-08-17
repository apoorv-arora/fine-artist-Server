package in.artist.controller.dataAccess;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.artist.database.classes.User;
import in.artist.database.classes.UserSession;
import in.artist.util.CommonLib;
import in.artist.util.DBUtil;

public class UserDetailDataAccess extends BaseAccess {

	public UserDetailDataAccess() {
		super(UserDetailDataAccess.class.getName());
	}

	public User getUserDetails(String accessToken) {
		Transaction transaction = null;
		Session session = null;
		User user = null;

		try {
			session = DBUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			String sql = "SELECT * FROM " + DBUtil.TABLE_USER + " WHERE " + DBUtil.COLUMN_USER_ID + " = " + "(SELECT "
					+ DBUtil.COLUMN_USER_SESSION_UID + " FROM " + DBUtil.TABLE_USER_SESSION + " WHERE "
					+ DBUtil.COLUMN_USER_SESSION_ACCESS_TOKEN + " = :accessToken)";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(User.class);
			query.setParameter("accessToken", accessToken);
			java.util.List results = (java.util.List) query.list();

			if (results != null && results.size() > 0 && results.get(0) instanceof User) {
				user = (User) results.get(0);
			}
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

	public User getUserDetails(int userId) {
		Transaction transaction = null;
		Session session = null;
		User user = null;

		try {
			session = DBUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			String sql = "SELECT * FROM " + DBUtil.TABLE_USER + " WHERE " + DBUtil.COLUMN_USER_ID
					+ " = :userId limit 1";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(User.class);
			query.setParameter("userId", userId);
			java.util.List results = (java.util.List) query.list();

			if (results != null && results.size() > 0 && results.get(0) instanceof User) {
				user = (User) results.get(0);
			}
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

	public User getUserDetailsFromEmail(String email) {
		Transaction transaction = null;
		Session session = null;
		User user = null;

		try {
			session = DBUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			String sql = "SELECT * FROM " + DBUtil.TABLE_USER + " WHERE " + DBUtil.COLUMN_USER_EMAIL
					+ " = :email limit 1";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(User.class);
			query.setParameter("email", email);
			java.util.List results = (java.util.List) query.list();

			if (results != null && results.size() > 0 && results.get(0) instanceof User) {
				user = (User) results.get(0);
			}
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

	public UserSession setUserSessionDetails(int userId) {
		Transaction transaction = null;
		Session session = null;
		UserSession userSession = null;

		try {
			session = DBUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			String sql = "SELECT * FROM " + DBUtil.TABLE_USER_SESSION + " WHERE " + DBUtil.COLUMN_USER_SESSION_UID
					+ " = :userId limit 1";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(UserSession.class);
			query.setParameter("userId", userId);
			java.util.List results = (java.util.List) query.list();

			if (results != null && results.size() > 0 && results.get(0) instanceof UserSession) {
				userSession = (UserSession) results.get(0);
			} else {
				userSession = new UserSession();
				userSession.setAccessToken(CommonLib.getAccessToken(userId));
				userSession.setCreated(System.currentTimeMillis());
				userSession.setUid(userId);

				session.save(userSession);
			}

			transaction.commit();
			session.close();

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return userSession;
	}

	/**
	 * Delete an accessToken for a particular user
	 */
	public boolean removeUserSession(String accessToken) {

		Session session = null;
		info("nullifyAccessToken enter");

		try {
			session = DBUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();

			String sql = "DELETE FROM " + DBUtil.TABLE_USER_SESSION + " WHERE "
					+ DBUtil.COLUMN_USER_SESSION_ACCESS_TOKEN + " = :access_token";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(UserSession.class);
			query.setParameter("access_token", accessToken);
			query.executeUpdate();
			transaction.commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		info("nullifyAccessToken exit");
		return false;
	}

	public User addUser(User user) {
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

	public User updateUserDetails(User user) {

		Session session = null;
		info("updateUserDetails enter");
		try {
			session = DBUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			session.update(user);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			user = null;
			error("Hibernate exception: " + e.getMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		info("userActive exit");
		return user;
	}

}
