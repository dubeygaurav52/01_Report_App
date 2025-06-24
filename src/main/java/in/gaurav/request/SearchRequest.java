package in.gaurav.request;

import lombok.Data;

@Data
public class SearchRequest {

	private String planName;
	private String planStatus;
	private String gender;
	private String planStartDate;
	private String planEndDate;
}
