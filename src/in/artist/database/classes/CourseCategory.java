package in.artist.database.classes;

import java.io.Serializable;

public class CourseCategory implements Serializable {
	private int courseCategoryId;
	
    private String categoryTitle;
    private String categoryIconUrl;

    public CourseCategory(){}
    
    public int getCourseCategoryId() {
		return courseCategoryId;
	}

	public void setCourseCategoryId(int courseCategoryId) {
		this.courseCategoryId = courseCategoryId;
	}

	public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryIconUrl() {
        return categoryIconUrl;
    }

    public void setCategoryIconUrl(String categoryIconUrl) {
        this.categoryIconUrl = categoryIconUrl;
    }
}
