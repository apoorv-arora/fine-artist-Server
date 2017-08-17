package in.artist.database.classes;

public class SubCourse {

	// private key
	private int subCourseId;
	
	// foreign key
	private int courseId;

	private String title;
	private long startDate;
	private String commitment;
	private String aboutTheCourse;

	public SubCourse() {
	}

	public int getSubCourseId() {
		return subCourseId;
	}

	public void setSubCourseId(int subCourseId) {
		this.subCourseId = subCourseId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public String getCommitment() {
		return commitment;
	}

	public void setCommitment(String commitment) {
		this.commitment = commitment;
	}

	public String getAboutTheCourse() {
		return aboutTheCourse;
	}

	public void setAboutTheCourse(String aboutTheCourse) {
		this.aboutTheCourse = aboutTheCourse;
	}
	
}
