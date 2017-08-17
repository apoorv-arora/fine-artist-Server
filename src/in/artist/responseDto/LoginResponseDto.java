package in.artist.responseDto;

import java.util.function.Supplier;

public class LoginResponseDto extends BaseResponse {

	private Supplier supplier;
	private String accessToken;
	
	public LoginResponseDto(){}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
