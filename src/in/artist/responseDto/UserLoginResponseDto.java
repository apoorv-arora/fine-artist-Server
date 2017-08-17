package in.artist.responseDto;

import in.artist.database.classes.User;

public class UserLoginResponseDto extends BaseResponse {

	private User user;
	private String accessToken;
	
	public UserLoginResponseDto(){}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}