package in.artist.api.blog;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import in.artist.api.BaseResource;
import in.artist.controller.dataAccess.BlogDetailDataAccess;
import in.artist.controller.dataAccess.UserDetailDataAccess;
import in.artist.database.classes.BlogUpdates;
import in.artist.database.classes.User;
import in.artist.responseDto.BlogUpdatesResponseDto;
import in.artist.util.CommonLib;

@Path("/blog/updates/")
public class UpdatesApi extends BaseResource {

	public UpdatesApi() {
		super(UpdatesApi.class.getName());
	}

	/**
	 * @author apoorvarora
	 */
	@Path("/all")
	@POST
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String getRecommendedCourses(@FormParam("accessToken") String accessToken) {
		BlogUpdatesResponseDto responseDto = new BlogUpdatesResponseDto();
		UserDetailDataAccess uDetailAccess = new UserDetailDataAccess();
		User user = uDetailAccess.getUserDetails(accessToken);
		// check if supplier exists
		if (user != null && user.getUserId() > 0) {
			BlogDetailDataAccess bDetailDataAccess = new BlogDetailDataAccess();
			responseDto.setBlogUpdates(bDetailDataAccess.getBlogUpdates());
			responseDto.setCode(CommonLib.RESPONSE_CODE_SUCCESS);
		} else {
			responseDto.setCode(CommonLib.RESPONSE_CODE_USER_DOESN_NOT_EXIST);
			responseDto.setErrorMessage(CommonLib.ERROR_USER_DOES_NOT_EXIST);
		}
		return CommonLib.getJson(responseDto);
	}

	/**
	 * @author apoorvarora
	 */
	@Path("/add")
	@POST
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String addBlogUpdate(@FormParam("accessToken") String accessToken,
			@FormParam("longDescription") String longDescription,
			@FormParam("shortDescription") String shortDescription, @FormParam("refImageUrl") String refImageUrl,
			@FormParam("title") String title, @FormParam("refUrl") String refUrl) {
		BlogUpdatesResponseDto responseDto = new BlogUpdatesResponseDto();
		UserDetailDataAccess uDetailAccess = new UserDetailDataAccess();
		User user = uDetailAccess.getUserDetails(accessToken);
		// check if supplier exists
		if (user != null && user.getUserId() > 0) {
			BlogDetailDataAccess bDetailDataAccess = new BlogDetailDataAccess();
			BlogUpdates blogUpdate = new BlogUpdates();
			blogUpdate.setCreated(System.currentTimeMillis());
			blogUpdate.setLongDescription(longDescription);
			blogUpdate.setShortDescription(shortDescription);
			blogUpdate.setRefImageUrl(refImageUrl);
			blogUpdate.setRefUrl(refUrl);
			blogUpdate.setTitle(title);
			bDetailDataAccess.addBlogUpdate(blogUpdate);
			responseDto.setCode(CommonLib.RESPONSE_CODE_SUCCESS);
		} else {
			responseDto.setCode(CommonLib.RESPONSE_CODE_USER_DOESN_NOT_EXIST);
			responseDto.setErrorMessage(CommonLib.ERROR_USER_DOES_NOT_EXIST);
		}
		return CommonLib.getJson(responseDto);
	}

}
