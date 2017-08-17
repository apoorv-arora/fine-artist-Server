package in.artist.database.classes;

import java.io.Serializable;

public class User implements Serializable {

	private int userId;
	private String name;
	private String number;
	private String email;
	private String imageUrl;
	
	//facebook data
	private String facebookData;
	private String facebookId;
	private String facebookPermission;
	private String facebookToken;
	
	private long created;
	private long modified;
	
	public User(){}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	public String getFacebookData() {
		return facebookData;
	}

	public void setFacebookData(String facebookData) {
		this.facebookData = facebookData;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getFacebookPermission() {
		return facebookPermission;
	}

	public void setFacebookPermission(String facebookPermission) {
		this.facebookPermission = facebookPermission;
	}

	public String getFacebookToken() {
		return facebookToken;
	}

	public void setFacebookToken(String facebookToken) {
		this.facebookToken = facebookToken;
	}

}
