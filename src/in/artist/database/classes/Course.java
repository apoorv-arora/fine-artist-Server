package in.artist.database.classes;

import java.io.Serializable;

public class Course implements Serializable {
	private int courseId;
	
	// course brief
	private String title;
	private String shortDescription;
	private String coverImageUrl;

	// course category
	private int categoryId;

	// course detail data
	private String longDescription;
	private String createdByImageUrl;
	
	public Course(){}

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

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getCoverImageUrl() {
		return coverImageUrl;
	}

	public void setCoverImageUrl(String coverImageUrl) {
		this.coverImageUrl = coverImageUrl;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getCreatedByImageUrl() {
		return createdByImageUrl;
	}

	public void setCreatedByImageUrl(String createdByImageUrl) {
		this.createdByImageUrl = createdByImageUrl;
	}
	
}
