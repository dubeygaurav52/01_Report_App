package in.gaurav.service;

import java.util.List;

import in.gaurav.entity.CitizenPlan;
import in.gaurav.request.SearchRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ReportService {

	public List<String> getPlanNames();
	
	public List<String> getPlanStatus();
	
	public List<CitizenPlan> search(SearchRequest request);
	
	public boolean exportExcel(HttpServletResponse response) throws Exception;
	
	public boolean exportPdf(HttpServletResponse response) throws Exception;
}
