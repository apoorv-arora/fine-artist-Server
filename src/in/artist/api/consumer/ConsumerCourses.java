package in.artist.api.consumer;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import in.artist.api.BaseResource;
import in.artist.controller.dataAccess.SubcriptionDataAccess;
import in.artist.controller.dataAccess.UserDetailDataAccess;
import in.artist.database.classes.User;
import in.artist.database.classes.UserCourseSubscription;
import in.artist.responseDto.MySubscriptionDtoList;
import in.artist.responseDto.RecommendedCoursesDto;
import in.artist.responseDto.SubscriptionDto;
import in.artist.responseDto.util.Converter;
import in.artist.util.CommonLib;

@Path("/consumer/courses")
public class ConsumerCourses extends BaseResource {

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
	public String getPriceList(@FormParam("accessToken") String accessToken) {
		MySubscriptionDtoList responseDto = new MySubscriptionDtoList();
		UserDetailDataAccess uDetailAccess = new UserDetailDataAccess();
		User user = uDetailAccess.getUserDetails(accessToken);
		// check if supplier exists
		if (user != null && user.getUserId() > 0) {
			SubcriptionDataAccess cDetailDataAccess = new SubcriptionDataAccess();
			List<SubscriptionDto> courses = Converter.convertListOfUserCourseSubscriptionToListOfSubscriptionDto(
					cDetailDataAccess.getSubscribedCoursesForUserId(user.getUserId()));
			responseDto.setSubscriptions(courses);
			responseDto.setCode(CommonLib.RESPONSE_CODE_SUCCESS);
		} else {
			responseDto.setCode(CommonLib.RESPONSE_CODE_USER_DOESN_NOT_EXIST);
			responseDto.setErrorMessage(CommonLib.ERROR_USER_DOES_NOT_EXIST);
		}
		return CommonLib.getJson(responseDto);
	}

	@Path("/subscribe")
	@POST
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String subscribeCourse(@FormParam("accessToken") String accessToken, @FormParam("courseId") int courseId) {
		RecommendedCoursesDto responseDto = new RecommendedCoursesDto();
		UserDetailDataAccess uDetailAccess = new UserDetailDataAccess();
		User user = uDetailAccess.getUserDetails(accessToken);
		// check if supplier exists
		if (user != null && user.getUserId() > 0) {
			SubcriptionDataAccess cDetailDataAccess = new SubcriptionDataAccess();
			UserCourseSubscription userCourseSubscription = new UserCourseSubscription();
			userCourseSubscription.setCourseId(courseId);
			userCourseSubscription.setUserId(user.getUserId());
			userCourseSubscription.setCreated(System.currentTimeMillis());
			cDetailDataAccess.subscribedCourseForUserId(userCourseSubscription);
			responseDto.setCode(CommonLib.RESPONSE_CODE_SUCCESS);
		} else {
			responseDto.setCode(CommonLib.RESPONSE_CODE_USER_DOESN_NOT_EXIST);
			responseDto.setErrorMessage(CommonLib.ERROR_USER_DOES_NOT_EXIST);
		}
		return CommonLib.getJson(responseDto);
	}

}
