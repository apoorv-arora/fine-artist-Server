package in.artist.api.auth;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import in.artist.api.BaseResource;
import in.artist.controller.dataAccess.UserDetailDataAccess;
import in.artist.database.classes.User;
import in.artist.database.classes.UserSession;
import in.artist.responseDto.BaseResponse;
import in.artist.responseDto.UserLoginResponseDto;
import in.artist.util.CommonLib;

@Path("/auth/consumer")
public class ConsumerApi extends BaseResource {

	public static final int LOGIN_TYPE_GOOGLE = 101;
	public static final int LOGIN_TYPE_FACEBOOK = 102;
	public static final int LOGIN_TYPE_SELF = 103;

	public ConsumerApi() {
		super(ConsumerApi.class.getName());
	}

	/**
	 * @author apoorvarora
	 */
	@Path("/login")
	@POST
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String authorization(@FormParam("userName") String userName, @FormParam("email") String email,
			@FormParam("password") String password, @FormParam("registrationId") String regId,
			@FormParam("latitude") double latitude, @FormParam("longitude") double longitude,
			@FormParam("deviceInfo") String deviceInfo, @FormParam("facebookId") String fbId,
			@FormParam("facebookData") String fbData, @FormParam("token") String token,
			@FormParam("profile_pic") String profilePic, @FormParam("facebookPermission") String fb_permissions,
			@FormParam("logintype") int loginType) {
		UserLoginResponseDto responseDto = new UserLoginResponseDto();
		User user = null;
		UserDetailDataAccess uDetailDataAccess = new UserDetailDataAccess();
		if (loginType == LOGIN_TYPE_FACEBOOK || loginType == LOGIN_TYPE_GOOGLE) {
			// check facebook parameters

			// check if email exists or not
			user = uDetailDataAccess.getUserDetailsFromEmail(email);

			if (user != null) {
				// user exists
				boolean isValueChanged = false;
				if (user.getFacebookData() != null && fbData != null && !user.getFacebookData().equals(fbData)) {
					user.setFacebookData(fbData);
					isValueChanged = true;
				}
				if (user.getFacebookToken() != null && token != null && !user.getFacebookToken().equals(token)) {
					user.setFacebookToken(token);
					isValueChanged = true;
				}
				if (user.getFacebookPermission() != null && fb_permissions != null
						&& !user.getFacebookPermission().equals(fb_permissions)) {
					user.setFacebookPermission(fb_permissions);
					isValueChanged = true;
				}
				if (user.getFacebookId() != null && fbId != null && !user.getFacebookId().equals(fbId)) {
					user.setFacebookId(fbId);
					isValueChanged = true;
				}
				if (user.getImageUrl() != null && profilePic != null && !user.getImageUrl().equals(profilePic)) {
					user.setImageUrl(profilePic);
					isValueChanged = true;
				}
				if (isValueChanged) {
					final User newUser = user;
					Runnable runnable = new Runnable() {
						public void run() {
							uDetailDataAccess.updateUserDetails(newUser);
						}
					};
					Thread newThread = new Thread(runnable);
					newThread.start();
				}
			} else {
				// user does not exist
				user = new User();
				user.setImageUrl(profilePic);
				user.setName(userName);
				user.setEmail(email);
				user.setFacebookId(fbId);
				user.setFacebookData(fbData);
				user.setFacebookPermission(fb_permissions);
				user.setCreated(System.currentTimeMillis());
				user.setModified(0);
				user.setFacebookToken(token);

				user = uDetailDataAccess.addUser(user);

				// send the email
			}
		}

		if (user == null || user.getUserId() <= 0) {
			responseDto.setCode(CommonLib.RESPONSE_CODE_USER_DOESN_NOT_EXIST);
			responseDto.setErrorMessage(CommonLib.ERROR_USER_DOES_NOT_EXIST);
			return CommonLib.getJson(responseDto);
		}

		UserSession userSession = uDetailDataAccess.setUserSessionDetails(user.getUserId());
		if (userSession != null && userSession.getAccessToken() != null && userSession.getUid() == user.getUserId()) {
			responseDto.setAccessToken(userSession.getAccessToken());
			responseDto.setUser(user);
		}

		return CommonLib.getJson(responseDto);
	}

	/**
	 * Logout
	 * 
	 * @author apoorvarora
	 */
	@Path("/logout")
	@POST
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String userLogout(@FormParam("accessToken") String accessToken) {
		BaseResponse responseDto = new BaseResponse();

		UserDetailDataAccess uDetailAccess = new UserDetailDataAccess();
		User user = uDetailAccess.getUserDetails(accessToken);
		// check if supplier exists
		if (user != null && user.getUserId() > 0) {
			uDetailAccess.removeUserSession(accessToken);
			responseDto.setCode(CommonLib.RESPONSE_CODE_SUCCESS);
		} else {
			responseDto.setCode(CommonLib.RESPONSE_CODE_USER_DOESN_NOT_EXIST);
			responseDto.setErrorMessage(CommonLib.ERROR_USER_DOES_NOT_EXIST);
		}
		return CommonLib.getJson(responseDto);
	}

}