package in.artist.api.course;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import in.artist.api.BaseResource;
import in.artist.controller.dataAccess.CourseCategoryDataAccess;
import in.artist.controller.dataAccess.UserDetailDataAccess;
import in.artist.database.classes.CourseCategory;
import in.artist.database.classes.User;
import in.artist.responseDto.CourseCategoriesResponseDto;
import in.artist.responseDto.RecommendedCoursesDto;
import in.artist.responseDto.util.Converter;
import in.artist.util.CommonLib;

@Path("/course/category")
public class CategoryApi extends BaseResource {

	public CategoryApi() {
		super(CategoryApi.class.getName());
	}

	/**
	 * @author apoorvarora
	 */
	@Path("/all")
	@POST
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String getCourseCategories(@FormParam("accessToken") String accessToken) {
		CourseCategoriesResponseDto responseDto = new CourseCategoriesResponseDto();
		UserDetailDataAccess uDetailAccess = new UserDetailDataAccess();
		User user = uDetailAccess.getUserDetails(accessToken);
		// check if supplier exists
		if (user != null && user.getUserId() > 0) {
			CourseCategoryDataAccess cDetailDataAccess = new CourseCategoryDataAccess();
			List<CourseCategory> courseCategories = cDetailDataAccess.getCourseCategories();
			responseDto.setCourseCategories(courseCategories);
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
	@Path("/courses")
	@POST
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String getCourseCategoriesForCategoryId(@FormParam("accessToken") String accessToken,
			@FormParam("categoryId") int categoryId) {
		RecommendedCoursesDto responseDto = new RecommendedCoursesDto();
		UserDetailDataAccess uDetailAccess = new UserDetailDataAccess();
		User user = uDetailAccess.getUserDetails(accessToken);
		// check if supplier exists
		if (user != null && user.getUserId() > 0) {
			CourseCategoryDataAccess cDetailDataAccess = new CourseCategoryDataAccess();
			responseDto.setRecommendedCourses(Converter.convertListOfCoursesToListOfCourseBriefDtos(
					cDetailDataAccess.getCoursesForCategoryId(categoryId)));
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
	@Path("/search")
	@POST
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String getCourseCategoriesForCategoryId(@FormParam("accessToken") String accessToken,
			@QueryParam("query") String query) {
		RecommendedCoursesDto responseDto = new RecommendedCoursesDto();
		UserDetailDataAccess uDetailAccess = new UserDetailDataAccess();
		User user = uDetailAccess.getUserDetails(accessToken);
		// check if supplier exists
		if (user != null && user.getUserId() > 0) {
			CourseCategoryDataAccess cDetailDataAccess = new CourseCategoryDataAccess();
			responseDto.setRecommendedCourses(Converter
					.convertListOfCoursesToListOfCourseBriefDtos(cDetailDataAccess.getCoursesForQueryId(query)));
			responseDto.setCode(CommonLib.RESPONSE_CODE_SUCCESS);
		} else {
			responseDto.setCode(CommonLib.RESPONSE_CODE_USER_DOESN_NOT_EXIST);
			responseDto.setErrorMessage(CommonLib.ERROR_USER_DOES_NOT_EXIST);
		}
		return CommonLib.getJson(responseDto);
	}

}
