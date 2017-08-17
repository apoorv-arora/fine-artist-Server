package in.artist.responseDto;

import java.util.List;

import in.artist.database.classes.AppConfig;

public class AppConfigResponseDto extends BaseResponse {
	private List<AppConfig> appConfigList;

	public AppConfigResponseDto() {
	}

	public List<AppConfig> getAppConfigList() {
		return appConfigList;
	}

	public void setAppConfigList(List<AppConfig> appConfigList) {
		this.appConfigList = appConfigList;
	}

}
