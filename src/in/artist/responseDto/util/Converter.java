package in.artist.responseDto.util;

import java.util.ArrayList;
import java.util.List;

import in.artist.database.classes.Course;
import in.artist.responseDto.CourseBriefDto;

public class Converter {

	public static List<CourseBriefDto> convertListOfCoursesToListOfCourseBriefDtos(List<Course> courses) {
		if (courses == null)
			return null;
		List<CourseBriefDto> courseBriefs = new ArrayList<>();
		for (Course course:courses) {
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
}
