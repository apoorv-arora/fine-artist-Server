package in.artist.responseDto;

import java.util.List;

public class RecommendedCoursesDto extends BaseResponse {

	private List<CourseBriefDto> recommendedCourses;
	
	public RecommendedCoursesDto(){}

	public List<CourseBriefDto> getRecommendedCourses() {
		return recommendedCourses;
	}

	public void setRecommendedCourses(List<CourseBriefDto> recommendedCourses) {
		this.recommendedCourses = recommendedCourses;
	}
	
}
