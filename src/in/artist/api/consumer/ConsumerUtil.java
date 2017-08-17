package in.artist.api.consumer;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import in.artist.api.BaseResource;
import in.artist.controller.dataAccess.AppConfigDataAccess;
import in.artist.responseDto.AppConfigResponseDto;
import in.artist.util.CommonLib;

@Path("/consumer/util")
public class ConsumerUtil extends BaseResource {

	public ConsumerUtil() {
		super(ConsumerUtil.class.getName());
	}

	/**
	 * @author apoorvarora
	 */
	@Path("/config")
	@POST
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String getPriceList(@FormParam("accessToken") String accessToken) {
		AppConfigResponseDto responseDto = new AppConfigResponseDto();
		AppConfigDataAccess appConfigDataAccess = new AppConfigDataAccess();
		responseDto.setAppConfigList(appConfigDataAccess.getAppConfigDetails());
		responseDto.setCode(CommonLib.RESPONSE_CODE_SUCCESS);
		return CommonLib.getJson(responseDto);
	}

}
