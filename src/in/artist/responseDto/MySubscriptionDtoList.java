package in.artist.responseDto;

import java.util.List;

public class MySubscriptionDtoList extends BaseResponse {

	private List<SubscriptionDto> subscriptions;
	
	public MySubscriptionDtoList() {}

	public List<SubscriptionDto> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<SubscriptionDto> subscriptions) {
		this.subscriptions = subscriptions;
	}
	
}
