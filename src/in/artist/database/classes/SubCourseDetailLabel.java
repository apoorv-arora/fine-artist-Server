package in.artist.database.classes;

import java.io.Serializable;

public class SubCourseDetailLabel implements Serializable {
	
	// primary key
	private int subCourseDetailLabelId;

	// secondary key
	private int subCourseDetailId;
	
	private String iconUrl;
    private String description;
    
    public SubCourseDetailLabel(){}

	public int getSubCourseDetailLabelId() {
		return subCourseDetailLabelId;
	}

	public void setSubCourseDetailLabelId(int subCourseDetailLabelId) {
		this.subCourseDetailLabelId = subCourseDetailLabelId;
	}

	public int getSubCourseDetailId() {
		return subCourseDetailId;
	}

	public void setSubCourseDetailId(int subCourseDetailId) {
		this.subCourseDetailId = subCourseDetailId;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
}
