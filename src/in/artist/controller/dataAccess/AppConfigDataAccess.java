package in.artist.controller.dataAccess;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.artist.database.classes.AppConfig;
import in.artist.util.DBUtil;

public class AppConfigDataAccess extends BaseAccess {

	public AppConfigDataAccess() {
		super(AppConfigDataAccess.class.getName());
	}

	public List<AppConfig> getAppConfigDetails() {
		Transaction transaction = null;
		Session session = null;
		List<AppConfig> appConfigDetailList = new ArrayList<AppConfig>();
		try {
			session = DBUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			String sql = "SELECT * FROM " + DBUtil.TABLE_APP_CONFIG;
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(AppConfig.class);
			java.util.List results = (java.util.List) query.list();

			if (results != null && results.size() > 0) {
				appConfigDetailList.addAll((ArrayList<AppConfig>) results);
			}

			transaction.commit();
			session.close();

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return appConfigDetailList;
	}

}
