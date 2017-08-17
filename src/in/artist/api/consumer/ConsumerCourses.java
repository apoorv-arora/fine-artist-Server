package in.artist.api.consumer;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import in.artist.api.BaseResource;
import in.artist.controller.dataAccess.CourseDetailDataAccess;
import in.artist.controller.dataAccess.UserDetailDataAccess;
import in.artist.database.classes.User;
import in.artist.responseDto.CourseBriefDto;
import in.artist.responseDto.RecommendedCoursesDto;
import in.artist.responseDto.util.Converter;
import in.artist.util.CommonLib;

@Path("/consumer/courses")
public class ConsumerCourses  extends BaseResource {

	public ConsumerCourses() {
		super(ConsumerCourses.class.getName());
	}

	/**
	 * @author apoorvarora
	 */
	@Path("/mine")
	@POST
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String getPriceList(@FormParam("accessToken") String accessToken, @FormParam("number") String number) {
		RecommendedCoursesDto responseDto = new RecommendedCoursesDto();
		UserDetailDataAccess uDetailAccess = new UserDetailDataAccess();
		User user = uDetailAccess.getUserDetails(accessToken);
		// check if supplier exists
		if (user != null && user.getUserId() > 0) {
			CourseDetailDataAccess cDetailDataAccess = new CourseDetailDataAccess();
			List<CourseBriefDto> courses = Converter
					.convertListOfCoursesToListOfCourseBriefDtos(cDetailDataAccess.getRecommendedCourses());
			responseDto.setRecommendedCourses(courses);
			responseDto.setCode(CommonLib.RESPONSE_CODE_SUCCESS);
		} else {
			responseDto.setCode(CommonLib.RESPONSE_CODE_USER_DOESN_NOT_EXIST);
			responseDto.setErrorMessage(CommonLib.ERROR_USER_DOES_NOT_EXIST);
		}
		return CommonLib.getJson(responseDto);
	}

}
