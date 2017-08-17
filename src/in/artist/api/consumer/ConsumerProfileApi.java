package in.artist.api.consumer;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import in.artist.api.BaseResource;
import in.artist.controller.dataAccess.UserDetailDataAccess;
import in.artist.database.classes.User;
import in.artist.responseDto.UserLoginResponseDto;
import in.artist.util.CommonLib;

@Path("/consumer/profile")
public class ConsumerProfileApi extends BaseResource {

	public ConsumerProfileApi() {
		super(ConsumerProfileApi.class.getName());
	}

	/**
	 * @author apoorvarora
	 */
	@Path("/update")
	@POST
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String getPriceList(@FormParam("accessToken") String accessToken, @FormParam("number") String number) {
		UserLoginResponseDto responseDto = new UserLoginResponseDto();
		UserDetailDataAccess uDetailAccess = new UserDetailDataAccess();
		User user = uDetailAccess.getUserDetails(accessToken);
		// check if supplier exists
		if (user != null && user.getUserId() > 0) {
			user.setNumber(number);
			user = uDetailAccess.updateUserDetails(user);
			responseDto.setUser(user);
			responseDto.setCode(CommonLib.RESPONSE_CODE_SUCCESS);
		} else {
			responseDto.setCode(CommonLib.RESPONSE_CODE_USER_DOESN_NOT_EXIST);
			responseDto.setErrorMessage(CommonLib.ERROR_USER_DOES_NOT_EXIST);
		}
		return CommonLib.getJson(responseDto);
	}

}
