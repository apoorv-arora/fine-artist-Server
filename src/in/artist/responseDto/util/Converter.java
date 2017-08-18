package in.artist.responseDto.util;

import java.util.ArrayList;
import java.util.List;

import in.artist.controller.dataAccess.CourseDetailDataAccess;
import in.artist.controller.dataAccess.UserDetailDataAccess;
import in.artist.database.classes.Course;
import in.artist.database.classes.UserCourseSubscription;
import in.artist.responseDto.CourseBriefDto;
import in.artist.responseDto.SubscriptionDto;

public class Converter {

	public static List<CourseBriefDto> convertListOfCoursesToListOfCourseBriefDtos(List<Course> courses) {
		if (courses == null)
			return null;
		List<CourseBriefDto> courseBriefs = new ArrayList<>();
		for (Course course : courses) {
			courseBriefs.add(convertCourseToCourseBriefDto(course));
		}
		return courseBriefs;
	}

	public static CourseBriefDto convertCourseToCourseBriefDto(Course course) {
		if (course == null)
			return null;
		CourseBriefDto briefDto = new CourseBriefDto();
		briefDto.setCourseId(course.getCourseId());
		briefDto.setCoverImageUrl(course.getCoverImageUrl());
		briefDto.setShortDescription(course.getShortDescription());
		return briefDto;
	}
	
	public static List<SubscriptionDto> convertListOfUserCourseSubscriptionToListOfSubscriptionDto(List<UserCourseSubscription> courses) {
		if (courses == null)
			return null;
		List<SubscriptionDto> courseBriefs = new ArrayList<>();
		for (UserCourseSubscription course : courses) {
			courseBriefs.add(convertUserCourseSubscriptionToSubscriptionDto(course));
		}
		return courseBriefs;
	}

	public static SubscriptionDto convertUserCourseSubscriptionToSubscriptionDto(UserCourseSubscription subscription) {
		if (subscription == null)
			return null;
		SubscriptionDto subscriptionDto = new SubscriptionDto();
		UserDetailDataAccess uDataAccess = new UserDetailDataAccess();
		subscriptionDto.setUser(uDataAccess.getUserDetails(subscription.getUserId()));
		CourseDetailDataAccess cDataAccess = new CourseDetailDataAccess();
		subscriptionDto.setCourse(cDataAccess.getCourseDetails(subscription.getCourseId()));
		subscriptionDto.setCreated(subscription.getCreated());
		subscriptionDto.setModified(subscription.getModified());
		return subscriptionDto;
	}
}
