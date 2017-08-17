package in.artist.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class DBUtil {

	private static SessionFactory sessionFactory;

	// table names
	public static final String TABLE_APP_CONFIG = "appconfig";
	public static final String TABLE_USER = "user";
	public static final String TABLE_USER_SESSION = "usersession";
	public static final String TABLE_COURSE_CATEGORY = "coursecategory";
	public static final String TABLE_COURSE = "course";
	public static final String TABLE_BLOG_UPDATES = "blogupdates";
	public static final String TABLE_SUB_COURSE = "subcourse";
	public static final String TABLE_SUB_COURSE_DETAIL = "subcoursedetail";
	public static final String TABLE_SUB_COURSE_DETAIL_LABEL = "subcoursedetaillabel";
	public static final String TABLE_COURSE_OBJECTIVES = "courseobjectives";

	public static final String COLUMN_USER_ID = "id";
	public static final String COLUMN_USER_NAME = "name";
	public static final String COLUMN_USER_NUMBER = "number";
	public static final String COLUMN_USER_EMAIL = "email";
	public static final String COLUMN_USER_IMAGEURL = "imageUrl";
	public static final String COLUMN_USER_CREATED = "created";
	public static final String COLUMN_USER_MODIFIED = "modified";

	public static final String COLUMN_USER_SESSION_ID = "sessionId";
	public static final String COLUMN_USER_SESSION_ACCESS_TOKEN = "accessToken";
	public static final String COLUMN_USER_SESSION_UID = "uid";
	public static final String COLUMN_USER_SESSION_CREATED = "created";
	public static final String COLUMN_USER_SESSION_MODIFIED = "modified";

	public static final String COLUMN_APP_CONFIG_ID = "appConfigId";
	public static final String COLUMN_APP_CONFIG_KEY = "key";
	public static final String COLUMN_APP_CONFIG_VALUE = "value";

	private static SessionFactory configureSessionFactory() throws HibernateException {

		if (sessionFactory == null) {
			Configuration configuration = new Configuration();
			configuration.configure();
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
					.buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		}
		return sessionFactory;
	}

	public static SessionFactory getSessionFactory() {
		return configureSessionFactory();

	}

}