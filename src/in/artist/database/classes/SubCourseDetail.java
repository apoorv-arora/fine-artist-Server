package in.artist.database.classes;

public class SubCourseDetail {

	// primary key
	private int subcourseDetailId;
	
	// foreign key
	private int subcourseId;
	private String timeline;
    private String title;
    private String description;

    public SubCourseDetail(){}

	public int getSubcourseDetailId() {
		return subcourseDetailId;
	}

	public void setSubcourseDetailId(int subcourseDetailId) {
		this.subcourseDetailId = subcourseDetailId;
	}

	public int getSubcourseId() {
		return subcourseId;
	}

	public void setSubcourseId(int subcourseId) {
		this.subcourseId = subcourseId;
	}

	public String getTimeline() {
		return timeline;
	}

	public void setTimeline(String timeline) {
		this.timeline = timeline;
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
