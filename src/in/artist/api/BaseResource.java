package in.artist.api;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import in.artist.util.CommonLib;

public class BaseResource {

	// Logger object
	private Logger logger;

	public BaseResource(String classObj) {
		if (CommonLib.ZLOG && classObj != null) {
			logger = Logger.getLogger(classObj);
			BasicConfigurator.configure();
		}
	}

	public void debug(Object message) {
		if (CommonLib.ZLOG && message != null)
			logger.debug(message);
	}

	public void info(Object message) {
		if (CommonLib.ZLOG && message != null)
			logger.info(message);
	}

	public void error(Object message) {
		if (CommonLib.ZLOG && message != null)
			logger.error(message);
	}

	public void fatal(Object message) {
		if (CommonLib.ZLOG && message != null)
			logger.fatal(message);
	}
}
