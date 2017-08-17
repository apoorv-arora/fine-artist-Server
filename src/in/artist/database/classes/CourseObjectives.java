package in.artist.database.classes;

import java.io.Serializable;

public class CourseObjectives implements Serializable{
	private int courseObjectiveId;
	
	private String iconUrl;
	private String title;
	private String description;

	public CourseObjectives() {
	}

	public int getCourseObjectiveId() {
		return courseObjectiveId;
	}

	public void setCourseObjectiveId(int courseObjectiveId) {
		this.courseObjectiveId = courseObjectiveId;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
