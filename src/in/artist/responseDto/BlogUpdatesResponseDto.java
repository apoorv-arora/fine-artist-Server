package in.artist.responseDto;

import java.util.List;

import in.artist.database.classes.BlogUpdates;

public class BlogUpdatesResponseDto extends BaseResponse {

	private List<BlogUpdates> blogUpdates;
	
	public BlogUpdatesResponseDto(){}

	public List<BlogUpdates> getBlogUpdates() {
		return blogUpdates;
	}

	public void setBlogUpdates(List<BlogUpdates> blogUpdates) {
		this.blogUpdates = blogUpdates;
	}

}
