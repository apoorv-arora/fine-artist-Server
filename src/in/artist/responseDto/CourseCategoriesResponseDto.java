package in.artist.responseDto;

import java.util.List;

import in.artist.database.classes.CourseCategory;

public class CourseCategoriesResponseDto extends BaseResponse {
	private List<CourseCategory> courseCategories;
	
	public CourseCategoriesResponseDto() {
	}

	public List<CourseCategory> getCourseCategories() {
		return courseCategories;
	}

	public void setCourseCategories(List<CourseCategory> courseCategories) {
		this.courseCategories = courseCategories;
	}

}
