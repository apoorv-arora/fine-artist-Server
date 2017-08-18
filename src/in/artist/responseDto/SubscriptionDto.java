package in.artist.responseDto;

import in.artist.database.classes.Course;
import in.artist.database.classes.User;

public class SubscriptionDto extends BaseResponse {

	private User user;
	private Course course;
	private long created;
	private long modified;
	
	public SubscriptionDto() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public long getCreated() {
		return created;
	}

	public void setCreated(long created) {
		this.created = created;
	}

	public long getModified() {
		return modified;
	}

	public void setModified(long modified) {
		this.modified = modified;
	}
	
}
