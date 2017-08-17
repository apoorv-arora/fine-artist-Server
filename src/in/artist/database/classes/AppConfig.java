package in.artist.database.classes;

import java.io.Serializable;

public class AppConfig implements Serializable{
	
	private int appConfigId;
	private String appKey;
	private String appValue;
	
	public AppConfig(){}

	public int getAppConfigId() {
		return appConfigId;
	}

	public void setAppConfigId(int appConfigId) {
		this.appConfigId = appConfigId;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppValue() {
		return appValue;
	}

	public void setAppValue(String appValue) {
		this.appValue = appValue;
	}

}
