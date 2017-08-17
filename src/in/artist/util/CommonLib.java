package in.artist.util;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.regex.Pattern;

import org.apache.tomcat.util.codec.binary.Base64;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;

public class CommonLib {

	// such properties needs to be triggered from DB
	public static final boolean ZLOG = false;
	public static final boolean LIVE_BUILD = false;

	public static final int RESPONSE_CODE_SUCCESS = 0;
	public static final int RESPONSE_CODE_USER_DOESN_NOT_EXIST = 1001;
	public static final int RESPONSE_CODE_INVALID_EMAIL = 1002;
	public static final int RESPONSE_CODE_UNAUTHORIZED = 401;

	public static final String ERROR_USER_DOES_NOT_EXIST = "User does not exist";
	public static final String ERROR_USER_ALREADY_EXIST = "User already exists";
	public static final String ERROR_INVALID_EMAIL = "Invalid email";
	public static final String ERROR_HACK_TRY = "I am sorry, but who's this?";

	public static final String ZAPP_ID = "shiftnoww@gmail.com";
	public static final String ZAPP_PWD = "shift.noww";

	public static final int BOOKING_STATUS_CREATED = 101;
	public static final int BOOKING_STATUS_ASSIGNED = 102;
	public static final int BOOKING_STATUS_COMPLETED = 102;
	public static final int BOOKING_STATUS_CANCELLED = 102;

	public static String getJson(Object object) {
		return new Gson().toJson(object);
	}

	public static Object fromJson(String json, Class<?> T) {
		return new Gson().fromJson(json, T);
	}

	public static String randomPassword() {
		return new BigInteger(130, new SecureRandom()).toString(32);
	}

	public static String defaultSupplierImageUrl() {
		return "";
	}

	public static boolean isValidEmail(String email) {
		boolean result = false;
		try {
			String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
			Pattern p = Pattern.compile(ePattern);
			java.util.regex.Matcher m = p.matcher(email);
			result = m.matches();
		} catch (Exception ex) {
			result = false;
		}
		return result;
	}

	public static JSONObject getJsonObject(String json) {
		try {
			return new JSONObject(json);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new JSONObject();
	}

	public static String getAccessToken(int supplierId) {
		String keySource = supplierId + "shiftnow" + String.valueOf(System.currentTimeMillis() * 1000)
				+ String.valueOf((int) (Math.random() * 1000 * 1000));
		byte[] tokenByte = Base64.encodeBase64(keySource.getBytes());
		return new String(tokenByte);
	}

}
